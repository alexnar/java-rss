package ru.alexnar.rss.domain.fetcher;

import org.junit.Test;

public class FeedFetcherTest {
  @Test(expected = FeedFetcherException.class)
  public void rssFetcherNullUrlThrowsException() throws FeedFetcherException {
    FeedFetcher rssFetcher = new FeedFetcher(null, 5);
  }

  @Test(expected = FeedFetcherException.class)
  public void rssFetcherEmptyUrlThrowsException() throws FeedFetcherException {
    FeedFetcher rssFetcher = new FeedFetcher("", 5);
  }

  @Test(expected = FeedFetcherException.class)
  public void rssFetcherIncorrectUrlThrowsException() throws FeedFetcherException {
    FeedFetcher rssFetcher = new FeedFetcher("asf://google.com/", 5);
  }
}