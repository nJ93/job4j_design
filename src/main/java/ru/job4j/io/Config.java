package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;
import java.util.stream.Collectors;

public class Config {

  private final String path;
  private Map<String, String> values = new HashMap<>();

  public Config(final String path) {
    this.path = path;
  }

  public void load() {
    try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
      values = reader
              .lines()
              .filter(line -> line.contains("=") && !line.trim().startsWith("#"))
              .peek(line -> {
                if ("".equals(line.substring(0, line.indexOf("=")))) {
                    throw new IllegalArgumentException();
                }
              })
              .collect(Collectors.toMap(
                      line -> line.trim().substring(0, line.indexOf("=")),
                      line -> line.trim().substring(line.indexOf("=") + 1)
              ));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public String value(String key) {
    return values.get(key);
  }

  @Override
  public String toString() {
    StringJoiner out = new StringJoiner(System.lineSeparator());
    try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
      read.lines().forEach(out::add);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return out.toString();
  }

  public static void main(String[] args) {
    System.out.println(new Config("app.properties"));
  }
}