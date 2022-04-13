package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

  private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

  public static void main(String[] args) {
    String name = "Petr Arsentev";
    int age = 33;
    long height = 180;
    double weight = 80.00;
    float averageScores = 8.6f;
    char symbol = 'a';
    boolean isTrue = true;
    byte something = 12;
    short iq = 150;
    LOG.debug("User info name : {}, age : {}, height : {}, weight : {}, avgScores : {}, symbol : {}, isTrue : {}, something : {}, iq : {}", name, age, height, weight, averageScores, symbol, isTrue, something, iq);
  }
}