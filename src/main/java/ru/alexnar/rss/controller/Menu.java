package ru.alexnar.rss.controller;

import ru.alexnar.rss.controller.commands.Command;
import ru.alexnar.rss.controller.commands.DefaultCommand;
import ru.alexnar.rss.controller.commands.HelloCommand;

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

  public void executeCommand(String commandName) {
    Command command = commands.get(commandName);
    command.execute();
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

  public static void main(String[] args) {
    Map<String, Command> commands = new HashMap<>();
    commands.put("create", new HelloCommand());
    commands.put("create1", new HelloCommand());
    commands.put("create2", new HelloCommand());
    Menu menu = new Menu(commands);
    menu.executeCommand("create3");
  }
}
