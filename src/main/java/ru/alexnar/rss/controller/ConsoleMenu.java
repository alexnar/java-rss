package ru.alexnar.rss.controller;

import ru.alexnar.rss.domain.commands.Command;
import ru.alexnar.rss.controller.console.CommandLine;
import ru.alexnar.rss.controller.console.CommandLineParseException;
import ru.alexnar.rss.controller.console.CommandLineReader;

import java.util.List;
import java.util.Map;

public class ConsoleMenu extends Menu {
  public ConsoleMenu() {
    super();
    Command quitCommand = quitCommand();
    Command helpCommand = helpCommand();
    commands.put(quitCommand.alias(), quitCommand);
    commands.put(helpCommand.alias(), helpCommand);
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
      printWrongCommand();
      return;
    }
    if (commands.get(commandLine.alias) == null) {
      printWrongCommand();
      return;
    }
    executeCommand(commandLine);
  }

  private void printWrongCommand() {
    System.out.println("Wrong command!!!");
    printHelp();
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
        return "quit";
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
