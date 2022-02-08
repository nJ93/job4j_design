package ru.job4j.io;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class ArgsName {

  private final Map<String, String> values = new HashMap<>();

  public String get(String key) {
    return values.get(key);
  }

  private void parse(String[] args) {
    if (args.length == 0) {
      throw new IllegalArgumentException("Empty arguments");
    }

    values.putAll(Arrays.stream(args)
            .map(str -> str.substring(1).split("="))
            .peek(array -> {
              if (array.length <= 1 || array[0] == null || array[1] == null || "".equals(array[0]) || "".equals(array[1])) {
                throw new IllegalArgumentException("Wrong argument");
              }
            })
            .collect(Collectors.toMap(k -> k[0], v -> v[1])));
  }

  public static ArgsName of(String[] args) {
    ArgsName names = new ArgsName();
    names.parse(args);
    return names;
  }

  public static void main(String[] args) {
    ArgsName jvm = ArgsName.of(new String[]{"-Xmx=512", "-encoding=UTF-8"});
    System.out.println(jvm.get("Xmx"));

    ArgsName zip = ArgsName.of(new String[]{"-out=project.zip", "-encoding=UTF-8"});
    System.out.println(zip.get("out"));
  }
}