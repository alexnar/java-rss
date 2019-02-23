package ru.alexnar.rss.domain.fetcher;

public class ScheduleFeedFetcher {
  private final String url;
  private final int period;
  private final String outputFileName;
  private final int elementCount;

  public ScheduleFeedFetcher(String url, int period, String outputFileName, int elementCount) {
    this.url = url;
    this.period = period;
    this.outputFileName = outputFileName;
    this.elementCount = elementCount;
  }

  public void start() {

  }

  public void edit() {

  }

  public void stop() {

  }
}
