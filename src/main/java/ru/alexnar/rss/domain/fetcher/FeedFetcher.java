package ru.alexnar.rss.domain.fetcher;

import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class FeedFetcher {
  private final URL url;
  private final int elementCount;

  public FeedFetcher(String urlStr, int elementCount) throws FeedFetcherException {
    this.url = url(urlStr);
    this.elementCount = elementCount;
  }

  public SyndFeed fetch() throws FeedException {
    SyndFeedInput input = new SyndFeedInput();
    SyndFeed feed;
    try (XmlReader xmlReader = new XmlReader(url)) {
      feed = input.build(xmlReader);
    } catch (IOException e) {
      throw new FeedException("Error creating xml reader", e);
    }
    return feed;
  }

  private URL url(String urlStr) throws FeedFetcherException {
    URL url;
    try {
      url = new URL(urlStr);
    } catch (MalformedURLException e) {
      throw new FeedFetcherException("wrong url format", e);
    }
    return url;
  }
}
