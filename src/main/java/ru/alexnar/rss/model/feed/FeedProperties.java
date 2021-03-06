package ru.alexnar.rss.model.feed;

import ru.alexnar.rss.model.feed.select.FeedElementFields;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class FeedProperties {
  private static final int DEFAULT_ELEMENTS_COUNT = Integer.MAX_VALUE;
  private static final Period DEFAULT_PERIOD = new Period(20, TimeUnit.SECONDS);

  public String url;
  public int elementCount;
  public Period period;
  public String outputFileName;
  public List<String> elementFields;

  public FeedProperties() {

  }

  public FeedProperties(String url) {
    this(url, DEFAULT_ELEMENTS_COUNT, DEFAULT_PERIOD, allFields());
  }

  public FeedProperties(String url, int elementCount, Period period, List<String> elementFields) {
    this(url, elementCount, period, elementFields, null);
    outputFileName = fileNameFromUrl(url);
  }

  public FeedProperties(String url, int elementCount, Period period, List<String> elementFields,
                        String outputFileName) {
    this.url = url;
    this.elementCount = elementCount;
    this.period = period;
    this.elementFields = elementFields;
    this.outputFileName = outputFileName;
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
