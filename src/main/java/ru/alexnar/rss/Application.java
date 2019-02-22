package ru.alexnar.rss;

import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.SyndFeedOutput;
import com.rometools.rome.io.XmlReader;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class Application {
  private void run() throws IOException, FeedException {
    // https://habr.com/ru/rss/post/335382/
    // https://meta.stackexchange.com/feeds/question/3403
    String urlStr = "https://habr.com/ru/rss/post/335382/";
    URL url = new URL(urlStr);
    SyndFeedInput input = new SyndFeedInput();
    SyndFeed feed = input.build(new XmlReader(url));

    String description = feed.getDescription();
    List<SyndEntry> entries = feed.getEntries();
    entries.forEach(e -> System.out.println(e.getDescription().getValue() + "\n\n"));
    System.out.println(description);
  }

  public static void main(String[] args) throws IOException, FeedException {
    Application application = new Application();
    application.run();
  }
}
