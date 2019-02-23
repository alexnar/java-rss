package ru.alexnar.rss.domain.commands;

import java.util.List;

public class DefaultCommand implements Command {
  @Override
  public void execute(List<String> args) {
    System.out.println("command not found");
  }

  @Override
  public String alias() {
    return "default";
  }

  @Override
  public String description() {
    return null;
  }
}
