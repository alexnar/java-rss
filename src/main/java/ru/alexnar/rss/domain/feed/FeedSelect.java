package ru.alexnar.rss.domain.feed;

import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.feed.synd.SyndFeedImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class FeedSelect {
  public final SyndFeed feed;
  public final SyndFeed feedSelect;
  private final FeedProperties props;

  public FeedSelect(SyndFeed feed, FeedProperties props) {
    this.feed = feed;
    this.feedSelect = initFeedSelect();
    this.props = props;
  }

  public SyndFeed select(List<String> params) {
    // author, language, element_
    return null;
  }

  private Map<String, Supplier<SyndFeed>> paramSelectMap() {
    Map<String, Supplier<SyndFeed>> selectMap = new HashMap<>();
    Supplier<SyndFeed> authorSelector = () -> {
      this.feedSelect.setAuthor(feed.getAuthor());
      return feedSelect;
    };
    Supplier<SyndFeed> languageSelector = () -> {
      this.feedSelect.setLanguage(feed.getLanguage());
      return feedSelect;
    };
    Supplier<SyndFeed> itemSelector = () -> {
      this.feedSelect.setAuthor(feed.getAuthor());
      return feedSelect;
    };
    selectMap.put("author",  authorSelector);
    return null;
  }

  private Supplier<SyndFeed> itemSelector() {
    return () -> {
      List<SyndEntry> entries = feed.getEntries();
      List<SyndEntry> subEntries = entries.subList(0, props.elementCount);


      // feedSelect.setEntries(selectedEntries);
      return feedSelect;
    };
  }

  private SyndFeed initFeedSelect() {
    SyndFeedImpl feedSelect = new SyndFeedImpl();
    feedSelect.setFeedType(feed.getFeedType());
    feedSelect.setTitle(feed.getTitle());
    feedSelect.setLink(feed.getLink());
    feedSelect.setDescription(feed.getDescription());
    return feedSelect;
  }
}
