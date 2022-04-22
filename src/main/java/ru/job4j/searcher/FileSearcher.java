package ru.job4j.searcher;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.util.List;
import java.util.function.Predicate;
import java.util.regex.Pattern;

public class FileSearcher {

  public void process(ArgsName argsName) throws IOException {
    validateInputArguments(argsName);

    String startDirectory = argsName.get("d");
    String searchExpression = argsName.get("n");
    String searchType = argsName.get("t");
    String outputFilePath = argsName.get("o");

    SearchFilesVisitor searchFilesVisitor;

    switch (searchType) {
      case "name":
        searchFilesVisitor = new SearchFilesVisitor(p -> p.getFileName().toString().equals(searchExpression));
        break;
      case "mask":
        PathMatcher maskMatcher = FileSystems.getDefault().getPathMatcher("glob:" + searchExpression);
        searchFilesVisitor = new SearchFilesVisitor(p -> maskMatcher.matches(p.getFileName()));
        break;
      case "regex":
        Predicate<String> regexPredicate = Pattern.compile(searchExpression).asMatchPredicate();
        searchFilesVisitor = new SearchFilesVisitor(p -> regexPredicate.test(p.getFileName().toString()));
        break;
      default:
        throw new IllegalStateException("Unexpected value: " + searchType);
    }

    Files.walkFileTree(Path.of(startDirectory), searchFilesVisitor);
    List<Path> paths = searchFilesVisitor.getPaths();

    writeResult(outputFilePath, paths);
  }

  private void validateInputArguments(ArgsName argsName) {
    String startDirectory = argsName.get("d");
    argsName.get("n");
    String searchType = argsName.get("t");
    String writeFile = argsName.get("o");

    File checkFile = new File(startDirectory);
    if (!checkFile.exists() || !checkFile.isDirectory()) {
      throw new IllegalArgumentException("First argument is not a directory");
    }

    if (!("name".equals(searchType) || "mask".equals(searchType) || "regex".equals(searchType))) {
      throw new IllegalArgumentException("Invalid search type");
    }

    checkFile = new File(writeFile);
    if (checkFile.isDirectory()) {
      throw new IllegalArgumentException("Result file is a directory");
    }

  }

  private void writeResult(String filePath, List<Path> pathList) {
    try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
      pathList.forEach(elem -> writer.write(elem.toAbsolutePath().toString().concat(System.lineSeparator())));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static void main(String[] args) throws IOException {
    FileSearcher fileSearcher = new FileSearcher();
    ArgsName argsName = ArgsName.of(args);
    fileSearcher.process(argsName);
  }
}
