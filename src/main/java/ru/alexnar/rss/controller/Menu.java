package ru.alexnar.rss.controller;

import ru.alexnar.rss.controller.commands.Command;
import ru.alexnar.rss.controller.commands.DefaultCommand;
import ru.alexnar.rss.controller.commands.HelloCommand;
import ru.alexnar.rss.controller.console.CommandLine;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Menu {
  private Map<String, Command> commands;

  public Menu() {
    this.commands = defaultCommandMap();
  }

  public Menu(Map<String, Command> commands) {
    this.commands = commands;
  }

  /*public void start() {
    CommandLineReader commandLineReader = new CommandLineReader();
    while (true) {
      String commandLineStr = commandLineReader.readLine();
      CommandLine commandLine = new CommandLine(commandLineStr);
    }
  }*/

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
            new DefaultCommand()
    );
  }
}
