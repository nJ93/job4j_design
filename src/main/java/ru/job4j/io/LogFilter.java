package ru.job4j.io;

import java.io.*;
import java.util.List;
import java.util.stream.Collectors;

public class LogFilter {
  public static List<String> filter(String file) {
    List<String> result = null;
    try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
      result = reader.lines().filter(line -> {
        String[] splitedLine = line.split(" ");
        return "404".equals(splitedLine[splitedLine.length - 2]);
      }).collect(Collectors.toList());
    } catch (IOException e) {
      e.printStackTrace();
    }
    return result;
  }

  public static void save(List<String> log, String file) {
    try (PrintWriter writer = new PrintWriter(new BufferedOutputStream(new FileOutputStream(file)))) {
      for (String line : log) {
        writer.println(line);
      }
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
  }

  public static void main(String[] args) {
    List<String> log = filter("log.txt");
    save(log, "404.txt");
  }
}