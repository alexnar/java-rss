package ru.alexnar.rss.controller.console;

public class CommandLineParseException extends Exception {
  public CommandLineParseException(String message) {
    super(message);
  }

  public CommandLineParseException(String message, Throwable cause) {
    super(message, cause);
  }
}