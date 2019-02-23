package ru.alexnar.rss.domain.feed;

public class FeedProperties {
  public final String url;
  public int elementCount;
  public int period;
  public String outputFileName;

  public FeedProperties(String url, int elementCount, int period) {
    this.url = url;
    this.elementCount = elementCount;
    this.period = period;
    this.outputFileName = url;
  }

  public FeedProperties(String url, int elementCount, int period, String outputFileName) {
    this.url = url;
    this.elementCount = elementCount;
    this.period = period;
    this.outputFileName = outputFileName;
  }
}
