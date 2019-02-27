package ru.alexnar.rss.model.feed;

import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import ru.alexnar.rss.domain.feed.select.FeedEntryFieldSelector;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FeedEntry {
  private final Map<String, String> fieldMap;

  public FeedEntry(SyndEntry syndEntry, Map<String, FeedEntryFieldSelector> selectors) {
    fieldMap = new HashMap<>();
    selectors.forEach((field, selector) -> fieldMap.put(field, selector.selectField(syndEntry)));
  }

  public String get(String key) {
    return fieldMap.get(key);
  }

  @Override
  public String toString() {
    return fieldMap.entrySet().stream()
            .map(entry -> entry.getKey() + ": " + entry.getValue() + "\n")
            .collect(Collectors.joining());
  }
}
