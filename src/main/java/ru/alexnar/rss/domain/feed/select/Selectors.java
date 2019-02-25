package ru.alexnar.rss.domain.feed.select;

import ru.alexnar.rss.domain.feed.FeedProperties;

import java.util.HashMap;
import java.util.Map;

public class Selectors {
  public static Map<String, FeedFieldSelector> paramSelectorMap(FeedProperties props) {
    Map<String, FeedFieldSelector> selectMap = new HashMap<>();
    FeedFieldSelector authorSelector = (source, result) -> result.setAuthor(source.getAuthor());
    FeedFieldSelector languageSelector = (source, result) -> result.setLanguage(source.getLanguage());
    FeedFieldSelector entriesSelector = new FeedEntriesSelector(props);
    selectMap.put("author",  authorSelector);
    selectMap.put("language",  languageSelector);
    selectMap.put("element",  entriesSelector);
    return selectMap;
  }

  public static Map<String, FeedEntryFieldSelector> elementFieldSelectorMap() {
    Map<String, FeedEntryFieldSelector> entryFieldFeedSelectorMap = new HashMap<>();
    FeedEntryFieldSelector titleSelector = (source, result) -> result.setTitle(source.getTitle());
    FeedEntryFieldSelector linkSelector = (source, result) -> result.setLink(source.getLink());
    FeedEntryFieldSelector descriptionSelector = (source, result) -> result.setDescription(source.getDescription());
    FeedEntryFieldSelector pubDateSelector = (source, result) -> result.setPublishedDate(source.getPublishedDate());
    entryFieldFeedSelectorMap.put("title", titleSelector);
    entryFieldFeedSelectorMap.put("link", linkSelector);
    entryFieldFeedSelectorMap.put("description", descriptionSelector);
    entryFieldFeedSelectorMap.put("pubDate", pubDateSelector);
    return entryFieldFeedSelectorMap;
  }
}
