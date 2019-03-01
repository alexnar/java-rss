package ru.alexnar.rss.controller.commands;

import ru.alexnar.rss.domain.config.RssConfigAccessor;
import ru.alexnar.rss.model.config.RssConfig;
import ru.alexnar.rss.model.feed.Feed;
import ru.alexnar.rss.model.feed.FeedProperties;

import java.util.List;

public class ListFeedsCommand implements Command {
  @Override
  public void execute(List<String> args) {
    RssConfig rssConfig = RssConfigAccessor.getInstance();
    rssConfig.feedSchedules.entrySet().stream()
            .map(entry -> entry.getValue().feed)
            .forEach(this::printFeed);
  }

  @Override
  public String alias() {
    return "listFeeds";
  }

  @Override
  public String description() {
    return "list all feeds";
  }

  private void printFeed(Feed feed) {
    FeedProperties props = feed.properties;
    String url = props.url;
    long duration = props.period.duration;
    String unitStr = props.period.unit.toString();
    String feedInfo = "Fetch every " + duration + " " + unitStr + " url: " + url;
    String lastFetchInfo = " (Last fetched: " + feed.lastFetched + ")";
    System.out.println(feedInfo + lastFetchInfo);
  }
}
