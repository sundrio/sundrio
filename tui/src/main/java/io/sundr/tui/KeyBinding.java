package io.sundr.tui;

import java.util.function.Consumer;

/**
 * Represents a key binding with its description and action.
 */
public class KeyBinding {
  private final int keyCode;
  private final String description;
  private final Consumer<TermFrame> action;

  /**
   * Creates a key binding with a numeric key code.
   *
   * @param keyCode the numeric key code
   * @param description human-readable description of the action
   * @param action the action to execute when the key is pressed
   */
  public KeyBinding(int keyCode, String description, Consumer<TermFrame> action) {
    this.keyCode = keyCode;
    this.description = description;
    this.action = action;
  }

  /**
   * Creates a key binding with a Key enum.
   *
   * @param key the Key enum value
   * @param description human-readable description of the action
   * @param action the action to execute when the key is pressed
   */
  public KeyBinding(Key key, String description, Consumer<TermFrame> action) {
    this(key.getKeyCode(), description, action);
  }

  /**
   * Gets the numeric key code for this binding.
   *
   * @return the key code
   */
  public int getKeyCode() {
    return keyCode;
  }

  /**
   * Gets the human-readable description for this binding.
   *
   * @return the description
   */
  public String getDescription() {
    return description;
  }

  /**
   * Gets the action to be executed when the key is pressed.
   *
   * @return the action consumer
   */
  public Consumer<TermFrame> getAction() {
    return action;
  }

  /**
   * Get a human-readable representation of the key.
   *
   * @return the human-readable key name
   */
  public String getKeyName() {
    return Key.getKeyName(keyCode);
  }
}