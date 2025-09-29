package io.sundr.tui;

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * System-based implementation of TermInput that uses native system commands
 * to handle terminal input. This is a fallback implementation when JLine is not available.
 */
public class SystemTermInput implements TermInput {

  private final InputStream inputStream;
  private volatile boolean closed = false;
  private volatile Process sttyProcess = null;

  public SystemTermInput() {
    this.inputStream = System.in;
  }

  @Override
  public AutoCloseable enterRawMode() {
    if (closed) {
      throw new IllegalStateException("TermInput is closed");
    }

    try {
      // Try to set raw mode using stty
      if (isUnixLike()) {
        sttyProcess = new ProcessBuilder("stty", "-icanon", "-echo", "min", "1")
            .inheritIO()
            .start();
        sttyProcess.waitFor();
      }
    } catch (Exception e) {
      // Fallback - continue without raw mode
    }

    return () -> {
      if (!closed) {
        try {
          // Restore normal mode
          if (isUnixLike()) {
            Process restoreProcess = new ProcessBuilder("stty", "icanon", "echo")
                .inheritIO()
                .start();
            restoreProcess.waitFor();
          }
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
      return inputStream.read();
    } catch (IOException e) {
      return -1;
    }
  }

  @Override
  public int readKey(long timeoutMillis) {
    if (closed) {
      return -1;
    }

    CompletableFuture<Integer> readFuture = CompletableFuture.supplyAsync(() -> {
      try {
        return inputStream.read();
      } catch (IOException e) {
        return -1;
      }
    });

    try {
      return readFuture.get(timeoutMillis, TimeUnit.MILLISECONDS);
    } catch (TimeoutException e) {
      readFuture.cancel(true);
      return -2; // timeout
    } catch (Exception e) {
      return -1;
    }
  }

  @Override
  public boolean isInteractive() {
    if (closed) {
      return false;
    }

    // Check if we're in a TTY environment
    String term = System.getenv("TERM");
    if (term == null || "dumb".equals(term)) {
      return false;
    }

    // Try to run tty command on Unix-like systems
    if (isUnixLike()) {
      try {
        Process ttyProcess = new ProcessBuilder("tty").start();
        ttyProcess.waitFor();
        return ttyProcess.exitValue() == 0;
      } catch (Exception e) {
        // fallback
      }
    }

    // On Windows, check if we have a console
    try {
      return System.console() != null;
    } catch (Exception e) {
      return false;
    }
  }

  @Override
  public void close() throws Exception {
    closed = true;
    if (sttyProcess != null && sttyProcess.isAlive()) {
      sttyProcess.destroyForcibly();
    }
  }

  private boolean isUnixLike() {
    String os = System.getProperty("os.name", "").toLowerCase();
    return os.contains("nix") || os.contains("nux") || os.contains("mac") || os.contains("darwin");
  }
}
