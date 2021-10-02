package ru.job4j.map;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleMap<K, V> implements Map<K, V> {
  public static void main(String[] args) {
//    SimpleMap<Integer, String> simpleMap = new SimpleMap();
//    simpleMap.put(1, "asda");
//    String s = simpleMap.get(0);
//    System.out.println(s);
//    java.util.Map<Integer, String> asd = new HashMap<>();
//    asd.put(1, "fdsf");
//    String sd = asd.get(0);
//    System.out.println(sd);
//    float a = 1.0F;
    int a = 1;
    int b = 2;
    System.out.println((float) a / b);
  }

  private static final float LOAD_FACTOR = 0.75f;

  private int capacity = 8;

  private int count = 0;

  private int modCount = 0;

  private MapEntry<K, V>[] table = new MapEntry[capacity];

  @Override
  public boolean put(K key, V value) {
    if ((float) count / capacity >= LOAD_FACTOR) {
      expand();
    }
    int index = indexFor(hash(key.hashCode()));
    if (table[index] != null) {
      return false;
    } else {
      table[index] = new MapEntry<>(key, value);
      count++;
      modCount++;
      return true;
    }
  }

  private int hash(int hashCode) {
    return hashCode % capacity;
  }

  private int indexFor(int hash) {
    return (capacity - 1) & hash;
  }

  private void expand() {
    MapEntry<K, V>[] oldTable = table;
    capacity = capacity << 1;
    table = new MapEntry[capacity];
    for (MapEntry<K, V> mapEntry : oldTable) {
      table[indexFor(hash(mapEntry.key.hashCode()))] = mapEntry;
    }
  }

  @Override
  public V get(K key) {
    int index = indexFor(hash(key.hashCode()));
    if (table[index] != null && key.equals(table[index].key)) {
      return table[index].value;
    }
    return null;
  }

  @Override
  public boolean remove(K key) {
    int index = indexFor(hash(key.hashCode()));
    if (table[index] != null && key.equals(table[index].key)) {
      table[index] = null;
      count--;
      modCount++;
      return true;
    }
    return false;
  }

  @Override
  public Iterator<K> iterator() {
    return new Iterator<K>() {
      private final int expectedModCount = modCount;
      private int index = 0;

      @Override
      public boolean hasNext() {
        for (int i = index; i < table.length; i++) {
          if (table[i] != null) {
            index = i;
            return true;
          }
        }
        return false;
      }

      @Override
      public K next() {
        if (!hasNext()) {
          throw new NoSuchElementException();
        }
        if (expectedModCount != modCount) {
          throw new ConcurrentModificationException();
        }
        return table[index].key;
      }
    };
  }

  private static class MapEntry<K, V> {
    K key;
    V value;

    public MapEntry(K key, V value) {
      this.key = key;
      this.value = value;
    }
  }
}