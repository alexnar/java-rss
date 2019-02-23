package ru.alexnar.rss.controller.commands;

import java.util.List;

public class HelloCommand implements Command {
  @Override
  public void execute(List<String> args) {
    sayHello();
  }

  @Override
  public String alias() {
    return "hello";
  }

  public void sayHello() {
    System.out.println("hello world");
  }
}
