package ru.alexnar.rss.controller.console;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CommandLine {
  public final String alias;
  private final List<String> args;

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
    String[] parts = commandLine.split("\\W+");
    return Arrays.stream(parts)
            .filter(part -> !part.isEmpty())
            .map(String::trim)
            .collect(Collectors.toList());
  }
}
