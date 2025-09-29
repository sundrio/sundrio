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

  public TermFrameState(Supplier<String> headerSupplier, Supplier<String> footerSupplier,
      List<String> contentLines, int currentLine) {
    this.headerSupplier = headerSupplier;
    this.footerSupplier = footerSupplier;
    this.contentLines = new ArrayList<>(contentLines);
    this.currentLine = currentLine;
  }

  public Supplier<String> getHeaderSupplier() {
    return headerSupplier;
  }

  public Supplier<String> getFooterSupplier() {
    return footerSupplier;
  }

  public List<String> getContentLines() {
    return new ArrayList<>(contentLines);
  }

  public int getCurrentLine() {
    return currentLine;
  }
}