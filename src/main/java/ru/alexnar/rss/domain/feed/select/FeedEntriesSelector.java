package ru.alexnar.rss.domain.feed.select;

import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndEntryImpl;
import com.rometools.rome.feed.synd.SyndFeed;
import ru.alexnar.rss.model.feed.FeedProperties;

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
    int selectElementCount = selectElementsCount(sourceFeed, props);
    List<SyndEntry> limitedEntries = sourceEntries.subList(0, selectElementCount);
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

  private int selectElementsCount(SyndFeed sourceFeed, FeedProperties props) {
    List<SyndEntry> entries = sourceFeed.getEntries();
    int selectElementCount = Math.min(props.elementCount, entries.size());
    return selectElementCount > 0 ? selectElementCount : 0;
  }

  private SyndEntry copyEntry(SyndEntry sourceEntry, List<FeedEntryFieldSelector> feedEntryFieldSelectors) {
    SyndEntry resultEntry = new SyndEntryImpl();
    feedEntryFieldSelectors.forEach(selector -> selector.selectField(sourceEntry, resultEntry));
    return resultEntry;
  }
}
