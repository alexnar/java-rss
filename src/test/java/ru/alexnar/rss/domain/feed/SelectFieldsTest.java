package ru.alexnar.rss.domain.feed;

import org.junit.Test;
import ru.alexnar.rss.domain.feed.select.SelectFields;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class SelectFieldsTest {
  @Test
  public void correctFieldsParsed() {
    List<String> fields = Arrays.asList("author", "language", "element_title", "element_link", "element_description");
    SelectFields selectFields = new SelectFields(fields);
    List<String> generalFields = selectFields.generalFields;
    List<String> elementFields = selectFields.elementFields;
    assertEquals(2, generalFields.size());
    assertEquals(3, elementFields.size());
    assertTrue(generalFields.containsAll(Arrays.asList("author", "language")));
    assertTrue(elementFields.containsAll(Arrays.asList("element_title", "element_link", "element_description")));
  }

  @Test
  public void emptyListReturnEmptyLists() {
    SelectFields selectFields = new SelectFields(new ArrayList<>());
    emptyAssertion(selectFields);
  }

  @Test
  public void nullReturnEmptyLists() {
    SelectFields selectFields = new SelectFields(null);
    emptyAssertion(selectFields);
  }

  private void emptyAssertion(SelectFields selectFields) {
    List<String> generalFields = selectFields.generalFields;
    List<String> elementFields = selectFields.elementFields;
    assertNotNull(generalFields);
    assertNotNull(elementFields);
    assertTrue(generalFields.isEmpty());
    assertTrue(elementFields.isEmpty());
  }

}