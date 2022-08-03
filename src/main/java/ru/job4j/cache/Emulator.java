package ru.job4j.cache;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;

public class Emulator {

  private static final int SET_DIRECTORY = 1;
  private static final int LOAD_FILE = 2;
  private static final int GET_FILE = 3;
  private static final String WRONG_ENTER_MSG = "Wrong enter\n";
  private static final String SET_DIRECTORY_TO_WORK_MSG = "Set directory to work\n";
  private static final String ENTER_FILE_NAME_MSG = "Enter file name\n";
  private static final String TYPE_PATH_TO_DIR_MSG = "Type path to directory\n";
  private static final String FILE_LOADED_IN_CACHE_MSG = "File loaded in cache\n";
  private static final String SHOW_ENTER_PHRASES = """
          1. Set directory
          2. Load file in cache
          3. Get file from cache
          Type 'Exit' to stop the program
          """;

  public static void main(String[] args) {
    try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
      AbstractCache<String, String> cache = null;
      System.out.println(SHOW_ENTER_PHRASES);
      String enter = "";
      String result;
      while (!enter.equals("Exit")) {
        enter = reader.readLine();
        int numberEnter;
        try {
          numberEnter = Integer.parseInt(enter);
        } catch (NumberFormatException e) {
          System.out.println(WRONG_ENTER_MSG);
          continue;
        }
        switch (numberEnter) {
          case SET_DIRECTORY:
            System.out.println(TYPE_PATH_TO_DIR_MSG);
            enter = reader.readLine();
            if (!checkPathIsDirectory(enter)) {
              System.out.println(SHOW_ENTER_PHRASES);
              continue;
            }
            cache = new DirFileCache(enter);
            System.out.println(SHOW_ENTER_PHRASES);
            continue;
          case LOAD_FILE:
            if (cache == null) {
              System.out.println(SET_DIRECTORY_TO_WORK_MSG);
              System.out.println(SHOW_ENTER_PHRASES);
              continue;
            }
            System.out.println(ENTER_FILE_NAME_MSG);
            enter = reader.readLine();
            result = cache.get(enter);
            if (result != null) {
              System.out.println(FILE_LOADED_IN_CACHE_MSG);
            }
            System.out.println(SHOW_ENTER_PHRASES);
            continue;
          case GET_FILE:
            if (cache == null) {
              System.out.println(SET_DIRECTORY_TO_WORK_MSG);
              System.out.println(SHOW_ENTER_PHRASES);
              continue;
            }
            System.out.println(ENTER_FILE_NAME_MSG);
            enter = reader.readLine();
            result = cache.get(enter);
            if (result != null) {
              System.out.println(result);
            }
            System.out.println(SHOW_ENTER_PHRASES);
            continue;
          default:
            System.out.println(WRONG_ENTER_MSG);
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
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
