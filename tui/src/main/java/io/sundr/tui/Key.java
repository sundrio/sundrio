package io.sundr.tui;

/**
 * Enum representing common keyboard keys with their key codes and display names.
 */
public enum Key {
  // Control keys
  /** Escape key */
  ESC(27, "ESC"),
  /** Enter key (line feed) */
  ENTER(10, "Enter"),
  /** Enter key (carriage return) */
  ENTER_CR(13, "Enter"),
  /** Tab key */
  TAB(9, "Tab"),
  /** Backspace key */
  BACKSPACE(127, "Backspace"),
  /** Space key */
  SPACE(32, "Space"),

  // Important Ctrl combinations
  /** Ctrl+A key combination */
  CTRL_A(1, "Ctrl+A"),
  /** Ctrl+C key combination */
  CTRL_C(3, "Ctrl+C"),
  /** Ctrl+D key combination */
  CTRL_D(4, "Ctrl+D"),
  /** Ctrl+X key combination */
  CTRL_X(24, "Ctrl+X"),
  /** Ctrl+Z key combination */
  CTRL_Z(26, "Ctrl+Z"),

  // Common special characters
  /** Question mark character */
  QUESTION_MARK(63, "?");

  private final int keyCode;
  private final String displayName;

  Key(int keyCode, String displayName) {
    this.keyCode = keyCode;
    this.displayName = displayName;
  }

  /**
   * Gets the numeric key code for this key.
   *
   * @return the key code
   */
  public int getKeyCode() {
    return keyCode;
  }

  /**
   * Gets the human-readable display name for this key.
   *
   * @return the display name
   */
  public String getDisplayName() {
    return displayName;
  }

  /**
   * Find a Key enum by its key code.
   *
   * @param keyCode the key code to search for
   * @return the Key enum if found, null otherwise
   */
  public static Key fromKeyCode(int keyCode) {
    for (Key key : values()) {
      if (key.keyCode == keyCode) {
        return key;
      }
    }
    return null;
  }

  /**
   * Get a human-readable representation of any key code.
   * Uses enum display name if available, otherwise formats the raw key code.
   *
   * @param keyCode the key code to get the name for
   * @return human-readable key name
   */
  public static String getKeyName(int keyCode) {
    Key key = fromKeyCode(keyCode);
    if (key != null) {
      return key.getDisplayName();
    }

    // Fallback for unknown keys
    if (keyCode >= 32 && keyCode <= 126) {
      return "'" + (char) keyCode + "'";
    } else {
      return "Key(" + keyCode + ")";
    }
  }
}