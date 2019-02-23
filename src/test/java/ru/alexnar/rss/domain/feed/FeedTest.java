package ru.alexnar.rss.domain.feed;

import org.junit.Test;

public class FeedTest {
  @Test(expected = FeedException.class)
  public void rssFetcherNullUrlThrowsException() throws FeedException {
    FeedProperties feedProperties = new FeedProperties(null, 1, 1);
    Feed rssFetcher = new Feed(feedProperties);
  }

  @Test(expected = FeedException.class)
  public void rssFetcherEmptyUrlThrowsException() throws FeedException {
    FeedProperties feedProperties = new FeedProperties("", 1, 1);
    Feed rssFetcher = new Feed(feedProperties);
  }

  @Test(expected = FeedException.class)
  public void rssFetcherIncorrectUrlThrowsException() throws FeedException {
    FeedProperties feedProperties = new FeedProperties("asf://google.com/", 1, 1);
    Feed rssFetcher = new Feed(feedProperties);
  }
}