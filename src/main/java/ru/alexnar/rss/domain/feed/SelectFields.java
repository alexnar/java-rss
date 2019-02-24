package ru.alexnar.rss.domain.feed;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SelectFields {
  private static final String FIELD_ELEMENT_IDENTIFIER = "element_";
  public final List<String> generalFields;
  public final List<String> elementFields;

  public SelectFields(List<String> args) {
    if (args == null) {
      this.generalFields = new ArrayList<>();
      this.elementFields = new ArrayList<>();
      return;
    }
    this.generalFields = args.stream()
            .filter(arg -> !arg.startsWith(FIELD_ELEMENT_IDENTIFIER))
            .collect(Collectors.toList());
    this.elementFields = args.stream()
            .filter(arg -> arg.startsWith(FIELD_ELEMENT_IDENTIFIER))
            .map(arg -> arg.substring(FIELD_ELEMENT_IDENTIFIER.length()))
            .collect(Collectors.toList());

  }
}
