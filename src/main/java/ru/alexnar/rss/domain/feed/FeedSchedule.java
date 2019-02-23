package ru.alexnar.rss.domain.feed;

import java.util.concurrent.ScheduledFuture;

public class FeedSchedule {
  public final Feed feed;
  public final ScheduledFuture scheduledFuture;

  public FeedSchedule(Feed feed, ScheduledFuture scheduledFuture) {
    this.feed = feed;
    this.scheduledFuture = scheduledFuture;
  }
}
