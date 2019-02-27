package ru.alexnar.rss.controller;

import org.junit.Test;
import ru.alexnar.rss.model.console.CommandLine;
import ru.alexnar.rss.model.console.CommandLineParseException;

import static org.junit.Assert.*;

public class CommandLineTest {
  @Test(expected = CommandLineParseException.class)
  public void emptyCommandThrowsParseException() throws CommandLineParseException {
    CommandLine commandLine = new CommandLine("");
  }

  @Test(expected = CommandLineParseException.class)
  public void nullCommandThrowsParseException() throws CommandLineParseException {
    CommandLine commandLine = new CommandLine(null);
  }

  @Test
  public void commandWithoutArgsAliasParsed() throws CommandLineParseException {
    CommandLine commandLine = new CommandLine("command1");
    assertEquals("command1", commandLine.alias);
    assertEquals(0, commandLine.argsLen());
  }

  @Test
  public void commandWithArgsParsed() throws CommandLineParseException {
    CommandLine commandLine = new CommandLine("command1 arg1 arg2");
    assertEquals("command1", commandLine.alias);
    assertEquals("arg1", commandLine.arg(0));
    assertEquals("arg2", commandLine.arg(1));
  }

  @Test
  public void notTrimmedCommandParsed() throws CommandLineParseException {
    CommandLine commandLine = new CommandLine("   command1  arg1      arg2   ");
    assertEquals("command1", commandLine.alias);
    assertEquals(2, commandLine.argsLen());
    assertEquals("arg1", commandLine.arg(0));
    assertEquals("arg2", commandLine.arg(1));
  }
}