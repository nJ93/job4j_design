package ru.job4j.cache;

import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractCache<K, V> {

  protected final Map<K, SoftReference<V>> cache = new HashMap<>();

  public void put(K key, V value) {
    cache.put(key, new SoftReference<>(value));
  }

  public V get(K key) {
    SoftReference<V> obj = cache.get(key);
    V result;
    if (obj == null) {
      result = this.load(key);
      if (result != null) {
        this.put(key, result);
      }
    } else {
      result = obj.get();
    }
    return result;
  }

  protected abstract V load(K key);
}