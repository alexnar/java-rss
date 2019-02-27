package ru.alexnar.rss.model.feed;

import java.util.concurrent.TimeUnit;

public class Period {
  public final long duration;
  public final TimeUnit unit;

  public Period(long duration, TimeUnit unit) {
    this.duration = duration;
    this.unit = unit;
  }
}
