package ru.job4j.io;

import java.io.*;
import java.nio.file.Path;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {
  private Path mainSource;

  public void packFiles(List<Path> sources, File target) {
    try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
      for (Path source : sources) {
        Path sourceFile = mainSource.relativize(source);
        zip.putNextEntry(new ZipEntry(sourceFile.toString()));
        try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source.toFile()))) {
          zip.write(out.readAllBytes());
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private void validateInputArguments(ArgsName args) {
    File source = new File(args.get("d"));
    args.get("e");
    args.get("o");
    if (!source.isDirectory()) {
      throw new IllegalArgumentException(source + " is not a directory");
    }
  }

  public void setMainSource(Path source) {
    this.mainSource = source;
  }

  public static void main(String[] args) throws IOException {
    Zip zip = new Zip();
    ArgsName argsName = ArgsName.of(args);
    zip.validateInputArguments(argsName);
    String directory = argsName.get("d");
    String extensionToExclude = argsName.get("e");
    String output = argsName.get("o");
    zip.setMainSource(Path.of(directory));
    List<Path> allFiles = Search.search(Path.of(directory), p -> !p.toFile().getName().endsWith(extensionToExclude));
    zip.packFiles(allFiles, new File(output));
  }
}