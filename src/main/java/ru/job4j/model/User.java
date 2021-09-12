package ru.job4j.model;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class User {
  private String name;
  private int children;
  private Calendar birthday;

  public User(String name, int children, Calendar birthday) {
    this.name = name;
    this.children = children;
    this.birthday = birthday;
  }

  public static void main(String[] args) {
    Calendar calendar = Calendar.getInstance();
    String userStr = "User";
    User userOne = new User(userStr, 1, calendar);
    User userTwo = new User(userStr, 1, calendar);
    Map<User, Object> userMap = new HashMap<>();
    userMap.put(userOne, new Object());
    userMap.put(userTwo, new Object());
    for (Map.Entry<User, Object> entry: userMap.entrySet()) {
      System.out.println(entry.getKey());
      System.out.println(entry.getValue());
    }
  }
}
