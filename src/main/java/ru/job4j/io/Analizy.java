package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Analizy {
  public void unavailable(String source, String target) throws IOException {
    try (BufferedReader reader = new BufferedReader(new FileReader(source))) {
      List<String> lines = reader.lines().collect(Collectors.toList());
      List<String> periodList = new ArrayList<>();
      StringBuilder period = new StringBuilder();
      for (String line : lines) {
        String[] splitedLine = line.split(" ");
        String code = splitedLine[0];
        String time = splitedLine[1];
        if ("400".equals(code) || "500".equals(code)) {
          if (period.isEmpty()) {
            period.append(time).append(";");
          }
        } else {
          if (!period.isEmpty()) {
            period.append(time).append(";");
            periodList.add(period.toString());
            period.setLength(0);
          }
        }
      }
      if (!periodList.isEmpty()) {
        try (PrintWriter writer = new PrintWriter(new BufferedOutputStream(new FileOutputStream(target)))) {
          for (String line : periodList) {
            writer.write(line + System.lineSeparator());
          }
        }
      }
    }
  }

  public static void main(String[] args) {
    Analizy analizy = new Analizy();
    try {
      analizy.unavailable("./data/server.log", "./data/unavailable.csv");
      analizy.unavailable("./data/server1.log", "./data/unavailable1.csv");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}