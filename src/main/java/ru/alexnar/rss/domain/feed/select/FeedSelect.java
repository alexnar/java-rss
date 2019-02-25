package ru.alexnar.rss.domain.feed.select;

import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.feed.synd.SyndFeedImpl;
import ru.alexnar.rss.domain.feed.FeedProperties;

import java.util.Map;

import static ru.alexnar.rss.domain.feed.select.Selectors.paramSelectorMap;

public class FeedSelect {
  private final SyndFeed sourceFeed;
  private final FeedProperties props;

  public FeedSelect(SyndFeed sourceFeed, FeedProperties props) {
    this.sourceFeed = sourceFeed;
    this.props = props;
  }

  public SyndFeed select() {
    SyndFeed resultFeed = initialResultFeed();
    SelectFields selectFields = props.selectFields;
    Map<String, FeedFieldSelector> paramSelectors = paramSelectorMap(props);
    for (String generalField : selectFields.generalFields) {
      FeedFieldSelector feedFieldSelector = paramSelectors.get(generalField);
      feedFieldSelector.selectField(sourceFeed, resultFeed);
    }
    if (selectFields.elementFields.isEmpty()) return resultFeed;
    FeedFieldSelector elementSelector = paramSelectors.get("element");
    elementSelector.selectField(sourceFeed, resultFeed);
    return resultFeed;
  }

  private SyndFeed initialResultFeed() {
    SyndFeedImpl feedSelect = new SyndFeedImpl();
    feedSelect.setFeedType(sourceFeed.getFeedType());
    feedSelect.setTitle(sourceFeed.getTitle());
    feedSelect.setLink(sourceFeed.getLink());
    feedSelect.setDescription(sourceFeed.getDescription());
    return feedSelect;
  }
}
