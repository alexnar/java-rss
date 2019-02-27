package ru.alexnar.rss.domain.feed.select;

import com.rometools.rome.feed.synd.*;
import com.rometools.rome.io.FeedException;
import org.junit.Test;
import ru.alexnar.rss.domain.feed.FeedTestException;
import ru.alexnar.rss.model.feed.Feed;
import ru.alexnar.rss.model.feed.FeedEntry;
import ru.alexnar.rss.model.feed.FeedProperties;
import ru.alexnar.rss.model.feed.Period;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;
import static ru.alexnar.rss.model.feed.select.FeedElementFields.*;

public class FeedSelectTest {
  @Test
  public void selectElementFieldWorksCorrectly() throws ParseException {
    SyndFeed feed = filledFeed();
    List<String> fields = Arrays.asList(TITLE.field(), PUB_DATE.field());
    FeedSelect feedSelect = feedSelect(feed, fields, 2);
    List<FeedEntry> entries = feedSelect.select();

    FeedEntry entry1 = entries.get(0);
    FeedEntry entry2 = entries.get(1);
    String title1 = entry1.get(TITLE.field());
    String title2 = entry2.get(TITLE.field());
    String description1 = entry1.get(DESCRIPTION.field());
    String description2 = entry2.get(DESCRIPTION.field());
    assertEquals(2, entries.size());
    assertEquals("ROME v1.0", title1);
    assertEquals("ROME v3.0", title2);
    assertNull(description1);
    assertNull(description2);
  }

  @Test
  public void selectElementCountMoreThanExistsReturnAll() throws ParseException {
    SyndFeed feed = filledFeed();
    List<String> fields = Arrays.asList(TITLE.field(), PUB_DATE.field());
    FeedSelect feedSelect = feedSelect(feed, fields, 3);
    List<FeedEntry> entries = feedSelect.select();

    assertEquals(2, entries.size());
  }

  @Test
  public void selectNegativeElementCountReturnEmpties() throws ParseException {
    SyndFeed feed = filledFeed();
    List<String> fields = Arrays.asList(TITLE.field(), PUB_DATE.field());
    FeedSelect feedSelect = feedSelect(feed, fields, -1);
    List<FeedEntry> entries = feedSelect.select();

    assertEquals(0, entries.size());
  }

  private FeedSelect feedSelect(SyndFeed syndFeed, List<String> fields, int elementCount) {
    String url = "https://some_not_existing_url.test";
    FeedProperties props = new FeedProperties(url, elementCount, defaultPeriod(), fields);
    Feed feed;
    try {
      feed = new Feed(props, syndFeed);
    } catch (FeedException e) {
      throw new FeedTestException("cannot create feed");
    }
    return new FeedSelect(feed);
  }

  private SyndFeed filledFeed() throws ParseException {
    SyndFeed feed = new SyndFeedImpl();
    feed.setFeedType("feed_type");
    feed.setTitle("Sample Feed (created with ROME)");
    feed.setLink("http://rome.dev.java.net");
    feed.setDescription("This feed has been created using ROME (Java syndication utilities");
    feed.setAuthor("Стэнли Кубрик");
    feed.setLanguage("ru");
    List<SyndEntry> entries = new ArrayList<>();
    SyndEntry entry;
    SyndContent description;
    entry = new SyndEntryImpl();
    entry.setTitle("ROME v1.0");
    entry.setLink("http://wiki.java.net/bin/view/Javawsxml/Rome01");
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    Date date = simpleDateFormat.parse("2004-07-27");
    entry.setPublishedDate(date);
    description = new SyndContentImpl();
    description.setType("text/plain");
    description.setValue("Initial release of ROME");
    entry.setDescription(description);
    entries.add(entry);
    entry = new SyndEntryImpl();
    entry.setTitle("ROME v3.0");
    entry.setLink("http://wiki.java.net/bin/view/Javawsxml/Rome03");
    entry.setPublishedDate(date);
    description = new SyndContentImpl();
    description.setType("text/html");
    description.setValue("<p>More Bug fixes, mor API changes, some new features and some Unit testing</p>"+
            "<p>For details check the <a href=\"https://rometools.jira.com/wiki/display/ROME/Change+Log#ChangeLog-Changesmadefromv0.3tov0.4\">Changes Log</a></p>");
    entry.setDescription(description);
    entries.add(entry);
    feed.setEntries(entries);
    return feed;
  }

  private Period defaultPeriod() {
    return new Period(1, TimeUnit.SECONDS);
  }
}