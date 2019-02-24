package ru.alexnar.rss.domain.feed;

import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class Feed implements Runnable {
  private final FeedProperties properties;
  private final URL url;

  public Feed(FeedProperties properties) throws FeedException {
    this.properties = properties;
    this.url = url(properties.url);
  }

  @Override
  public void run() {
    SyndFeed feed = null;
    try {
      feed = fetch();
      write(feed);
    } catch (FeedException e) {
      System.out.println("Feed error");
    }
  }

  private SyndFeed fetch() throws FeedException {
    SyndFeedInput input = new SyndFeedInput();
    SyndFeed feed;
    try (XmlReader xmlReader = new XmlReader(url)) {
      feed = input.build(xmlReader);
    } catch (IOException e) {
      throw new FeedException("Error creating xml reader", e);
    }
    return feed;
  }

  private void write(SyndFeed feed) {
    // write to file
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
