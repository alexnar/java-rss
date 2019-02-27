package ru.alexnar.rss.domain.feed;

import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.SyndFeedOutput;
import org.xml.sax.InputSource;
import ru.alexnar.rss.domain.feed.select.FeedSelect;
import ru.alexnar.rss.model.feed.Feed;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class FeedProcessor implements Runnable {
  private Feed feed;

  public FeedProcessor(Feed feed) {
    this.feed = feed;
  }

  @Override
  public void run() {
    SyndFeed feed = fetch();
    SyndFeed selectFeed = select(feed);
    write(selectFeed);
  }

  private SyndFeed fetch() {
    SyndFeedInput input = new SyndFeedInput();

    SyndFeed syndFeed = null;
    try (InputStream inputStream = feed.url.openStream()) {
      InputSource inputSource = new InputSource(inputStream);
      syndFeed = input.build(inputSource);
    } catch (IOException e) {
      System.out.println("Creating xml reader error");
    } catch (FeedException e) {
      System.out.println("Fetching feed error");
    }
    return syndFeed;
  }

  private SyndFeed select(SyndFeed syndFeed) {
    FeedSelect feedSelect = new FeedSelect(syndFeed, feed.properties);
    return feedSelect.select();
  }

  private void write(SyndFeed syndFeed) {
    FeedWriter feedWriter = new FeedWriter();
    feedWriter.write(feed, syndFeed);
  }
}