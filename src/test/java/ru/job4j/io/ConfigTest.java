package ru.job4j.io;

import org.hamcrest.Matchers;
import org.junit.Ignore;
import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ConfigTest {

  @Test
  public void whenPairWithoutComment() {
    String path = "app.properties";
    Config config = new Config(path);
    config.load();
    assertThat(config.value("hibernate.dialect"), is("org.hibernate.dialect.PostgreSQLDialect"));
    assertThat(config.value("dialect.hibernate"), is(Matchers.nullValue()));
  }

  @Test
  public void whenCommentsAndEmptyLines() {
    String path = "./data/second.properties";
    Config config = new Config(path);
    config.load();
    assertThat(config.value("test"), is(Matchers.nullValue()));
    assertThat(config.value("test1"), is(Matchers.nullValue()));
    assertThat(config.value("test2"), is(Matchers.nullValue()));
  }

  @Test(expected = IllegalArgumentException.class)
  public void whenWrongStructure() {
    String path = "./data/third.properties";
    Config config = new Config(path);
    config.load();
  }

  @Test(expected = IllegalArgumentException.class)
  public void whenEmptyValue() {
    String path = "./data/fourth.properties";
    Config config = new Config(path);
    config.load();
  }

  @Test(expected = IllegalArgumentException.class)
  public void whenMissingDelimiter() {
    String path = "./data/fifth.properties";
    Config config = new Config(path);
    config.load();
  }

  @Test(expected = IllegalArgumentException.class)
  public void whenMoreThanOneDelimiter() {
    String path = "./data/sixth.properties";
    Config config = new Config(path);
    config.load();
  }
}

