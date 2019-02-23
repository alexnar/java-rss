package ru.alexnar.rss.controller.console;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CommandLineReader {
  public String readLine() {
    String line = "";
    try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
      line = bufferedReader.readLine();
    } catch (IOException e) {
      System.out.println("Error while reading command");
    }
    return line;
  }
}
