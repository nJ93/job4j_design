package ru.job4j.io.duplicates;

import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashSet;
import java.util.Set;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
  private final Set<FileProperty> alreadyExistsFiles = new HashSet<>();
  private final Set<FileProperty> duplicateFiles = new HashSet<>();

  @Override
  public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
    FileProperty fileProperty = new FileProperty(file.toFile().length(), file.getFileName().toString(), file.toAbsolutePath().toString());
    if (!alreadyExistsFiles.add(fileProperty)) {
      duplicateFiles.add(fileProperty);
    }
    return FileVisitResult.CONTINUE;
  }

  public Set<FileProperty> getDuplicateFiles() {
    return duplicateFiles;
  }
}