package ru.job4j.collection;

import java.util.*;

public class SimpleLinkedList<E> implements List<E> {
  private Node<E> first;
  private Node<E> last;
  private int size;
  private int modCount;

  @Override
  public void add(E value) {
    Node<E> newNode = new Node<>(last, value, null);
    Node<E> prevLast = last;
    last = newNode;
    if (prevLast == null) {
      first = newNode;
    } else {
      prevLast.next = newNode;
    }
    size++;
    modCount++;
  }

  @Override
  public E get(int index) {
    Objects.checkIndex(index, size);
    return getNodeByIndex(index).item;
  }

  private Node<E> getNodeByIndex(int index) {
    Node<E> node;
    if (index < size / 2) {
      node = first;
      for (int i = 0; i < index; i++) {
        node = node.next;
      }
    } else {
      node = last;
      for (int i = size - 1; i > index; i--) {
        node = node.prev;
      }
    }
    return node;
  }

  @Override
  public Iterator<E> iterator() {
    return new Iterator<E>() {
      int expectedModCount = modCount;
      int index = 0;
      Node<E> node = first;

      @Override
      public boolean hasNext() {
        return index < size;
      }

      @Override
      public E next() {
        if (!hasNext()) {
          throw new NoSuchElementException();
        }
        if (expectedModCount != modCount) {
          throw new ConcurrentModificationException();
        }
        E value = node.item;
        node = node.next;
        index++;
        return value;
      }
    };
  }

  private static class Node<E> {
    E item;
    SimpleLinkedList.Node<E> next;
    SimpleLinkedList.Node<E> prev;

    Node(SimpleLinkedList.Node<E> prev, E element, SimpleLinkedList.Node<E> next) {
      this.item = element;
      this.next = next;
      this.prev = prev;
    }
  }
}