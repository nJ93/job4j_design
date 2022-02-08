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
    if (args.length == 0) {
      throw new IllegalArgumentException("Empty input arguments");
    }
    String dirArgument = args[0];
    String extensionArgument = args[1];
    if (dirArgument == null || extensionArgument == null) {
      throw new IllegalArgumentException("Missing one of argument. Need to check input settings.");
    }
    File file = new File(dirArgument);
    if (!file.isDirectory()) {
      throw new IllegalArgumentException("First argument is not a directory");
    }

    search(file.toPath(), p -> p.toFile().getName().endsWith(extensionArgument)).forEach(System.out::println);
  }

  public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
    SearchFiles searcher = new SearchFiles(condition);
    Files.walkFileTree(root, searcher);
    return searcher.getPaths();
  }
}