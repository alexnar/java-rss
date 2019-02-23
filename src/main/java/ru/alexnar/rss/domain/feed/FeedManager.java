package ru.alexnar.rss.domain.feed;

import com.rometools.rome.io.FeedException;

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
    scheduledExecutorService = Executors.newScheduledThreadPool(threadPoolSize);
  }

  private void add(FeedProperties prop) throws FeedException {
    Feed feed = new Feed(prop);
    long duration = prop.period.duration;
    TimeUnit unit = prop.period.unit;
    ScheduledFuture<?> scheduledFuture = scheduledExecutorService
            .scheduleAtFixedRate(feed, SCHEDULE_INITIAL_DELAY, duration, unit);
    FeedSchedule feedSchedule = new FeedSchedule(feed, scheduledFuture);
    feedSchedules.put(prop.url, feedSchedule);
  }

  public void edit(String url, FeedProperties newFeedProperties) {

  }

  private void remove(String url) {

  }
}
