package ru.alexnar.rss.controller.commands;

import ru.alexnar.rss.controller.console.ConsoleConfig;
import ru.alexnar.rss.controller.console.WrongInputFormatException;
import ru.alexnar.rss.domain.feed.FeedManager;
import ru.alexnar.rss.model.feed.FeedProperties;
import ru.alexnar.rss.model.feed.Period;

import java.util.List;
import java.util.Scanner;

import static ru.alexnar.rss.controller.console.ConsoleUtils.*;

public class AddFeedCommand implements Command {
  @Override
  public void execute(List<String> args) {
    if (checkArgs(args)) {
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
    feedManager.add(feedProperties);
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


  private boolean checkArgs(List<String> args) {
    return args == null || args.isEmpty() || args.get(0) == null || args.get(0).isEmpty();
  }

  
}
