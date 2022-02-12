package ru.job4j.io.duplicates;

import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
  private final List<FileProperty> alreadyExistsFiles = new ArrayList<>();

  @Override
  public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
    FileProperty fileProperty = new FileProperty(file.toFile().length(), file.getFileName().toString(), file.toAbsolutePath().toString());
    alreadyExistsFiles.add(fileProperty);
    return FileVisitResult.CONTINUE;
  }

  public List<FileProperty> getDuplicateFiles() {
    return alreadyExistsFiles
            .stream()
            .filter(elem -> alreadyExistsFiles.indexOf(elem) != alreadyExistsFiles.lastIndexOf(elem))
            .collect(Collectors.toList());
  }
}