package ru.job4j.gc;

public class User {
  private String name;
  private int count;
  private boolean isActive;

  public User(String name, int count, boolean isActive) {
    this.name = name;
    this.count = count;
    this.isActive = isActive;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getCount() {
    return count;
  }

  public void setCount(int count) {
    this.count = count;
  }

  public boolean isActive() {
    return isActive;
  }

  public void setActive(boolean active) {
    isActive = active;
  }

  @Override
  protected void finalize() throws Throwable {
    System.out.printf("Removed %s %d %s%n", name, count, isActive);
  }

  public static void main(String[] args) {
    for (int i = 0; i < 67000; i++) {
      new User("user", i, true);
    }
  }
}
