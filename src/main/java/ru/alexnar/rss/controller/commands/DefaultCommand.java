package ru.alexnar.rss.controller.commands;

public class DefaultCommand implements Command {

  @Override
  public void execute() {
    System.out.println("command not found");
  }

  @Override
  public String alias() {
    return "default";
  }
}
