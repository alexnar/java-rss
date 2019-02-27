package ru.alexnar.rss.domain.feed.select;

import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndEntryImpl;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.feed.synd.SyndFeedImpl;
import ru.alexnar.rss.model.feed.Feed;
import ru.alexnar.rss.model.feed.FeedEntry;
import ru.alexnar.rss.model.feed.FeedProperties;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FeedSelect {
  private final Feed feed;


  public FeedSelect(Feed feed) {
    this.feed = feed;
  }

  public List<FeedEntry> select() {
    FeedProperties props = feed.properties;
    SyndFeed feedData = feed.currentFeedData;
    List<SyndEntry> entries = feedData.getEntries();
    List<SyndEntry> actualEntries = entries.stream()
            //.filter(entry -> entry.getPublishedDate().after(feed.lastFetched))
            .collect(Collectors.toList());
    int actualElementCount = selectElementsCount(feedData, props);
    actualEntries = actualEntries.subList(0, actualElementCount);
    return selectFields(actualEntries);
  }

  private int selectElementsCount(SyndFeed sourceFeed, FeedProperties props) {
    List<SyndEntry> entries = sourceFeed.getEntries();
    int selectElementCount = Math.min(props.elementCount, entries.size());
    return selectElementCount > 0 ? selectElementCount : 0;
  }

  private List<FeedEntry> selectFields(List<SyndEntry> actualEntries) {
    Map<String, FeedEntryFieldSelector> selectorMap = Selectors.elementFieldSelectorMap();
    Map<String, FeedEntryFieldSelector> actualSelectorMap = feed.properties.elementFields.stream()
            .collect(Collectors.toMap(field -> field, selectorMap::get));
    return actualEntries.stream()
            .map(entry -> new FeedEntry(entry, actualSelectorMap))
            .collect(Collectors.toList());
  }

}
