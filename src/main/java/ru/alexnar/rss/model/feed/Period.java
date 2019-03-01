package ru.alexnar.rss.model.feed;

import java.util.concurrent.TimeUnit;

public class Period {
  public long duration;
  public TimeUnit unit;

  public Period() {
  }

  public Period(long duration, TimeUnit unit) {
    this.duration = duration;
    this.unit = unit;
  }

  @Override
  public String toString() {
    return duration + " " + unit.toString();
  }
}
