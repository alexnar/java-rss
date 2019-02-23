package ru.alexnar.rss.controller;

import org.junit.Test;
import ru.alexnar.rss.controller.console.CommandLine;
import ru.alexnar.rss.controller.console.CommandLineParseException;

import static org.junit.Assert.*;

public class CommandLineTest {
  @Test(expected = CommandLineParseException.class)
  public void emptyCommandThrowsParseException() {
    CommandLine commandLine = new CommandLine("");
  }

  @Test(expected = CommandLineParseException.class)
  public void nullCommandThrowsParseException() {
    CommandLine commandLine = new CommandLine(null);
  }

  @Test
  public void commandWithoutArgsAliasParsed() {
    CommandLine commandLine = new CommandLine("command1");
    assertEquals("command1", commandLine.alias);
    assertEquals(0, commandLine.argsLen());
  }

  @Test
  public void commandWithArgsParsed() {
    CommandLine commandLine = new CommandLine("command1 arg1 arg2");
    assertEquals("command1", commandLine.alias);
    assertEquals(commandLine.arg(1), "arg1");
    assertEquals(commandLine.arg(2), "arg2");
  }

  @Test
  public void notTrimmedCommandParsed() {
    CommandLine commandLine = new CommandLine("   command1  arg1      arg2   ");
    assertEquals("command1", commandLine.alias);
    assertEquals(2, commandLine.argsLen());
    assertEquals(commandLine.arg(1), "arg1");
    assertEquals(commandLine.arg(2), "arg2");
  }

}