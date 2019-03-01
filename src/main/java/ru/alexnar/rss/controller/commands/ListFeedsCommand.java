package ru.alexnar.rss.controller.commands;

import ru.alexnar.rss.domain.config.RssConfigAccessor;
import ru.alexnar.rss.model.config.RssConfig;
import ru.alexnar.rss.model.feed.FeedProperties;

import java.util.List;

public class ListFeedsCommand implements Command {
  @Override
  public void execute(List<String> args) {
    RssConfig rssConfig = RssConfigAccessor.getInstance();
    rssConfig.feedProperties
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

  private void printFeed(FeedProperties feedProperties) {
    String url = feedProperties.url;
    long duration = feedProperties.period.duration;
    String unitStr = feedProperties.period.unit.toString();
    System.out.println("Fetch every " + duration + " " + unitStr + " url: " + url);
  }
}
