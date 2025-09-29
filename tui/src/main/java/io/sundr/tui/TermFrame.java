package io.sundr.tui;

import static java.nio.charset.StandardCharsets.UTF_8;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;

import org.fusesource.jansi.AnsiConsole;

/**
 * TermFrame: Pin a header & footer; print safely in-between.
 * - Dynamic resize (watches rows/cols; redraws frame).
 * - Every printed line is cleared first to avoid leftovers.
 * - Clear content region without touching header/footer.
 *
 * Requires a VT-capable terminal. Uses Jansi to enable ANSI on Windows.
 */
public class TermFrame implements AutoCloseable {
  private static final String ESC = "\u001B[";

  private final PrintStream out;
  private Supplier<String> headerSupplier;
  private Supplier<String> footerSupplier;

  // Live terminal geometry
  private int rows;
  private int cols;

  // Derived (1-based absolute rows)
  private int contentTop;
  private int contentBottom;

  private final Object renderLock = new Object();
  private boolean started = false;

  // Resize watcher
  private Thread resizeThread;
  private final AtomicBoolean keepWatching = new AtomicBoolean(false);
  private int watchIntervalMs = 250; // tweak if desired

  private final TermInput termInput;
  private Thread shutdownHook;

  // Track current line position for fill-then-scroll behavior
  private int currentLine;

  // Key binding system
  private final Map<Integer, KeyBinding> keyBindings;
  private final int helpKey;
  private final int exitKey;
  private final List<BiConsumer<TermFrame, Integer>> keyListeners;

  // Content buffer for help screen restoration
  private List<String> contentBuffer;
  private int savedCurrentLine;
  private final List<String> displayedLines = new ArrayList<>();
  private boolean helpMode = false;

  // State management for save/restore functionality
  private TermFrameState savedState;

  private TermFrame(Supplier<String> headerSupplier, Supplier<String> footerSupplier, Integer initialRows, Integer initialCols,
      TermInput termInput,
      Map<Integer, KeyBinding> keyBindings, int helpKey, int exitKey, List<BiConsumer<TermFrame, Integer>> keyListeners) {
    this.out = AnsiConsole.out();
    this.headerSupplier = headerSupplier == null ? () -> "" : headerSupplier;
    this.footerSupplier = footerSupplier == null ? () -> "" : footerSupplier;
    this.termInput = termInput == null ? new JLineTermInput() : termInput;
    this.keyBindings = keyBindings == null ? new HashMap<>() : new HashMap<>(keyBindings);
    this.helpKey = helpKey;
    this.exitKey = exitKey;
    this.keyListeners = keyListeners == null ? new ArrayList<>() : new ArrayList<>(keyListeners);

    int[] size = detectSizeOrDefault(initialRows, initialCols);
    this.rows = size[0];
    this.cols = size[1];
    recomputeContentArea();
    validateHeights();
  }

  // Convenience constructor for simpler cases
  private TermFrame(Supplier<String> headerSupplier, Supplier<String> footerSupplier, Integer initialRows, Integer initialCols,
      TermInput termInput) {
    this(headerSupplier, footerSupplier, initialRows, initialCols, termInput, null, Key.QUESTION_MARK.getKeyCode(),
        Key.ESC.getKeyCode(), null);
  }

  /** Create a new TermFrame with default settings. */
  public static TermFrame newFrame() {
    return new TermFrame(() -> "", () -> "", null, null, null);
  }

  /** Create a new TermFrame with header and footer suppliers. */
  public static TermFrame newFrame(Supplier<String> headerSupplier, Supplier<String> footerSupplier) {
    return new TermFrame(headerSupplier, footerSupplier, null, null, null);
  }

  /** Start: draw frame, set scroll region, start resize watcher. */
  public void start() {
    if (started)
      return;
    AnsiConsole.systemInstall();
    synchronized (renderLock) {
      hideCursor();
      fullRedraw(); // computes scroll region, draws header/footer, clears content
      started = true;
      startResizeWatcher();

      // Add shutdown hook to ensure terminal is restored on unexpected exit
      shutdownHook = new Thread(() -> {
        try {
          restoreTerminal();
        } catch (Exception e) {
          // ignore
        }
      }, "termframe-shutdown-hook");
      Runtime.getRuntime().addShutdownHook(shutdownHook);
    }
  }

  /** Stop: restore terminal and stop watcher. */
  @Override
  public void close() {
    synchronized (renderLock) {
      if (started) {
        // Remove shutdown hook since we're closing normally
        try {
          if (shutdownHook != null) {
            Runtime.getRuntime().removeShutdownHook(shutdownHook);
            shutdownHook = null;
          }
        } catch (Exception e) {
          // ignore - might already be shutting down
        }

        restoreTerminal();
        started = false;
      }
    }
    try {
      termInput.close();
    } catch (Exception e) {
      // ignore
    }
  }

  private void restoreTerminal() {
    stopResizeWatcher();

    // Restore terminal to normal state
    resetScrollRegion();
    clearScreen();
    moveCursor(1, 1); // Move to top-left
    showCursor();

    // Reset any terminal modes that might have been changed
    out.print(ESC + "c"); // Reset terminal (RIS - Reset to Initial State)

    flush();
    AnsiConsole.systemUninstall();
  }

  /** Clears the content area and returns cursor to its top-left. */
  public void clearContent() {
    synchronized (renderLock) {
      for (int r = contentTop; r <= contentBottom; r++) {
        moveCursor(r, 1);
        clearLine();
      }
      moveCursor(contentTop, 1);
      currentLine = contentTop; // Reset to start from top
      displayedLines.clear(); // Clear the content buffer
      flush();
    }
  }

  /** Print a scrolling line in the content area; always clears line first. */
  public void println(String s) {
    synchronized (renderLock) {
      if (!started) {
        return; // Don't print if not started
      }

      String[] lines = (s == null ? "" : s).split("\\n|\\r");
      for (String line : lines) {
        if (currentLine <= contentBottom) {
          // Fill mode: write to current line and advance
          moveCursor(currentLine, 1);
          clearLine();
          out.print(line);
          displayedLines.add(line);
          currentLine++;
        } else {
          // Scroll mode: we've filled the content area, now scroll
          moveCursor(contentBottom, 1);
          clearLine();
          out.print(line);
          out.print("\n"); // This will cause scrolling within the scroll region

          // Remove first line and add new line to maintain buffer
          if (!displayedLines.isEmpty()) {
            displayedLines.remove(0);
          }
          displayedLines.add(line);
        }
      }

      // Redraw header and footer in case dynamic content has changed
      drawHeader();
      drawFooter();
      flush();
    }
  }

  /** Overwrite a specific content row (0-based) without scrolling; clears that line first. */
  public void printAt(int rowOffset, String s) {
    synchronized (renderLock) {
      int absRow = contentTop + rowOffset;
      if (absRow < contentTop || absRow > contentBottom)
        return;
      moveCursor(absRow, 1);
      clearLine();
      out.print(s == null ? "" : s);
      flush();
    }
  }

  /** Get the TermInput instance for reading key input */
  public TermInput getTermInput() {
    return termInput;
  }

  // --- Key Binding System ---

  /** Create a new TermFrame with a header supplier. */
  public TermFrame withHeader(Supplier<String> headerSupplier) {
    return new TermFrame(headerSupplier, this.footerSupplier, null, null, this.termInput,
        this.keyBindings, this.helpKey, this.exitKey, this.keyListeners);
  }

  /** Create a new TermFrame with a header string. */
  public TermFrame withHeader(String header) {
    return withHeader(() -> header);
  }

  /** Create a new TermFrame with a footer supplier. */
  public TermFrame withFooter(Supplier<String> footerSupplier) {
    return new TermFrame(this.headerSupplier, footerSupplier, null, null, this.termInput,
        this.keyBindings, this.helpKey, this.exitKey, this.keyListeners);
  }

  /** Create a new TermFrame with a footer string. */
  public TermFrame withFooter(String footer) {
    return withFooter(() -> footer);
  }

  /** Create a new TermFrame with a key binding. */
  public TermFrame withKeyBinding(Key key, String description, Consumer<TermFrame> action) {
    return withKeyBinding(key.getKeyCode(), description, action);
  }

  /** Create a new TermFrame with a key binding. */
  public TermFrame withKeyBinding(int keyCode, String description, Consumer<TermFrame> action) {
    Map<Integer, KeyBinding> newBindings = new HashMap<>(this.keyBindings);
    newBindings.put(keyCode, new KeyBinding(keyCode, description, action));
    return new TermFrame(this.headerSupplier, this.footerSupplier, null, null, this.termInput,
        newBindings, this.helpKey, this.exitKey, this.keyListeners);
  }

  /** Create a new TermFrame with an exit key. */
  public TermFrame withExitKey(Key exitKey) {
    return new TermFrame(this.headerSupplier, this.footerSupplier, null, null, this.termInput,
        this.keyBindings, this.helpKey, exitKey.getKeyCode(), this.keyListeners);
  }

  /** Create a new TermFrame with a help key. */
  public TermFrame withHelpKey(Key helpKey) {
    return new TermFrame(this.headerSupplier, this.footerSupplier, null, null, this.termInput,
        this.keyBindings, helpKey.getKeyCode(), this.exitKey, this.keyListeners);
  }

  /** Create a new TermFrame with a help key. */
  public TermFrame withHelpKey(int keyCode) {
    return new TermFrame(this.headerSupplier, this.footerSupplier, null, null, this.termInput,
        this.keyBindings, keyCode, this.exitKey, this.keyListeners);
  }

  /** Create a new TermFrame with an additional key listener. */
  public TermFrame withKeyListener(BiConsumer<TermFrame, Integer> listener) {
    List<BiConsumer<TermFrame, Integer>> newListeners = new ArrayList<>(this.keyListeners);
    newListeners.add(listener);
    return new TermFrame(this.headerSupplier, this.footerSupplier, null, null, this.termInput,
        this.keyBindings, this.helpKey, this.exitKey, newListeners);
  }

  /**
   * Process a key press and execute bound actions.
   *
   * @return true to continue, false to exit
   */
  public boolean processKey(int keyCode) {
    // If in help mode, any key restores content
    if (helpMode) {
      helpMode = false;
      restoreContent();
      return true;
    }

    // Check for exit key
    if (keyCode == exitKey) {
      return false;
    }

    // Check for help key
    if (keyCode == helpKey) {
      showHelp();
      return true;
    }

    // Check for custom bindings
    KeyBinding binding = keyBindings.get(keyCode);
    if (binding != null) {
      binding.getAction().accept(this);
      return true;
    }

    // Key not bound, continue
    return true;
  }

  /**
   * Show help screen with all key bindings.
   */
  public void showHelp() {
    // Save current content
    saveContent();
    helpMode = true;

    clearContent();
    println("=== Key Bindings ===");
    println("");

    // Show exit key
    println(Key.getKeyName(exitKey) + " - Exit");

    // Show help key
    println(Key.getKeyName(helpKey) + " - Show this help");

    // Show custom bindings
    for (KeyBinding binding : keyBindings.values()) {
      println(binding.getKeyName() + " - " + binding.getDescription());
    }

    if (keyBindings.isEmpty()) {
      println("(No custom key bindings defined)");
    }

    println("");
    println("Press any key to continue...");
  }

  /**
   * Save current content for later restoration.
   */
  private void saveContent() {
    contentBuffer = new ArrayList<>(displayedLines);
    savedCurrentLine = currentLine;
  }

  /**
   * Restore previously saved content.
   */
  public void restoreContent() {
    if (contentBuffer != null) {
      clearContent();
      for (String line : contentBuffer) {
        println(line);
      }
      contentBuffer = null; // Clear after restoration
    }
  }

  /**
   * Save the current complete state of the TermFrame.
   */
  public void saveState() {
    savedState = new TermFrameState(
        headerSupplier,
        footerSupplier,
        new ArrayList<>(displayedLines),
        currentLine);
  }

  /**
   * Restore the previously saved state.
   */
  public void restoreState() {
    if (savedState != null) {
      // Note: We can't change final headerSupplier/footerSupplier,
      // but we can restore content and position
      this.currentLine = savedState.getCurrentLine();

      clearContent();
      for (String line : savedState.getContentLines()) {
        println(line);
      }
      savedState = null; // Clear after restoration
    }
  }

  /**
   * Wait for any key press and then execute the callback.
   * This is useful for "press any key to continue" scenarios.
   *
   * @param callback called with (termFrame, keyCode) when any key is pressed
   */
  public void waitForAnyKeyPress(BiConsumer<TermFrame, Integer> callback) throws Exception {
    if (!started || termInput == null) {
      throw new IllegalStateException("TermFrame must be started before waiting for key press");
    }

    try (AutoCloseable rawMode = termInput.enterRawMode()) {
      int key = termInput.readKey();
      if (key != -1) {
        callback.accept(this, key);
      }
    }
  }

  /**
   * Run the main event loop, processing keys until exit.
   * This method handles the key reading and processing internally.
   */
  public void run() throws Exception {
    if (!started) {
      throw new IllegalStateException("TermFrame must be started before running event loop");
    }

    try (AutoCloseable rawMode = termInput.enterRawMode()) {
      boolean running = true;

      while (running) {
        int key = termInput.readKey(1000);

        if (key == -2) {
          // Timeout - continue waiting
          continue;
        } else if (key == -1) {
          // EOF - exit
          println("EOF received - exiting...");
          break;
        } else {
          // Process the key through the binding system
          running = processKey(key);

          // Notify listeners after processing
          for (BiConsumer<TermFrame, Integer> listener : keyListeners) {
            try {
              listener.accept(this, key);
            } catch (Exception e) {
              // Ignore listener exceptions to prevent breaking the event loop
            }
          }
        }
      }
    }
  }

  public int contentHeight() {
    return contentBottom - contentTop + 1;
  }

  public int rows() {
    return rows;
  }

  public int cols() {
    return cols;
  }

  public TermFrame watchIntervalMs(int ms) {
    this.watchIntervalMs = Math.max(50, ms);
    return this;
  }

  // ---------- internals ----------

  private void fullRedraw() {
    validateHeights();
    resetScrollRegion(); // in case it was set previously
    clearScreen(); // wipe everything
    drawHeader();
    drawFooter();
    setScrollRegion(contentTop, contentBottom);
    moveCursor(contentTop, 1);
    currentLine = contentTop; // Reset to start from top
    flush();
  }

  private void validateHeights() {
    List<String> header = getHeaderLines();
    List<String> footer = getFooterLines();
    int headerHeight = header.size();
    int footerHeight = footer.size();
    if (headerHeight + footerHeight >= rows) {
      throw new IllegalStateException("Header+Footer exceed terminal height (" + rows + ")");
    }
  }

  private void recomputeContentArea() {
    List<String> header = getHeaderLines();
    List<String> footer = getFooterLines();
    int headerHeight = header.size();
    int footerHeight = footer.size();
    this.contentTop = headerHeight + 1;
    this.contentBottom = rows - footerHeight;
  }

  private List<String> getHeaderLines() {
    String headerText = headerSupplier.get();
    if (headerText == null || headerText.trim().isEmpty()) {
      return List.of();
    }
    return List.of(headerText.split("\\n"));
  }

  private List<String> getFooterLines() {
    String footerText = footerSupplier.get();
    if (footerText == null || footerText.trim().isEmpty()) {
      return List.of();
    }
    return List.of(footerText.split("\\n"));
  }

  private void drawHeader() {
    List<String> header = getHeaderLines();
    for (int i = 0; i < header.size(); i++) {
      moveCursor(1 + i, 1);
      clearLine();
      out.print(header.get(i));
    }
  }

  private void drawFooter() {
    List<String> footer = getFooterLines();
    int topOfFooter = rows - footer.size() + 1;
    for (int i = 0; i < footer.size(); i++) {
      moveCursor(topOfFooter + i, 1);
      clearLine();
      out.print(footer.get(i));
    }
  }

  // --- ANSI
  private void clearScreen() {
    out.print(ESC + "2J" + ESC + "H");
  }

  private void clearLine() {
    out.print("\r");
    out.print(ESC + "2K");
  }

  private void moveCursor(int row, int col) {
    out.print(ESC + row + ";" + col + "H");
  }

  private void setScrollRegion(int top, int bottom) {
    out.print(ESC + top + ";" + bottom + "r");
  }

  private void resetScrollRegion() {
    out.print(ESC + "r");
  }

  private void hideCursor() {
    out.print(ESC + "?25l");
  }

  private void showCursor() {
    out.print(ESC + "?25h");
  }

  private void flush() {
    out.flush();
  }

  // --- Size detection & watcher

  private int[] detectSizeOrDefault(Integer preferredRows, Integer preferredCols) {
    if (preferredRows != null && preferredRows > 0 && preferredCols != null && preferredCols > 0) {
      return new int[] { preferredRows, preferredCols };
    }

    // Try to get size from JLine terminal first (more accurate)
    if (termInput instanceof JLineTermInput) {
      try {
        JLineTermInput jlineInput = (JLineTermInput) termInput;
        org.jline.terminal.Terminal terminal = jlineInput.getTerminal();
        if (terminal != null) {
          int jlineRows = terminal.getHeight();
          int jlineCols = terminal.getWidth();
          if (jlineRows > 0 && jlineCols > 0) {
            return new int[] { jlineRows, jlineCols };
          }
        }
      } catch (Exception e) {
        // Fall back to system detection
      }
    }

    // Fallback to system commands
    int rows = detectRows().orElse(24);
    int cols = detectCols().orElse(80);
    return new int[] { rows, cols };
  }

  public static Optional<Integer> detectRows() {
    Integer v = runInt("stty size < /dev/tty", 0); // "ROWS COLS"
    if (v != null && v > 0)
      return Optional.of(v);
    v = runInt("tput lines", -1);
    return (v != null && v > 0) ? Optional.of(v) : Optional.empty();
  }

  public static Optional<Integer> detectCols() {
    Integer v = runInt2("stty size < /dev/tty", 1); // second token = COLS
    if (v != null && v > 0)
      return Optional.of(v);
    v = runInt("tput cols", -1);
    return (v != null && v > 0) ? Optional.of(v) : Optional.empty();
  }

  private static Integer runInt(String cmd, int fallbackIfParseFail) {
    try {
      Process p = new ProcessBuilder("sh", "-c", cmd).redirectErrorStream(true).start();
      p.getOutputStream().close();
      String out = new String(p.getInputStream().readAllBytes(), UTF_8).trim();
      p.waitFor();
      return Integer.parseInt(out);
    } catch (Exception e) {
      return fallbackIfParseFail >= 0 ? fallbackIfParseFail : null;
    }
  }

  private static Integer runInt2(String cmd, int tokenIndex) {
    try {
      Process p = new ProcessBuilder("sh", "-c", cmd).redirectErrorStream(true).start();
      p.getOutputStream().close();
      String out = new String(p.getInputStream().readAllBytes(), UTF_8).trim();
      p.waitFor();
      if (out.isBlank())
        return null;
      String[] parts = out.split("\\s+");
      if (parts.length <= tokenIndex)
        return null;
      return Integer.parseInt(parts[tokenIndex]);
    } catch (Exception e) {
      return null;
    }
  }

  private void startResizeWatcher() {
    keepWatching.set(true);
    resizeThread = new Thread(() -> {
      int lastRows = rows;
      int lastCols = cols;
      while (keepWatching.get()) {
        try {
          int[] newSize = detectSizeOrDefault(null, null);
          int r = newSize[0];
          int c = newSize[1];

          if (r != lastRows || c != lastCols) {
            synchronized (renderLock) {
              rows = r;
              cols = c;
              recomputeContentArea();
              fullRedraw();
            }
            lastRows = r;
            lastCols = c;
          }
          Thread.sleep(watchIntervalMs);
        } catch (InterruptedException ie) {
          Thread.currentThread().interrupt();
          break;
        } catch (Exception ignored) {
        }
      }
    }, "termframe-resize-watcher");
    resizeThread.setDaemon(true);
    resizeThread.start();
  }

  private void stopResizeWatcher() {
    keepWatching.set(false);
    if (resizeThread != null) {
      resizeThread.interrupt();
      try {
        resizeThread.join(250);
      } catch (InterruptedException ignored) {
      }
    }
  }
}
