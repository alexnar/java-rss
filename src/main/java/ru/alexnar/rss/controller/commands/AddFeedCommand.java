package ru.alexnar.rss.controller.commands;

import ru.alexnar.rss.domain.feed.FeedManager;
import ru.alexnar.rss.model.feed.FeedProperties;

import java.util.List;

public class AddFeedCommand implements Command {
  @Override
  public void execute(List<String> args) {
    String url = args.get(0);
    if (url == null) {
      System.out.println("no url specified");
      return;
    }
    FeedProperties feedProperties = new FeedProperties(url);
    FeedManager feedManager = FeedManager.getInstance();
    feedManager.add(feedProperties);
  }

  @Override
  public String alias() {
    return "addFeed";
  }

  @Override
  public String description() {
    return "Usage: addFeed <url>";
  }
}
