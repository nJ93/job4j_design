package ru.job4j.io;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;

public class Search {
  public static void main(String[] args) throws IOException {
    if (validateInputArguments(args)) {
      File file = new File(args[0]);
      search(file.toPath(), p -> p.toFile().getName().endsWith(args[1])).forEach(System.out::println);
    }
  }

  public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
    SearchFiles searcher = new SearchFiles(condition);
    Files.walkFileTree(root, searcher);
    return searcher.getPaths();
  }


  private static boolean validateInputArguments(String[] args) {
    if (args.length < 2) {
      throw new IllegalArgumentException("Empty or missing input arguments");
    }
    File file = new File(args[0]);
    if (!file.isDirectory()) {
      throw new IllegalArgumentException("First argument is not a directory");
    }
    return true;
  }
}