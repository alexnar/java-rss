package ru.alexnar.rss;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class Application {
  private void run() {
    ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
    ScheduledFuture<?> scheduledFuture = scheduledExecutorService.scheduleAtFixedRate(
            () -> System.out.println("Hello"), 0, 10, TimeUnit.SECONDS);
    System.out.println("111");
  }

  public static void main(String[] args) {
    Application application = new Application();
    application.run();
  }
}
