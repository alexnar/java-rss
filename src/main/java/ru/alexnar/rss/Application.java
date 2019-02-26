package ru.alexnar.rss;

import ru.alexnar.rss.controller.ConsoleMenu;
import ru.alexnar.rss.controller.Menu;
import ru.alexnar.rss.domain.feed.FeedManager;
import ru.alexnar.rss.domain.feed.FeedProperties;
import ru.alexnar.rss.domain.feed.Period;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class Application {
  private void run() {
    Menu menu = new ConsoleMenu();
    menu.start();
  }

  public static void main(String[] args) {
    Application application = new Application();
    application.run();
  }
}
