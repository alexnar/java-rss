package ru.alexnar.rss.controller.console;

public class CommandLine {
  public final String alias;
  private final String[] args;

  public CommandLine(String commandLine) {
    this.alias = "";
    this.args = new String[]{};
  }

  public String arg(int i) {
    return "";
  }

  public int argsLen() {
    return args.length;
  }

  private void parseCommandLine() {

  }

}
