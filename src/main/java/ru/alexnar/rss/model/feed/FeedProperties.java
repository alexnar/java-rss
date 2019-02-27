package ru.alexnar.rss.model.feed;

import ru.alexnar.rss.model.feed.select.FeedElementFields;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class FeedProperties {
  private static final int DEFAULT_ELEMENTS_COUNT = Integer.MAX_VALUE;
  private static final Period DEFAULT_PERIOD = new Period(20, TimeUnit.SECONDS);

  public final String url;
  public int elementCount;
  public Period period;
  public String outputFileName;
  public final List<String> elementFields;


  public FeedProperties(String url) {
    this(url, DEFAULT_ELEMENTS_COUNT, DEFAULT_PERIOD, allFields());
  }

  public FeedProperties(String url, int elementCount, Period period, List<String> elementFields) {
    this.url = url;
    this.elementCount = elementCount;
    this.period = period;
    this.outputFileName = fileNameFromUrl(url);
    this.elementFields = elementFields;
  }

  public void setOutputFileName(String outputFileName) {
    this.outputFileName = outputFileName;
  }

  private String fileNameFromUrl(String url) {
    if (url == null) return null;
    return url.replaceAll("/", "_");
  }

  private static List<String> allFields() {
    return FeedElementFields.fields();
  }
}
