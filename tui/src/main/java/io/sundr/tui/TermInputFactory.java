package io.sundr.tui;

/**
 * Factory for creating TermInput instances. Provides different implementations
 * based on available dependencies and platform capabilities.
 */
public final class TermInputFactory {

  private TermInputFactory() {
    // utility class
  }

  /**
   * Create the best available TermInput implementation.
   * Prefers JLine implementation if available, falls back to system implementation.
   *
   * @return A TermInput instance
   */
  public static TermInput create() {
    try {
      // Try to use JLine implementation first
      Class.forName("org.jline.terminal.Terminal");
      return new JLineTermInput();
    } catch (ClassNotFoundException | NoClassDefFoundError e) {
      // Fallback to system implementation
      return new SystemTermInput();
    }
  }

  /**
   * Create a JLine-based TermInput implementation.
   *
   * @return A JLineTermInput instance
   * @throws RuntimeException if JLine is not available
   */
  public static JLineTermInput createJLine() {
    try {
      Class.forName("org.jline.terminal.Terminal");
      return new JLineTermInput();
    } catch (ClassNotFoundException | NoClassDefFoundError e) {
      throw new RuntimeException("JLine library not available in classpath", e);
    }
  }

  /**
   * Create a system-based TermInput implementation.
   *
   * @return A SystemTermInput instance
   */
  public static SystemTermInput createSystem() {
    return new SystemTermInput();
  }
}