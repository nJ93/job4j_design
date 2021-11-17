package ru.job4j.question;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Analize {

  public static Info diff(Set<User> previous, Set<User> current) {

    for (User user : current) {
      if (!previous.contains(user)) {
//        previous.
      }
    }
    return new Info(0, 0, 0);
  }

}