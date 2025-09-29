package io.sundr.tui;

/**
 * Enum representing common keyboard keys with their key codes and display names.
 */
public enum Key {
  // Control keys
  ESC(27, "ESC"), ENTER(10, "Enter"), ENTER_CR(13, "Enter"), TAB(9, "Tab"), BACKSPACE(127, "Backspace"), SPACE(32, "Space"),

  // Important Ctrl combinations
  CTRL_A(1, "Ctrl+A"), CTRL_C(3, "Ctrl+C"), CTRL_D(4, "Ctrl+D"), CTRL_X(24, "Ctrl+X"), CTRL_Z(26, "Ctrl+Z"),

  // Common special characters
  QUESTION_MARK(63, "?");

  private final int keyCode;
  private final String displayName;

  Key(int keyCode, String displayName) {
    this.keyCode = keyCode;
    this.displayName = displayName;
  }

  public int getKeyCode() {
    return keyCode;
  }

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