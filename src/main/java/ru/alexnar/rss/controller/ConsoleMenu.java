package ru.alexnar.rss.controller;

import ru.alexnar.rss.controller.commands.Command;
import ru.alexnar.rss.controller.console.CommandLine;
import ru.alexnar.rss.controller.console.CommandLineParseException;
import ru.alexnar.rss.controller.console.CommandLineReader;

import java.util.List;
import java.util.Map;

public class ConsoleMenu extends Menu {
  private static final String QUIT_ALIAS = "quit";
  private static final String HELP_ALIAS = "help";

  public ConsoleMenu() {
    super();
    commands.put(QUIT_ALIAS, quitCommand());
    commands.put(HELP_ALIAS, helpCommand());
  }

  @Override
  public void start() {
    printHelp();
    while (true) {
      CommandLineReader commandLineReader = new CommandLineReader();
      String commandLineStr = commandLineReader.readLine();
      handleCommandLineEvent(commandLineStr);
    }
  }

  private void handleCommandLineEvent(String commandLineStr) {
    CommandLine commandLine = null;
    try {
      commandLine = new CommandLine(commandLineStr);
    } catch (CommandLineParseException e) {
      System.out.println("Wrong command");
      printHelp();
    }
    executeCommand(commandLine);
  }

  private void printHelp() {
    System.out.println("Available commands:");
    commands.entrySet().stream()
            .map(Map.Entry::getValue)
            .forEach(this::printCommand);
  }

  private void printCommand(Command command) {
    System.out.println(command.alias() + " - " + command.description());
  }

  private Command quitCommand() {
    return new Command() {
      @Override
      public void execute(List<String> args) {
        System.exit(0);
      }

      @Override
      public String alias() {
        return "exit";
      }

      @Override
      public String description() {
        return "stop application";
      }
    };
  }

  private Command helpCommand() {
    return new Command() {
      @Override
      public void execute(List<String> args) {
        printHelp();
      }

      @Override
      public String alias() {
        return "help";
      }

      @Override
      public String description() {
        return "print help";
      }
    };
  }

  public static void main(String[] args) {
    ConsoleMenu consoleMenu = new ConsoleMenu();
    consoleMenu.start();
  }

}
