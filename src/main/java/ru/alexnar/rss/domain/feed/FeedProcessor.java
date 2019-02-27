package ru.alexnar.rss.domain.feed;

import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;
import org.xml.sax.InputSource;
import ru.alexnar.rss.domain.feed.select.FeedSelect;
import ru.alexnar.rss.model.feed.Feed;
import ru.alexnar.rss.model.feed.FeedEntry;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class FeedProcessor implements Runnable {
  private Feed feed;

  public FeedProcessor(Feed feed) {
    this.feed = feed;
  }

  @Override
  public void run() {
    fetch();
    List<FeedEntry> entries = select(feed);
    write(entries);
  }

  private void fetch() {
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
    feed.currentFeedData = syndFeed;
  }

  private List<FeedEntry> select(Feed feed) {
    FeedSelect feedSelect = new FeedSelect(feed);
    return feedSelect.select();
  }

  private void write(List<FeedEntry> entries) {
    FeedWriter feedWriter = new FeedWriter();
    feedWriter.write(feed, entries);
  }
}
