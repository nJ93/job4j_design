package ru.job4j.it;

import org.hamcrest.core.Is;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertThat;

public class ListUtilsTest {

  @Test
  public void whenAddBefore() {
    List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
    ListUtils.addBefore(input, 1, 2);
    assertThat(Arrays.asList(1, 2, 3), Is.is(input));
  }

  @Test(expected = IndexOutOfBoundsException.class)
  public void whenAddBeforeWithInvalidIndex() {
    List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
    ListUtils.addBefore(input, 3, 2);
  }

  @Test
  public void whenAddAfterLast() {
    List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2));
    ListUtils.addAfter(input, 2, 3);
    assertThat(Arrays.asList(0, 1, 2, 3), Is.is(input));
  }

  @Test(expected = IndexOutOfBoundsException.class)
  public void whenAddAfterWithInvalidIndex() {
    List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
    ListUtils.addAfter(input, 3, 2);
  }

  @Test
  public void whenRemoveByPredicate() {
    List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4, 5));
    ListUtils.removeIf(input, elem -> elem > 1);
    assertThat(Arrays.asList(0, 1), Is.is(input));
  }

  @Test
  public void whenReplaceByPredicate() {
    List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4));
    ListUtils.replaceIf(input, elem -> elem % 2 == 0, 5);
    assertThat(Arrays.asList(5, 1, 5, 3, 5), Is.is(input));
  }

  @Test
  public void whenRemoveAll() {
    List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4));
    List<Integer> toRemove = new ArrayList<>(Arrays.asList(0, 2, 3));
    ListUtils.removeAll(input, toRemove);
    assertThat(Arrays.asList(1, 4), Is.is(input));
  }

}