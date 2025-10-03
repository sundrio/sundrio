package io.sundr.tui;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

/**
 * Represents the complete state of a TermFrame that can be saved and restored.
 */
public class TermFrameState {
  private final Supplier<String> headerSupplier;
  private final Supplier<String> footerSupplier;
  private final List<String> contentLines;
  private final int currentLine;

  /**
   * Creates a new TermFrameState with the specified components.
   *
   * @param headerSupplier supplier for the header content
   * @param footerSupplier supplier for the footer content
   * @param contentLines list of content lines
   * @param currentLine current line position
   */
  public TermFrameState(Supplier<String> headerSupplier, Supplier<String> footerSupplier,
      List<String> contentLines, int currentLine) {
    this.headerSupplier = headerSupplier;
    this.footerSupplier = footerSupplier;
    this.contentLines = new ArrayList<>(contentLines);
    this.currentLine = currentLine;
  }

  /**
   * Gets the header supplier.
   *
   * @return the header supplier
   */
  public Supplier<String> getHeaderSupplier() {
    return headerSupplier;
  }

  /**
   * Gets the footer supplier.
   *
   * @return the footer supplier
   */
  public Supplier<String> getFooterSupplier() {
    return footerSupplier;
  }

  /**
   * Gets a copy of the content lines.
   *
   * @return a new list containing the content lines
   */
  public List<String> getContentLines() {
    return new ArrayList<>(contentLines);
  }

  /**
   * Gets the current line position.
   *
   * @return the current line position
   */
  public int getCurrentLine() {
    return currentLine;
  }
}