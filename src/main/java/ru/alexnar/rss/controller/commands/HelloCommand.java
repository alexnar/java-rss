package ru.alexnar.rss.controller.commands;

public class HelloCommand implements Command {
  @Override
  public void execute() {
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
