package ru.alexnar.rss.controller.commands;

import ru.alexnar.rss.domain.feed.FeedManager;
import ru.alexnar.rss.model.feed.FeedProperties;
import ru.alexnar.rss.model.feed.Period;

import java.util.List;

import static ru.alexnar.rss.controller.console.ConsoleUtils.*;

public class AddFeedCommand implements Command {
  @Override
  public void execute(List<String> args) {
    if (checkManageFeedArgs(args)) {
      System.out.println("incorrect url specified");
      return;
    }
    String url = args.get(0);
    Period period = readPeriod();
    int count = readElementCount();
    String fileName = readOutputFileName();
    List<String> fields = readElementFields();
    FeedProperties feedProperties = new FeedProperties(url, count, period, fields, fileName);
    FeedManager feedManager = FeedManager.getInstance();
    feedManager.addFeed(feedProperties);
    System.out.println("Feed added.");
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
