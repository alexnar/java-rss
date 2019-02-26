package ru.alexnar.rss.domain.feed.select;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum FeedFields {
  AUTHOR("author"),
  LANGUAGE("language"),
  ELEMENT_TITLE("element_title"),
  ELEMENT_LINK("element_link"),
  ELEMENT_DESCRIPTION("element_description"),
  ELEMENT_PUB_DATE("element_pubDate");

  private final String field;

  FeedFields(String field) {
    this.field = field;
  }

  public String field() {
    return field;
  }

  public static List<String> fields() {
    return Arrays.stream(FeedFields.values())
            .map(element -> element.field)
            .collect(Collectors.toList());
  }
}
