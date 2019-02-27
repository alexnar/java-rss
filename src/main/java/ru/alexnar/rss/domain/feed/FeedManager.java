package ru.alexnar.rss.domain.feed;

import com.rometools.rome.io.FeedException;
import ru.alexnar.rss.model.feed.select.SelectFields;
import ru.alexnar.rss.model.feed.Feed;
import ru.alexnar.rss.model.feed.FeedProperties;
import ru.alexnar.rss.model.feed.FeedSchedule;
import ru.alexnar.rss.model.feed.Period;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class FeedManager {
  private static final int THREAD_POOL_SIZE = 4;
  private static final int SCHEDULE_INITIAL_DELAY = 0;

  private static FeedManager instance = new FeedManager();

  private Map<String, FeedSchedule> feedSchedules;
  private ScheduledExecutorService scheduledExecutorService;

  private FeedManager() {
    this(THREAD_POOL_SIZE);
  }

  private FeedManager(int threadPoolSize) {
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

  public static FeedManager getInstance() {
    return instance;
  }

  private void remove(String url) {

  }

  private Feed createFeed(FeedProperties properties) {
    Feed feed;
    try {
      feed = new Feed(properties);
    } catch (FeedException e) {
      System.out.println("sourceFeed creation error: " + e.getMessage());
      return null;
    }
    return feed;
  }
}
