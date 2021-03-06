package ru.alexnar.rss.model.console;

import ru.alexnar.rss.controller.console.ConsoleUtils;

import java.util.List;

/**
 * Represent command line record
 */
public class CommandLine {
  public final String alias;
  public final List<String> args;

  public CommandLine(String commandLine) throws CommandLineParseException {
    List<String>  commandLineParts = commandLineParts(commandLine);
    this.alias = parseAlias(commandLineParts);
    this.args = parseArgs(commandLineParts);
  }

  public String arg(int i) {
    if (i >= args.size()) return "";
    return args.get(i);
  }

  public int argsLen() {
    return args.size();
  }

  private String parseAlias(List<String> commandLineParts) throws CommandLineParseException {
    if (commandLineParts.isEmpty()) throw new CommandLineParseException("incorrect command");
    String commandLinePart = commandLineParts.get(0);
    String alias = commandLinePart.trim();
    if (alias.isEmpty()) throw new CommandLineParseException("incorrect command");
    return alias;
  }

  private List<String> parseArgs(List<String> commandLineParts) {
    return commandLineParts.subList(1, commandLineParts.size());
  }

  private List<String> commandLineParts(String commandLine) throws CommandLineParseException {
    if (commandLine == null || commandLine.isEmpty()) throw new CommandLineParseException("incorrect command");
    return ConsoleUtils.parseParts(commandLine, " ");
  }
}
