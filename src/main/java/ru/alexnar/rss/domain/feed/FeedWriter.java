package ru.alexnar.rss.domain.feed;

import ru.alexnar.rss.model.feed.Feed;
import ru.alexnar.rss.model.feed.FeedEntry;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.stream.Collectors;

public class FeedWriter {
  private final String outputDirPath;

  public FeedWriter(String outputDirPath) {
    this.outputDirPath = outputDirPath;
  }

  public void write(Feed feed, List<FeedEntry> feedEntries) {
    if (feedEntries == null || feedEntries.isEmpty()) return;
    if (!outputDirExists()) createOutputDir();
    String outputFileName = outputDirPath + feed.properties.outputFileName;
    File file = outputFile(outputFileName);
    String feedContent = feedEntriesToString(feedEntries);
    appendFeedContent(file, feedContent);
  }

  private String feedEntriesToString(List<FeedEntry> feedEntries) {
    return feedEntries.stream()
            .map(FeedEntry::toString)
            .collect(Collectors.joining("\n"));
  }

  private boolean outputDirExists() {
    return new File(outputDirPath).exists();
  }

  private void createOutputDir() {
    boolean mkdirs = new File(outputDirPath).mkdirs();
    if (!mkdirs) System.out.println("cannot create dir");
  }

  private void appendFeedContent(File file, String feedContent) {
    Path path = file.toPath();
    try {
      Files.write(path, feedContent.getBytes(), StandardOpenOption.APPEND);
    } catch (IOException e) {
      System.out.println("write to file error");
    }
  }
  private File outputFile(String outputFileName) {
    File file = new File(outputFileName);
    if (file.exists()) return file;
    try {
      file.createNewFile();
    } catch (IOException e) {
      System.out.println("can not create file");
    }
    return file;
  }
}
