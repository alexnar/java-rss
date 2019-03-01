package ru.alexnar.rss.domain.config;

import com.rometools.rome.io.FeedException;
import org.yaml.snakeyaml.Yaml;
import ru.alexnar.rss.YamlTest;
import ru.alexnar.rss.model.config.RssConfig;

import java.io.InputStream;

public class RssConfigAccessor {
  private static RssConfig config = initialize();


  public static RssConfig getInstance() {
    return config;
  }

  private static RssConfig initialize() {
    Yaml yaml = new Yaml(new RssYamlConstructor(RssConfig.class));
    InputStream inputStream = YamlTest.class.getResourceAsStream("/conf.yml");
    return yaml.load(inputStream);
  }
}
