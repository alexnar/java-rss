package ru.alexnar.rss.domain.feed.select;

import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndEntryImpl;
import com.rometools.rome.feed.synd.SyndFeed;
import ru.alexnar.rss.domain.feed.FeedProperties;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static ru.alexnar.rss.domain.feed.select.Selectors.elementFieldSelectorMap;

public class FeedEntriesSelector implements FeedFieldSelector {

  private final FeedProperties props;

  public FeedEntriesSelector(FeedProperties props) {
    this.props = props;
  }

  @Override
  public void selectField(SyndFeed sourceFeed, SyndFeed resultFeed) {
    List<SyndEntry> sourceEntries = sourceFeed.getEntries();
    List<SyndEntry> limitedEntries = sourceEntries.subList(0, props.elementCount);
    Map<String, FeedEntryFieldSelector> elementFieldSelectors = elementFieldSelectorMap();
    List<String> elementFields = props.selectFields.elementFields;
    if (elementFields.isEmpty()) return;
    List<FeedEntryFieldSelector> entrySelectors = elementFields.stream()
            .map(elementFieldSelectors::get)
            .collect(Collectors.toList());
    List<SyndEntry> selectedEntries = limitedEntries.stream()
            .map(entry -> copyEntry(entry, entrySelectors))
            .collect(Collectors.toList());
    resultFeed.setEntries(selectedEntries);
  }

  private SyndEntry copyEntry(SyndEntry sourceEntry, List<FeedEntryFieldSelector> feedEntryFieldSelectors) {
    SyndEntry resultEntry = new SyndEntryImpl();
    feedEntryFieldSelectors.forEach(selector -> selector.selectField(sourceEntry, resultEntry));
    return resultEntry;
  }
}
