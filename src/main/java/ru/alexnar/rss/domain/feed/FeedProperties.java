package ru.alexnar.rss.domain.feed;

import ru.alexnar.rss.domain.feed.select.FeedFields;
import ru.alexnar.rss.domain.feed.select.SelectFields;

import java.util.concurrent.TimeUnit;

public class FeedProperties {
  private static final int DEFAULT_ELEMENTS_COUNT = Integer.MAX_VALUE;
  private static final Period DEFAULT_PERIOD = new Period(20, TimeUnit.SECONDS);

  public final String url;
  public int elementCount;
  public Period period;
  public SelectFields selectFields;
  public String outputFileName;


  public FeedProperties(String url) {
    this(url, DEFAULT_ELEMENTS_COUNT, DEFAULT_PERIOD, allFields());
  }

  public FeedProperties(String url, int elementCount, Period period, SelectFields selectFields) {
    this.url = url;
    this.elementCount = elementCount;
    this.period = period;
    this.outputFileName = fileNameFromUrl(url);
    this.selectFields = selectFields;
  }

  public void setOutputFileName(String outputFileName) {
    this.outputFileName = outputFileName;
  }

  private String fileNameFromUrl(String url) {
    return url.replaceAll("/", "_");
  }

  private static SelectFields allFields() {
    return new SelectFields(FeedFields.fields());
  }
}
