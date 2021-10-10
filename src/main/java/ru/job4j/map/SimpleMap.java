package ru.job4j.map;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleMap<K, V> implements Map<K, V> {

  private static final float LOAD_FACTOR = 0.75f;

  private int capacity = 8;

  private int count = 0;

  private int modCount = 0;

  private MapEntry<K, V>[] table = new MapEntry[capacity];

  @Override
  public boolean put(K key, V value) {
    if (count >= capacity * LOAD_FACTOR) {
      expand();
    }
    int index = indexFor(hash(key.hashCode()));
    if (table[index] != null) {
      return false;
    }
    table[index] = new MapEntry<>(key, value);
    count++;
    modCount++;
    return true;
  }

  private int hash(int hashCode) {
    return hashCode % capacity;
  }

  private int indexFor(int hash) {
    return (capacity - 1) & hash;
  }

  private void expand() {
    MapEntry<K, V>[] oldTable = table;
    capacity *= 2;
    table = new MapEntry[capacity];
    for (MapEntry<K, V> mapEntry : oldTable) {
      if (mapEntry != null) {
        table[indexFor(hash(mapEntry.key.hashCode()))] = mapEntry;
      }
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
        if (expectedModCount != modCount) {
          throw new ConcurrentModificationException();
        }
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