package ru.alexnar.rss.domain.commands;

import java.util.List;

public interface Command {
  void execute(List<String> args);
  String alias();
  String description();
}
