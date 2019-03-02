package ru.alexnar.rss.model.config;

import com.rometools.rome.io.FeedException;
import ru.alexnar.rss.domain.config.ConfigException;
import ru.alexnar.rss.domain.feed.FeedProcessor;
import ru.alexnar.rss.model.feed.Feed;
import ru.alexnar.rss.model.feed.FeedProperties;
import ru.alexnar.rss.model.feed.FeedSchedule;

import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * General application config
 */
public class RssConfig {
  private static final int SCHEDULE_INITIAL_DELAY = 0;

  public int threadPoolSize;
  public List<FeedProperties> feedProperties;
  public Map<String, FeedSchedule> feedSchedules;
  public ScheduledExecutorService scheduledExecutorService;

  public RssConfig() {
    feedSchedules = new HashMap<>();
  }

  public void setup() {
    scheduledExecutorService = Executors.newScheduledThreadPool(threadPoolSize);
    for (FeedProperties prop : feedProperties) {
      FeedSchedule feedSchedule = null;
      try {
        feedSchedule = createFeedSchedule(prop);
      } catch (FeedException e) {
        throw new ConfigException("wrong feed properties in config file");
      }
      feedSchedules.put(prop.url, feedSchedule);
    }
  }

  private FeedSchedule createFeedSchedule(FeedProperties props) throws FeedException {
    Feed feed = new Feed(props);
    FeedProcessor feedProcessor = new FeedProcessor(feed);
    long duration = props.period.duration;
    TimeUnit unit = props.period.unit;
    ScheduledFuture<?> scheduledFuture = scheduledExecutorService
            .scheduleAtFixedRate(feedProcessor, SCHEDULE_INITIAL_DELAY, duration, unit);
    return new FeedSchedule(feed, scheduledFuture);
  }


}
