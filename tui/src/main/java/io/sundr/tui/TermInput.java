package io.sundr.tui;

/**
 * Terminal input interface for reading keyboard input in a cross-platform way.
 * Provides functionality to enter raw mode, read keys, and detect if terminal is interactive.
 */
public interface TermInput extends AutoCloseable {

  /**
   * Enter raw mode (no-echo, no line buffering); restores on close.
   *
   * @return An AutoCloseable that restores the previous terminal mode when closed
   */
  AutoCloseable enterRawMode();

  /**
   * Blocking single-key read (UTF-16 codepoint).
   *
   * @return The key code as an integer, or -1 on EOF
   */
  int readKey();

  /**
   * Non-blocking read with timeout; returns -2 on timeout.
   *
   * @param timeoutMillis Timeout in milliseconds
   * @return The key code as an integer, -1 on EOF, or -2 on timeout
   */
  int readKey(long timeoutMillis);

  /**
   * Is terminal a TTY?
   *
   * @return true if the terminal is interactive (TTY), false otherwise
   */
  boolean isInteractive();
}