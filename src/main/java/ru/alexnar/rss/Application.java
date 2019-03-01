package ru.alexnar.rss;

import ru.alexnar.rss.controller.ConsoleMenu;
import ru.alexnar.rss.controller.Menu;
import ru.alexnar.rss.domain.config.RssConfigAccessor;
import ru.alexnar.rss.model.config.RssConfig;

public class Application {
  public static void main(String[] args) {
    Application application = new Application();
    application.run();
  }

  private void run() {
    RssConfig rssConfig = RssConfigAccessor.getInstance();
    rssConfig.setup();
    Menu menu = new ConsoleMenu();
    menu.start();
  }
}
