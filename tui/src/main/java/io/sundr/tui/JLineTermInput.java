package io.sundr.tui;

import java.io.IOException;

import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;

/**
 * JLine-based implementation of TermInput that provides OS-agnostic terminal input handling.
 * Uses JLine3 library to handle raw mode, key reading, and TTY detection across platforms.
 */
public class JLineTermInput implements TermInput {

  private final Terminal terminal;
  private volatile boolean closed = false;

  /**
   * Creates a new JLineTermInput instance.
   * Initializes the underlying JLine terminal with system defaults.
   *
   * @throws RuntimeException if terminal initialization fails
   */
  public JLineTermInput() {
    try {
      this.terminal = TerminalBuilder.builder()
          .system(true)
          .build();
    } catch (IOException e) {
      throw new RuntimeException("Failed to initialize terminal", e);
    }
  }

  @Override
  public AutoCloseable enterRawMode() {
    if (closed) {
      throw new IllegalStateException("TermInput is closed");
    }

    terminal.enterRawMode();

    return () -> {
      if (!closed && terminal != null) {
        try {
          terminal.echo(true);
          terminal.flush();
        } catch (Exception e) {
          // ignore
        }
      }
    };
  }

  @Override
  public int readKey() {
    if (closed) {
      return -1;
    }

    try {
      return terminal.reader().read();
    } catch (IOException e) {
      return -1;
    }
  }

  @Override
  public int readKey(long timeoutMillis) {
    if (closed) {
      return -1;
    }

    try {
      return terminal.reader().read(timeoutMillis);
    } catch (IOException e) {
      return -1;
    }
  }

  @Override
  public boolean isInteractive() {
    if (closed) {
      return false;
    }

    return terminal != null && Terminal.TYPE_DUMB.equals(terminal.getType()) == false;
  }

  @Override
  public void close() throws Exception {
    if (!closed && terminal != null) {
      closed = true;
      try {
        terminal.close();
      } catch (IOException e) {
        // ignore
      }
    }
  }

  /**
   * Get the underlying JLine terminal instance.
   *
   * @return The JLine Terminal instance
   */
  public Terminal getTerminal() {
    return terminal;
  }
}
