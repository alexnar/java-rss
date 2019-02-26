package ru.alexnar.rss.domain.feed.select;

import com.rometools.rome.feed.synd.*;
import org.junit.Test;
import ru.alexnar.rss.domain.feed.Feed;
import ru.alexnar.rss.domain.feed.FeedProperties;
import ru.alexnar.rss.domain.feed.Period;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

public class FeedSelectTest {
  @Test
  public void selectFieldWorksCorrectly() throws ParseException {
    SyndFeed feed = filledFeed();
    List<String> fields = Collections.singletonList("author");
    FeedSelect feedSelect = feedSelect(feed, fields, 2);
    SyndFeed result = feedSelect.select();
    assertEquals("Стэнли Кубрик", result.getAuthor());
    assertNull(result.getLanguage());
  }

  @Test
  public void selectElementFieldWorksCorrectly() throws ParseException {
    SyndFeed feed = filledFeed();
    List<String> fields = Arrays.asList("author", "element_title", "element_pubDate");
    FeedSelect feedSelect = feedSelect(feed, fields, 2);
    SyndFeed result = feedSelect.select();

    SyndEntry entry1 = result.getEntries().get(0);
    SyndEntry entry2 = result.getEntries().get(1);
    String title1 = entry1.getTitle();
    String title2 = entry2.getTitle();
    SyndContent description1 = entry1.getDescription();
    SyndContent description2 = entry2.getDescription();
    assertEquals(2, result.getEntries().size());
    assertEquals("ROME v1.0", title1);
    assertEquals("ROME v3.0", title2);
    assertNull(description1);
    assertNull(description2);
  }

  @Test
  public void selectElementCountMoreThanExistsReturnAll() throws ParseException {
    SyndFeed feed = filledFeed();
    List<String> fields = Arrays.asList("author", "element_title", "element_pubDate");
    FeedSelect feedSelect = feedSelect(feed, fields, 3);
    SyndFeed result = feedSelect.select();

    assertEquals(2, result.getEntries().size());
  }

  @Test
  public void selectNegativeElementCountReturnEmpties() throws ParseException {
    SyndFeed feed = filledFeed();
    List<String> fields = Arrays.asList("author", "element_title", "element_pubDate");
    FeedSelect feedSelect = feedSelect(feed, fields, -1);
    SyndFeed result = feedSelect.select();

    assertEquals(0, result.getEntries().size());
  }

  private FeedSelect feedSelect(SyndFeed feed, List<String> fields, int elementCount) {
    String url = "https://some_not_existing_url.test";
    SelectFields selectFields = new SelectFields(fields);
    FeedProperties props = new FeedProperties(url, elementCount, defaultPeriod(), selectFields);
    return new FeedSelect(feed, props);
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