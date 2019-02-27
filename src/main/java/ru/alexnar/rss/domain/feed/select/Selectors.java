package ru.alexnar.rss.domain.feed.select;

import com.rometools.rome.feed.synd.SyndContent;
import com.rometools.rome.feed.synd.SyndEntry;
import ru.alexnar.rss.model.feed.FeedProperties;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static ru.alexnar.rss.model.feed.select.FeedElementFields.*;

public class Selectors {
  public static Map<String, FeedEntryFieldSelector> elementFieldSelectorMap() {
    Map<String, FeedEntryFieldSelector> entryFieldFeedSelectorMap = new HashMap<>();
    FeedEntryFieldSelector titleSelector = SyndEntry::getTitle;
    FeedEntryFieldSelector linkSelector = SyndEntry::getLink;
    FeedEntryFieldSelector descriptionSelector = (feed) -> {
      SyndContent description = feed.getDescription();
      if (description == null) return "";
      return description.getValue();
    };
    FeedEntryFieldSelector pubDateSelector = (feed) -> {
      Date publishedDate = feed.getPublishedDate();
      if (publishedDate == null) return "";
      return publishedDate.toString();
    };
    entryFieldFeedSelectorMap.put(TITLE.field(), titleSelector);
    entryFieldFeedSelectorMap.put(LINK.field(), linkSelector);
    entryFieldFeedSelectorMap.put(DESCRIPTION.field(), descriptionSelector);
    entryFieldFeedSelectorMap.put(PUB_DATE.field(), pubDateSelector);
    return entryFieldFeedSelectorMap;
  }
}
