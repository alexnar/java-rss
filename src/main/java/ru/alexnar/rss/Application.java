package ru.alexnar.rss;

import ru.alexnar.rss.controller.ConsoleMenu;
import ru.alexnar.rss.controller.Menu;

public class Application {
  private void run() {
    Menu menu = new ConsoleMenu();
    menu.start();
  }

  public static void main(String[] args) {
    Application application = new Application();
    application.run();
  }
}
