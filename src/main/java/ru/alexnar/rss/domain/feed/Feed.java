package ru.alexnar.rss.domain.feed;

import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.feed.synd.SyndFeedImpl;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.SyndFeedOutput;
import com.rometools.rome.io.XmlReader;
import org.xml.sax.InputSource;
import ru.alexnar.rss.domain.feed.select.FeedSelect;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
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
    SyndFeed feed = fetch();
    SyndFeed selectFeed = select(feed);
    write(selectFeed);
  }

  private SyndFeed fetch() {
    SyndFeedInput input = new SyndFeedInput();

    SyndFeed feed = null;
    try (InputStream inputStream = url.openStream()) {
      InputSource inputSource = new InputSource(inputStream);
      feed = input.build(inputSource);
    } catch (IOException e) {
      System.out.println("Creating xml reader error");
    } catch (FeedException e) {
      System.out.println("Fetching feed error");
    }
    return feed;
  }

  private SyndFeed select(SyndFeed feed) {
    FeedSelect feedSelect = new FeedSelect(feed, properties);
    return feedSelect.select();
  }

  private void write(SyndFeed feed) {
    SyndFeedOutput feedOutput = new SyndFeedOutput();
    try {
      String fileName = properties.outputFileName;
      File outputFile = new File(fileName);
      feedOutput.output(feed, outputFile);
    } catch (IOException e) {
      System.out.println("Error creating file");
    } catch (FeedException e) {
      System.out.println("Incorrect feed!");
    }
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
