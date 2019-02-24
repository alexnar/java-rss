package ru.alexnar.rss.domain.feed;

public class FeedProperties {
  public final String url;
  public final int elementCount;
  public final Period period;
  public final SelectFields selectFields;
  public String outputFileName;

  public FeedProperties(String url, int elementCount, Period period, SelectFields selectFields) {
    this.url = url;
    this.elementCount = elementCount;
    this.period = period;
    this.outputFileName = url;
    this.selectFields = selectFields;
  }

  public void setOutputFileName(String outputFileName) {
    this.outputFileName = outputFileName;
  }
}
