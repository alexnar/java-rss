package ru.alexnar.rss.domain.feed;

import com.rometools.rome.io.FeedException;
import org.junit.Test;
import ru.alexnar.rss.model.feed.Feed;
import ru.alexnar.rss.model.feed.FeedProperties;
import ru.alexnar.rss.model.feed.Period;

import java.util.concurrent.TimeUnit;

public class FeedTest {
  private static final Period DEFAULT_PERIOD = new Period(10, TimeUnit.SECONDS);

  @Test(expected = FeedException.class)
  public void rssFetcherNullUrlThrowsException() throws FeedException {
    FeedProperties feedProperties = new FeedProperties(null, 1, DEFAULT_PERIOD, null);
    Feed rssFetcher = new Feed(feedProperties);
  }

  @Test(expected = FeedException.class)
  public void rssFetcherEmptyUrlThrowsException() throws FeedException {
    FeedProperties feedProperties = new FeedProperties("", 1, DEFAULT_PERIOD, null);
    Feed rssFetcher = new Feed(feedProperties);
  }

  @Test(expected = FeedException.class)
  public void rssFetcherIncorrectUrlThrowsException() throws FeedException {
    FeedProperties feedProperties = new FeedProperties("asf://google.com/", 1, DEFAULT_PERIOD, null);
    Feed rssFetcher = new Feed(feedProperties);
  }
}