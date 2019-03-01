package ru.alexnar.rss.controller.commands;

import ru.alexnar.rss.domain.feed.FeedManager;

import java.util.List;

import static ru.alexnar.rss.controller.console.ConsoleUtils.checkManageFeedArgs;

public class RemoveFeedCommand implements Command {
  @Override
  public void execute(List<String> args) {
    if (checkManageFeedArgs(args)) {
      System.out.println("incorrect url specified");
      return;
    }
    String url = args.get(0);
    FeedManager feedManager = FeedManager.getInstance();
    feedManager.removeFeed(url);
    System.out.println("Feed removed");
  }

  @Override
  public String alias() {
    return "removeFeed";
  }

  @Override
  public String description() {
    return "Usage: removeFeed <url>";
  }
}
