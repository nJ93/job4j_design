package ru.job4j.map;

import org.junit.Assert;
import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;

public class SimpleMapTest {
  SimpleMap<Integer, String> simpleMap = new SimpleMap<>();

  @Test
  public void whenPutNotExistsThenTrue() {
    Assert.assertTrue(simpleMap.put(1, "one"));
  }

  @Test
  public void whenPutAlreadyExistsThenFalse() {
    simpleMap.put(1, "one");
    Assert.assertFalse(simpleMap.put(1, "two"));
  }

  @Test
  public void whenGetExistsThenEquals() {
    simpleMap.put(1, "one");
    simpleMap.put(2, "two");
    Assert.assertEquals("one", simpleMap.get(1));
    Assert.assertEquals("two", simpleMap.get(2));
  }

  @Test
  public void whenGetNotExistsThenNull() {
    Assert.assertNull(simpleMap.get(1));
  }

  @Test
  public void whenRemoveExistsThenTrue() {
    simpleMap.put(1, "one");
    Assert.assertTrue(simpleMap.remove(1));
  }

  @Test
  public void whenRemoveNotExistsThenFalse() {
    simpleMap.put(1, "one");
    Assert.assertFalse(simpleMap.remove(2));
  }

  @Test
  public void whenCheckIterator() {
    simpleMap.put(1, "one");
    simpleMap.put(2, "two");
    simpleMap.put(3, "three");
    Iterator<Integer> iterator = simpleMap.iterator();
    Assert.assertTrue(iterator.hasNext());
    iterator.next();
    Assert.assertTrue(iterator.hasNext());
    iterator.next();
    Assert.assertTrue(iterator.hasNext());
    iterator.next();
  }

  @Test(expected = ConcurrentModificationException.class)
  public void whenAddAfterGetIteratorThenMustBeException() {
    simpleMap.put(1, "one");
    Iterator<Integer> iterator = simpleMap.iterator();
    simpleMap.put(2, "two");
    iterator.next();
  }
}