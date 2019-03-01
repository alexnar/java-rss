package ru.alexnar.rss.model.feed;

import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;

public class Feed {
  public final FeedProperties properties;
  public final URL url;
  public SyndFeed currentFeedData;
  public Date lastFetched;

  public Feed(FeedProperties properties) throws FeedException {
    this(properties, null);
  }

  public Feed(FeedProperties properties, SyndFeed currentFeedData) throws FeedException {
    this.properties = properties;
    this.currentFeedData = currentFeedData;
    this.url = url(properties.url);
    this.lastFetched = new Date();
  }

  private URL url(String urlStr) throws FeedException {
    URL url;
    try {
      url = new URL(urlStr);
    } catch (MalformedURLException e) {
      throw new FeedException("wrong url format", e);
    }
    return url;
  }
}
