package ru.alexnar.rss.controller.console;

import ru.alexnar.rss.domain.config.RssConfigAccessor;
import ru.alexnar.rss.model.config.RssConfig;
import ru.alexnar.rss.model.feed.Period;
import ru.alexnar.rss.model.feed.select.FeedElementFields;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class ConsoleUtils {
  private static final String FORMAT_ERROR_MESSAGE = "Incorrect format.";
  private static final Scanner scanner = ConsoleConfig.scanner;


  public static Period readPeriod() {
    String helpMessage =
            "Format: <value> <unit> (unit in {SECONDS,MINUTES,HOURS}";
    System.out.println(helpMessage);
    String periodStr = scanner.nextLine();
    String[] parts = periodStr.split(" ");
    if (parts.length < 2) {
      System.out.println(FORMAT_ERROR_MESSAGE);
      return readPeriod();
    }
    long duration;
    TimeUnit unit;
    try {
      duration = Long.valueOf(parts[0]);
      unit = TimeUnit.valueOf(parts[1]);
    } catch (IllegalArgumentException e) {
      return readPeriod();
    }
    return new Period(duration, unit);
  }

  public static List<String> readElementFields() {
    String availableFields = FeedElementFields.fieldsStr();
    String helpMessage = "Format: field1 field2..." + "(Available fields: " + availableFields + ")";
    System.out.println(helpMessage);
    String parts = scanner.nextLine();
    if (parts == null || parts.isEmpty()) {
      System.out.println(FORMAT_ERROR_MESSAGE);
      return readElementFields();
    }
    return parseParts(parts, " ");
  }

  public static String readOutputFileName() {
    System.out.println("Write file name");
    String fileName = scanner.nextLine();
    if (fileName == null || fileName.isEmpty()) {
      System.out.println(FORMAT_ERROR_MESSAGE);
      return readOutputFileName();
    }
    return fileName;
  }

  public static int readElementCount() {
    System.out.println("Write integer number:");
    int elementCount;
    try {
      elementCount = Integer.valueOf(scanner.nextLine());
    } catch (NumberFormatException e) {
      System.out.println(FORMAT_ERROR_MESSAGE);
      return readElementCount();
    }
    return elementCount;
  }

  public static List<String> parseParts(String args, String delimiter) {
    String[] parts = args.split(delimiter);
    return Arrays.stream(parts)
            .filter(part -> !part.isEmpty())
            .map(String::trim)
            .collect(Collectors.toList());
  }
}
