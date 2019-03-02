package ru.alexnar.rss.model.console;

/**
 * Signalize that wrong command line was passed
 */
public class CommandLineParseException extends Exception {
  public CommandLineParseException(String message) {
    super(message);
  }
}
