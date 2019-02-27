package ru.alexnar.rss.model.feed.select;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum FeedElementFields {
  TITLE("title"),
  LINK("link"),
  DESCRIPTION("description"),
  PUB_DATE("pubDate");

  private final String field;

  FeedElementFields(String field) {
    this.field = field;
  }

  public String field() {
    return field;
  }

  public static List<String> fields() {
    return Arrays.stream(FeedElementFields.values())
            .map(element -> element.field)
            .collect(Collectors.toList());
  }
}
