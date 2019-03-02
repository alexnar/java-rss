package ru.alexnar.rss.domain.config;

/**
 * Exception that signalize incorrect
 * configuration settings
 */
public class ConfigException extends RuntimeException {
  public ConfigException(String message) {
    super(message);
  }
}
