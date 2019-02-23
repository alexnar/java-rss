package ru.alexnar.rss.controller.commands;

public interface Command {
  void execute();
  String alias();
}
