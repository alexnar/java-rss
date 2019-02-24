package ru.alexnar.rss;

import ru.alexnar.rss.domain.feed.FeedManager;
import ru.alexnar.rss.domain.feed.FeedProperties;
import ru.alexnar.rss.domain.feed.Period;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class Application {
  private void run() {
    FeedManager feedManager = new FeedManager();
    Period period = new Period(10,TimeUnit.SECONDS);
    FeedProperties feedProperties = new FeedProperties("https://habr.com/ru/rss/post/335382/", 10, period, null);
    feedManager.add(feedProperties);
  }

  public static void main(String[] args) {
    Application application = new Application();
    application.run();
  }
}
