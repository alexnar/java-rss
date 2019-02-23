package ru.alexnar.rss.controller.commands;

import java.util.List;

public interface Command {
  void execute(List<String> args);
  String alias();
}
