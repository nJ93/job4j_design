package ru.job4j.cache;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

public class Emulator {
  public static void main(String[] args) throws IOException {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    AbstractCache<String, String> cache = null;
    showEnterPhrases();
    String enter = "";
    String result;
    while (!enter.equals("Exit")) {
      enter = reader.readLine();
      switch (enter) {
        case "1":
          System.out.println("Type path to directory");
          enter = reader.readLine();
          if (!checkPathIsDirectory(enter)) {
            showEnterPhrases();
            continue;
          }
          cache = new DirFileCache(enter);
          showEnterPhrases();
          continue;
        case "2":
          if (cache == null) {
            System.out.println("Set directory to work\n");
            showEnterPhrases();
            continue;
          }
          System.out.println("Enter file name");
          enter = reader.readLine();
          result = cache.load(enter);
          if (result != null) {
            cache.put(enter, result);
            System.out.println("File loaded in cache\n");
          }
          showEnterPhrases();
          continue;
        case "3":
          if (cache == null) {
            System.out.println("Set directory to work\n");
            showEnterPhrases();
            continue;
          }
          System.out.println("Enter file name");
          enter = reader.readLine();
          result = cache.get(enter);
          if (result != null) {
            System.out.println(result + System.lineSeparator());
          }
          showEnterPhrases();
          continue;
        default:
          System.out.println("Wrong enter\n");
      }

    }

  }

  private static void showEnterPhrases() {
    List<String> enterPhrases = Arrays.asList(
            "1. Set directory",
            "2. Load file in cache",
            "3. Get file from cache",
            "Type 'Exit' to stop the program");
    enterPhrases.forEach(System.out::println);
  }

  private static boolean checkPathIsDirectory(String path) {
    boolean result = true;
    if (!Files.isDirectory(Path.of(path))) {
      result = false;
      System.out.println("Wrong path to directory\n");
    }
    return result;
  }
}
