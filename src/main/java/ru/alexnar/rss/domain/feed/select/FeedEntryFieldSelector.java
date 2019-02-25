package ru.alexnar.rss.domain.feed.select;

import com.rometools.rome.feed.synd.SyndEntry;

public interface FeedEntryFieldSelector {
  void selectField(SyndEntry sourceEntry, SyndEntry resultEntry);
}
