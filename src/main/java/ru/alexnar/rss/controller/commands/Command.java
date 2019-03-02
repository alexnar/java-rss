package ru.alexnar.rss.controller.commands;

import java.util.List;

/**
 * Base interface for interface commands
 */
public interface Command {
  /**
   * Execute command with arguments
   * @param args - passed from console args
   */
  void execute(List<String> args);

  String alias();

  String description();
}
