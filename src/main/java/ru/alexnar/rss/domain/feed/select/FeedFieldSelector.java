package ru.alexnar.rss.domain.feed.select;

import com.rometools.rome.feed.synd.SyndFeed;

public interface FeedFieldSelector {
  void selectField(SyndFeed sourceFeed, SyndFeed resultFeed);
}
