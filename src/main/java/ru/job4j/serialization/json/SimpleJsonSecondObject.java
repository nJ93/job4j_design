package ru.job4j.serialization.json;

public class SimpleJsonSecondObject {
  private final String justField;

  public SimpleJsonSecondObject(String justField) {
    this.justField = justField;
  }

  @Override
  public String toString() {
    return "SimpleJsonSecondObject{"
            + "justField='" + justField + '\''
            + '}';
  }
}
