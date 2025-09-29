package io.sundr.tui;

/**
 * Example demonstrating the TermFrame with key binding system.
 * This example shows how to use the TUI module with custom key bindings.
 */
public class TermFrameExample {

  public static void main(String[] args) throws Exception {
    // Create a TermFrame with header and footer
    String header = "=== Sundrio TUI Key Binding Example ===";
    String footer = "Status: Running | Keys: ?=Help, ESC=Exit, c=Clear, t=Time";

    try (TermFrame frame = TermFrame.newFrame()
        .withHeader(header)
        .withFooter(footer)
        .withExitKey(Key.ESC)
        .withKeyBinding('c', "Clear screen", tf -> {
          tf.clearContent();
          tf.println("Screen cleared!");
        }).withKeyBinding('t', "Show current time", tf -> {
          tf.println("Current time: " + java.time.LocalTime.now());
        }).withKeyBinding(Key.CTRL_C, "Force exit", tf -> {
          tf.println("Ctrl+C pressed - exiting...");
          System.exit(0);
        }).withKeyListener((tf, key) -> {
          // Only show unbound keys to avoid duplicate messages
          boolean isBinding = key == Key.ESC.getKeyCode() ||
              key == Key.QUESTION_MARK.getKeyCode() ||
              key == 'c' || key == 't' ||
              key == Key.CTRL_C.getKeyCode();
          if (!isBinding) {
            tf.println("Key pressed: " + Key.getKeyName(key));
          }
        })) {
      frame.start();

      // Show initial content
      frame.println("Key binding example started!");
      frame.println("Interactive: " + frame.getTermInput().isInteractive());
      frame.println("Terminal size: " + frame.rows() + " x " + frame.cols());
      frame.println("Content height: " + frame.contentHeight());
      frame.println("");
      frame.println("Try the following keys:");
      frame.println("  ? - Show help");
      frame.println("  c - Clear screen");
      frame.println("  t - Show time");
      frame.println("  ESC - Exit");
      frame.println("");
      frame.println("Press any key to start...");

      // Run the event loop - handles all key processing internally
      frame.run();

      frame.println("Example finished!");
      Thread.sleep(1000); // Brief pause before exit
    }
  }
}
