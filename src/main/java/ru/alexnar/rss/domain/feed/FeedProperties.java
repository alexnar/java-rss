package ru.alexnar.rss.domain.feed;

public class FeedProperties {
  public final String url;
  public final int elementCount;
  public final Period period;
  public final String outputFileName;

  public FeedProperties(String url, int elementCount, Period period) {
    this.url = url;
    this.elementCount = elementCount;
    this.period = period;
    this.outputFileName = url;
  }

  public FeedProperties(String url, int elementCount, Period period, String outputFileName) {
    this.url = url;
    this.elementCount = elementCount;
    this.period = period;
    this.outputFileName = outputFileName;
  }
}
