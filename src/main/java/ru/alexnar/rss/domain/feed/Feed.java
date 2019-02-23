package ru.alexnar.rss.domain.feed;

import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class Feed {
  private final FeedProperties feedFetchProperties;
  private final URL url;

  public Feed(FeedProperties properties) throws FeedException {
    this.feedFetchProperties = properties;
    this.url = url(properties.url);
  }

  public SyndFeed fetch() throws com.rometools.rome.io.FeedException {
    SyndFeedInput input = new SyndFeedInput();
    SyndFeed feed;
    try (XmlReader xmlReader = new XmlReader(url)) {
      feed = input.build(xmlReader);
    } catch (IOException e) {
      throw new com.rometools.rome.io.FeedException("Error creating xml reader", e);
    }
    return feed;
  }

  public void write() {
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
