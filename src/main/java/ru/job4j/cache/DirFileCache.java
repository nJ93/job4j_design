package ru.job4j.cache;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.stream.Collectors;

public class DirFileCache extends AbstractCache<String, String> {

  private final String cachingDir;

  public DirFileCache(String cachingDir) {
    this.cachingDir = cachingDir;
  }

  @Override
  protected String load(String key) {
    String result = null;
    try (BufferedReader reader = new BufferedReader(new FileReader(cachingDir + File.separator + key))) {
      result = reader.lines().collect(Collectors.joining("\n"));
    } catch (IOException e) {
      e.printStackTrace();
    }
    return result;
  }
}