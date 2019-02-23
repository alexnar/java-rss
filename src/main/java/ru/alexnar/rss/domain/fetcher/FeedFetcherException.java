package ru.alexnar.rss.domain.fetcher;

public class FeedFetcherException extends Exception {
  public FeedFetcherException(String message) {
    super(message);
  }

  public FeedFetcherException(String message, Throwable cause) {
    super(message, cause);
  }
}
