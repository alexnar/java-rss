package ru.alexnar.rss.controller;

import ru.alexnar.rss.domain.commands.AddFeedCommand;
import ru.alexnar.rss.domain.commands.Command;
import ru.alexnar.rss.domain.commands.DefaultCommand;
import ru.alexnar.rss.domain.commands.HelloCommand;
import ru.alexnar.rss.model.console.CommandLine;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public abstract class Menu {
  protected Map<String, Command> commands;

  public Menu() {
    this.commands = defaultCommandMap();
  }

  public Menu(Map<String, Command> commands) {
    this.commands = commands;
  }

  public abstract void start();

  public void executeCommand(CommandLine commandLine) {
    Command command = commands.get(commandLine.alias);
    command.execute(commandLine.args);
  }

  private Map<String, Command> defaultCommandMap() {
    return defaultCommandList().stream()
            .collect(Collectors.toMap(Command::alias, command -> command));
  }

  private List<Command> defaultCommandList() {
    return Arrays.asList(
            new HelloCommand(),
            new DefaultCommand(),
            new AddFeedCommand()
    );
  }
}
