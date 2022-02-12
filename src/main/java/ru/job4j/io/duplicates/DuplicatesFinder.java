package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Set;

public class DuplicatesFinder {
  public static void main(String[] args) throws IOException {
    DuplicatesVisitor visitor = new DuplicatesVisitor();
    Files.walkFileTree(Path.of(""), visitor);
    List<FileProperty> duplicateFiles = visitor.getDuplicateFiles();
    duplicateFiles.forEach(System.out::println);
  }
}