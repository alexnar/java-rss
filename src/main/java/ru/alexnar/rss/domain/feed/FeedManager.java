package ru.alexnar.rss.domain.feed;

import com.rometools.rome.io.FeedException;
import ru.alexnar.rss.domain.config.RssConfigAccessor;
import ru.alexnar.rss.model.config.RssConfig;
import ru.alexnar.rss.model.feed.Feed;
import ru.alexnar.rss.model.feed.FeedProperties;
import ru.alexnar.rss.model.feed.FeedSchedule;

import java.util.Date;
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

  private RssConfig rssConfig;

  public FeedManager() {
    this.rssConfig = RssConfigAccessor.getInstance();
  }

  public void add(FeedProperties prop) {
    Feed feed = createFeed(prop);
    FeedProcessor feedProcessor = new FeedProcessor(feed);
    if (feed == null) return;
    long duration = prop.period.duration;
    TimeUnit unit = prop.period.unit;
    ScheduledFuture<?> scheduledFuture = rssConfig.scheduledExecutorService
            .scheduleAtFixedRate(feedProcessor, SCHEDULE_INITIAL_DELAY, duration, unit);
    FeedSchedule feedSchedule = new FeedSchedule(feed, scheduledFuture);
    rssConfig.feedSchedules.put(prop.url, feedSchedule);
  }

  public void edit(String url, FeedProperties newFeedProperties) {

  }

  public Map<String, FeedSchedule> getFeedSchedules() {
    return rssConfig.feedSchedules;
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
