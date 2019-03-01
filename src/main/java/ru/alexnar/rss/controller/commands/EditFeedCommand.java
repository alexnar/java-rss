package ru.alexnar.rss.controller.commands;

import ru.alexnar.rss.controller.console.ConsoleConfig;
import ru.alexnar.rss.controller.console.ConsoleUtils;
import ru.alexnar.rss.domain.config.RssConfigAccessor;
import ru.alexnar.rss.domain.feed.FeedManager;
import ru.alexnar.rss.model.config.RssConfig;
import ru.alexnar.rss.model.feed.Feed;
import ru.alexnar.rss.model.feed.FeedProperties;
import ru.alexnar.rss.model.feed.FeedSchedule;
import ru.alexnar.rss.model.feed.Period;

import java.util.List;
import java.util.Scanner;
import java.util.function.Function;
import java.util.function.Supplier;

import static ru.alexnar.rss.controller.console.ConsoleUtils.*;
import static ru.alexnar.rss.controller.console.ConsoleUtils.readElementFields;

public class EditFeedCommand implements Command {
  private FeedManager feedManager = FeedManager.getInstance();

  @Override
  public void execute(List<String> args) {
    if (checkManageFeedArgs(args)) {
      System.out.println("incorrect url specified");
      return;
    }
    String url = args.get(0);
    RssConfig rssConfig = RssConfigAccessor.getInstance();
    FeedSchedule feedSchedule = rssConfig.feedSchedules.get(url);
    Feed feed = feedSchedule.feed;
    FeedProperties props = feed.properties;
    Period period =
            proposeToChange(ConsoleUtils::readPeriod, props.period, "period");
    int elementCount =
            proposeToChange(ConsoleUtils::readElementCount, props.elementCount, "elementCount");
    String outputFileName =
            proposeToChange(ConsoleUtils::readOutputFileName, props.outputFileName, "outputFileName");
    List<String> elementFields =
            proposeToChange(ConsoleUtils::readElementFields, props.elementFields, "fields");
    FeedProperties newProps =
            new FeedProperties(url, elementCount, period, elementFields, outputFileName);
    feedManager.edit(url, newProps);
    System.out.println("Edit done.");
  }

  @Override
  public String alias() {
    return "editFeed";
  }

  @Override
  public String description() {
    return "Usage: editFeed <url>";
  }

  private <T> T proposeToChange(Supplier<T> supplier, T currentPropertyValue,
                                String changedPropertyId) {
    System.out.println("Current value of " + changedPropertyId + ": " + currentPropertyValue);
    System.out.println("Change property [yes, no]: ");
    Scanner scanner = ConsoleConfig.scanner;
    String decision = scanner.nextLine();
    if (!"yes".equals(decision)) return currentPropertyValue;
    return supplier.get();
  }
}
