package ru.alexnar.rss.domain.feed;

import com.rometools.rome.feed.synd.SyndContentImpl;
import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndEntryImpl;
import com.rometools.rome.io.FeedException;
import org.junit.Test;
import ru.alexnar.rss.domain.feed.select.FeedEntryFieldSelector;
import ru.alexnar.rss.model.feed.Feed;
import ru.alexnar.rss.model.feed.FeedEntry;
import ru.alexnar.rss.model.feed.FeedProperties;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static ru.alexnar.rss.domain.feed.select.Selectors.elementFieldSelectorMap;
import static ru.alexnar.rss.model.feed.select.FeedElementFields.*;

public class FeedWriterTest {
  private static final String OUTPUT_DIR = "/tmp/feed_output/";
  private static Map<String, FeedEntryFieldSelector> selectors = elementFieldSelectorMap();

  @Test
  public void writeOnlySelectedFields() throws FeedException {
    Map<String, FeedEntryFieldSelector> selectors = new HashMap<>();
    selectors.put(DESCRIPTION.field(), descriptionSelector());
    selectors.put(PUB_DATE.field(), pubDateSelector());
    FeedEntry entry = feedEntry("descr1", new Date(), "title1", "link1", selectors);
    FeedProperties props = new FeedProperties();
    props.url = "https://somesite.ru";
    props.outputFileName = "filename";
    String fullPath = OUTPUT_DIR + props.outputFileName;
    new File(fullPath).delete();
    Feed feed = new Feed(props);
    FeedWriter feedWriter = new FeedWriter(OUTPUT_DIR);
    feedWriter.write(feed, Collections.singletonList(entry));

    List<String> lines = readLines(fullPath);
    assertEquals(2, lines.size());
    assertTrue(lines.get(0).startsWith("description:"));
    assertTrue(lines.get(1).startsWith("pubDate:"));
  }

  @Test
  public void emptySelectorsWriteNothing() throws FeedException {
    Map<String, FeedEntryFieldSelector> selectors = new HashMap<>();
    FeedEntry entry = feedEntry("descr1", new Date(), "title1", "link1", selectors);
    FeedProperties props = new FeedProperties();
    props.url = "https://somesite.ru";
    props.outputFileName = "filename";
    String fullPath = OUTPUT_DIR + props.outputFileName;
    new File(fullPath).delete();
    Feed feed = new Feed(props);
    FeedWriter feedWriter = new FeedWriter(OUTPUT_DIR);
    feedWriter.write(feed, Collections.singletonList(entry));

    List<String> lines = readLines(fullPath);
    assertEquals(0, lines.size());
  }

  @Test
  public void nullFeedEntriesWriteDoNothing() throws FeedException {
    FeedWriter feedWriter = new FeedWriter("/tm");
    FeedEntry entry = feedEntry("descr1", new Date(), "title1", "link1", selectors);
    FeedProperties props = new FeedProperties();
    props.url = "https://somesite.ru";
    Feed feed = new Feed(props);

    feedWriter.write(feed, null);
  }

  private FeedEntry feedEntry(String descriptionStr, Date date, String title, String link,
                              Map<String, FeedEntryFieldSelector> selectors) {
    SyndEntryImpl entry = new SyndEntryImpl();
    SyndContentImpl description = new SyndContentImpl();
    description.setValue(descriptionStr);
    entry.setDescription(description);
    entry.setPublishedDate(date);
    entry.setTitle(title);
    entry.setLink(link);
    return new FeedEntry(entry, selectors);
  }

  private FeedEntryFieldSelector descriptionSelector() {
    return selectors.get(DESCRIPTION.field());
  }

  private FeedEntryFieldSelector titleSelector() {
    return selectors.get(TITLE.field());
  }

  private FeedEntryFieldSelector pubDateSelector() {
    return selectors.get(PUB_DATE.field());
  }

  private FeedEntryFieldSelector linkSelector() {
    return selectors.get(LINK.field());
  }

  private List<String> readLines(String fullPath) {
    List<String> lines;
    try {
      lines = Files.readAllLines(Paths.get(fullPath));
    } catch (IOException e) {
      return new ArrayList<>();
    }
    return lines;
  }
}
