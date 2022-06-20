package ru.job4j.gc.ref;

import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class SoftWeakExample {
  private static void safeSoftUsage(int index) {
    List<SoftReference<String>> listSoftRef = new ArrayList<>();
    List<WeakReference<String>> listWeakRef = new ArrayList<>();
    for (int i = 0; i < 1000; i++) {
      listSoftRef.add(new SoftReference<>("String " + i));
      listWeakRef.add(new WeakReference<>("String " + i));
    }

    String softStr = listSoftRef.get(index).get();
    if (softStr != null) {
      System.out.println("Safe usage soft " + softStr);
    }

    String weakStr = listWeakRef.get(index).get();
    if (weakStr != null) {
      System.gc();
      String nextWeak = listWeakRef.get(index + 1).get();
      if (nextWeak == null) {
        System.out.println("Safe usage weak " + weakStr);
      } else {
        System.out.println("GC is not worked");
      }
    }
  }

  public static void main(String[] args) {
    safeSoftUsage(22);

  }

}
