package ru.alexnar.rss.domain.feed;

import com.rometools.rome.io.FeedException;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class FeedManager {
  private static final int THREAD_POOL_SIZE = 4;
  private static final int SCHEDULE_INITIAL_DELAY = 0;

  private Map<String, FeedSchedule> feedSchedules;
  private ScheduledExecutorService scheduledExecutorService;

  public FeedManager() {
    this(THREAD_POOL_SIZE);
  }

  public FeedManager(int threadPoolSize) {
    feedSchedules = new HashMap<>();
    scheduledExecutorService = Executors.newScheduledThreadPool(threadPoolSize);
  }

  public void add(FeedProperties prop) {
    Feed feed = createFeed(prop);
    if (feed == null) return;
    long duration = prop.period.duration;
    TimeUnit unit = prop.period.unit;
    ScheduledFuture<?> scheduledFuture = scheduledExecutorService
            .scheduleAtFixedRate(feed, SCHEDULE_INITIAL_DELAY, duration, unit);
    FeedSchedule feedSchedule = new FeedSchedule(feed, scheduledFuture);
    feedSchedules.put(prop.url, feedSchedule);
  }

  public void edit(String url, FeedProperties newFeedProperties) {

  }

  public Map<String, FeedSchedule> getFeedSchedules() {
    return feedSchedules;
  }

  private void remove(String url) {

  }

  private Feed createFeed(FeedProperties properties) {
    Feed feed;
    try {
      feed = new Feed(properties);
    } catch (FeedException e) {
      System.out.println("feed creation error: " + e.getMessage());
      return null;
    }
    return feed;
  }

  public static void main(String[] args) {
    FeedManager feedManager = new FeedManager();
    FeedProperties props = new FeedProperties("https://habr.com/ru/rss/post/335382/", 5,
            new Period(10, TimeUnit.SECONDS), null);
    feedManager.add(props);
    feedManager.add(new FeedProperties("https://habr.com/ru/rss/post/335383/", 5, new Period(10, TimeUnit.SECONDS), null));
    System.out.println();
  }
}
