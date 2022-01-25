package ru.job4j.io;

import java.io.FileInputStream;
import java.io.IOException;

public class EvenNumberFile {
  public static void main(String[] args) {
    try (FileInputStream input = new FileInputStream("even.txt")) {
      StringBuilder text = new StringBuilder();
      int read;
      while ((read = input.read()) != -1) {
        text.append((char) read);
      }
      String[] numbers = text.toString().split(System.lineSeparator());
      for (String number : numbers) {
        System.out.println(Integer.parseInt(number) % 2 == 0);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
