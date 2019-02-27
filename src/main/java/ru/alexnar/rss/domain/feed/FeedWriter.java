package ru.alexnar.rss.domain.feed;

import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedOutput;
import ru.alexnar.rss.model.feed.Feed;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;

public class FeedWriter {
  private static final String OUTPUT_DIR_PATH = "feed_output/";

  public void write(Feed feed, SyndFeed syndFeed) {
    if (!outputDirExists()) createOutputDir();
    String outputFileName = OUTPUT_DIR_PATH + feed.properties.outputFileName;
    File file = outputFile(outputFileName);
    String feedContent = feedToString(syndFeed);
    appendFeedContent(file, feedContent, feed);
  }

  private String feedToString(SyndFeed syndFeed) {
    SyndFeedOutput feedOutput = new SyndFeedOutput();
    String result = "";
    try {
      result = feedOutput.outputString(syndFeed);
    } catch (FeedException e) {
      System.out.println("wrong feed");
    }
    return result;
  }

  private boolean outputDirExists() {
    return new File(OUTPUT_DIR_PATH).exists();
  }

  private void createOutputDir() {
    boolean mkdirs = new File(OUTPUT_DIR_PATH).mkdirs();
    if (!mkdirs) System.out.println("cannot create dir");
  }

  private void appendFeedContent(File file, String feedContent, Feed feed) {
    Path path = file.toPath();
    String content = prettyFeedContent(feedContent, feed);
    try {
      Files.write(path, content.getBytes(), StandardOpenOption.APPEND);
    } catch (IOException e) {
      System.out.println("write to file error");
    }
  }

  private String prettyFeedContent(String feedContent, Feed feed) {
    String separator = "\n--------------------------------------------------\n";
    return separator +
            "Feed from url:" + feed.url + "\n" +
            "Time fetched:" + LocalDateTime.now().toString() + "\n" +
            feedContent;
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

  private File createFile(String outputFileName) {
    return null;
  }
}
