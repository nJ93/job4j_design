package ru.job4j.searcher;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class ArgsName {

  private final Map<String, String> values = new HashMap<>();

  public String get(String key) {
    String s = values.get(key);
    if (s == null) {
      throw new IllegalArgumentException("Key is not exist");
    }
    return s;
  }

  private void parse(String[] args) {
    if (args.length == 0) {
      throw new IllegalArgumentException("Empty arguments");
    }

    values.putAll(Arrays.stream(args)
            .filter(this::validateString)
            .collect(Collectors.toMap(k -> k.substring(1, k.indexOf("=")), v -> v.substring(v.indexOf("=") + 1))));
  }

  private boolean validateString(String str) {
    boolean match = str.matches("-[^\\s]+=[^\\s]+");
    if (!match) {
      throw new IllegalArgumentException("Wrong argument " + str);
    }
    return true;
  }

  public static ArgsName of(String[] args) {
    ArgsName names = new ArgsName();
    names.parse(args);
    return names;
  }
}