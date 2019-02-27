package ru.alexnar.rss.model.feed;

import com.rometools.rome.io.FeedException;

import java.net.MalformedURLException;
import java.net.URL;

public class Feed {
  public final FeedProperties properties;
  public final URL url;

  public Feed(FeedProperties properties) throws FeedException {
    this.properties = properties;
    this.url = url(properties.url);
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
