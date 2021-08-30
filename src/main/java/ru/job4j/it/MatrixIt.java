package ru.job4j.it;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MatrixIt implements Iterator<Integer> {
  private final int[][] data;
  private int row = 0;
  private int column = 0;

  public MatrixIt(int[][] data) {
    this.data = data;
  }

  @Override
  public boolean hasNext() {
    if (data.length == 0) {
      return false;
    }
    for (int i = row; i < data.length; i++) {
      if (data[i].length != 0) {
        return true;
      }
    }
    return false;
  }

  @Override
  public Integer next() {
    if (!hasNext()) {
      throw new NoSuchElementException();
    }
    if (data[row].length == 0) {
      if (data[row].length == column) {
        row++;
        return next();
      }
      column++;
      return next();
    } else {
      if (data[row].length - 1 == column) {
        int value = data[row++][column];
        column = 0;
        return value;
      }
      return data[row][column++];
    }
  }
}