package io.sundr.tui;

import java.util.function.Consumer;

/**
 * Represents a key binding with its description and action.
 */
public class KeyBinding {
  private final int keyCode;
  private final String description;
  private final Consumer<TermFrame> action;

  public KeyBinding(int keyCode, String description, Consumer<TermFrame> action) {
    this.keyCode = keyCode;
    this.description = description;
    this.action = action;
  }

  public KeyBinding(Key key, String description, Consumer<TermFrame> action) {
    this(key.getKeyCode(), description, action);
  }

  public int getKeyCode() {
    return keyCode;
  }

  public String getDescription() {
    return description;
  }

  public Consumer<TermFrame> getAction() {
    return action;
  }

  /**
   * Get a human-readable representation of the key.
   */
  public String getKeyName() {
    return Key.getKeyName(keyCode);
  }
}