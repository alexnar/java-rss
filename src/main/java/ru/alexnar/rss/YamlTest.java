package ru.alexnar.rss;

import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;
import ru.alexnar.rss.domain.config.RssYamlConstructor;
import ru.alexnar.rss.model.config.RssConfig;

import java.io.InputStream;

public class YamlTest {
  public static void main(String[] args) {
    Yaml yaml = new Yaml(new RssYamlConstructor(RssConfig.class));
    InputStream inputStream = YamlTest.class.getResourceAsStream("/conf.yml");
    RssConfig rssConfig = yaml.load(inputStream);
    System.out.println();
  }
}
