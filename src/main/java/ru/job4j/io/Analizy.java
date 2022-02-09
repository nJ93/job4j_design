package ru.job4j.io;

import java.io.*;

public class Analizy {
  public void unavailable(String source, String target) throws IOException {
    try (BufferedReader reader = new BufferedReader(new FileReader(source));
         PrintWriter writer = new PrintWriter(new BufferedOutputStream(new FileOutputStream(target)))) {
      StringBuilder period = new StringBuilder();
      reader.lines().forEach(line -> {
        String[] splitedLine = line.split(" ");
        String code = splitedLine[0];
        String time = splitedLine[1];
        if ("400".equals(code) || "500".equals(code)) {
          if (period.isEmpty()) {
            period.append(time).append(";");
          }
        } else {
          if (!period.isEmpty()) {
            period.append(time).append(";").append(System.lineSeparator());
            writer.write(period.toString());
            period.setLength(0);
          }
        }
      });
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