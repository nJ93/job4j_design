package ru.job4j.io;

import java.io.*;
import java.nio.file.Path;
import java.util.*;

public class CSVReader {
  public static void handle(ArgsName argsName) throws Exception {
    validateInputArguments(argsName);

    String path = argsName.get("path");
    String delimiter = argsName.get("delimiter");
    String out = argsName.get("out");
    String filter = argsName.get("filter");

    Scanner scanner = new Scanner(Path.of(path));
    scanner.useDelimiter(delimiter);
    List<String> filterValues = Arrays.stream(filter.split(",")).toList();
    List<Integer> columnIndexes = new ArrayList<>();
    StringJoiner output = new StringJoiner(System.lineSeparator());

    StringJoiner filterJoiner = new StringJoiner(delimiter);
    if (scanner.hasNextLine()) {
      String[] columns = scanner.nextLine().split(delimiter);
      for (int i = 0; i < columns.length; i++) {
        String columnName = columns[i];
        if (filterValues.contains(columnName)) {
          columnIndexes.add(i);
          filterJoiner.add(columnName);
        }
      }
    }
    output.merge(filterJoiner);

    while (scanner.hasNextLine()) {
      String[] line = scanner.nextLine().split(delimiter);
      StringJoiner lineJoiner = new StringJoiner(delimiter);
      for (int i = 0; i < line.length; i++) {
        if (columnIndexes.contains(i)) {
          lineJoiner.add(line[i]);
        }
      }
      output.merge(lineJoiner);
    }

    if ("stdout".equals(out)) {
      System.out.println(output);
    } else {
      try (PrintWriter writer = new PrintWriter(new FileWriter(out))) {
        writer.write(output.toString().concat(System.lineSeparator()));
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  private static void validateInputArguments(ArgsName argsName) {
    String path = argsName.get("path");
    argsName.get("delimiter");
    String out = argsName.get("out");
    argsName.get("filter");

    File checkFile = new File(path);
    if (!checkFile.exists() || checkFile.isDirectory()) {
      throw new IllegalArgumentException("Path to csv file is wrong");
    }
    checkFile = new File(out);
    if (!"stdout".equals(out) && (!checkFile.exists() || checkFile.isDirectory())) {
      throw new IllegalArgumentException("Wrong 'out' argument");
    }
  }

  public static void main(String[] args) throws Exception {
    ArgsName argsName = ArgsName.of(args);
    CSVReader.handle(argsName);
  }
}