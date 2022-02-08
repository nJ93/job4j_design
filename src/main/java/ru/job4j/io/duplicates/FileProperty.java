package ru.job4j.io.duplicates;

import java.util.Objects;

public class FileProperty {

  private long size;
  private String name;
  private String path;

  public FileProperty(long size, String name, String path) {
    this.size = size;
    this.name = name;
    this.path = path;
  }

  public long getSize() {
    return size;
  }

  public void setSize(long size) {
    this.size = size;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPath() {
    return path;
  }

  public void setPath(String path) {
    this.path = path;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    FileProperty that = (FileProperty) o;
    return size == that.size && Objects.equals(name, that.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(size, name);
  }

  @Override
  public String toString() {
    return "size=" + size
            + ", name='" + name + '\''
            + ", path='" + path + '\'';
  }
}