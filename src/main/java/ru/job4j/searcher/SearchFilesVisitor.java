package ru.job4j.searcher;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class SearchFilesVisitor extends SimpleFileVisitor<Path> {
  private final Predicate<Path> pathPredicate;
  private final List<Path> paths = new ArrayList<>();

  public SearchFilesVisitor(Predicate<Path> predicate) {
    this.pathPredicate = predicate;
  }

  @Override
  public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
    if (pathPredicate.test(file)) {
      paths.add(file);
    }
    return FileVisitResult.CONTINUE;
  }

  public List<Path> getPaths() {
    return paths;
  }
}
