package ru.alexnar.rss.domain.config;

import org.yaml.snakeyaml.constructor.AbstractConstruct;
import org.yaml.snakeyaml.constructor.Constructor;
import org.yaml.snakeyaml.nodes.Node;
import org.yaml.snakeyaml.nodes.NodeId;
import org.yaml.snakeyaml.nodes.ScalarNode;
import org.yaml.snakeyaml.nodes.Tag;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * Custom Yaml constructor
 */
public class RssYamlConstructor extends Constructor {
  public RssYamlConstructor(Class<?> theRoot) {
    super(theRoot);
    this.yamlConstructors.put(new Tag("!timeUnit"), new ConstructPeriod());
  }

  /**
   * Constructor to serialize @{@link TimeUnit}
   */
  private class ConstructPeriod extends AbstractConstruct {
    @Override
    public Object construct(Node node) {
      Object o = constructScalar((ScalarNode) node);
      String unitStr = o.toString();
      TimeUnit unit;
      try {
        unit = TimeUnit.valueOf(unitStr);
      } catch (IllegalArgumentException e) {
        String availableValues = Arrays.stream(TimeUnit.values())
                .map(TimeUnit::toString)
                .collect(Collectors.joining(", "));
        throw new ConfigException("Wrong time unit declaration: " + availableValues);
      }
      return unit;
    }
  }
}
