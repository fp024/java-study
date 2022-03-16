package org.fp024.java.study.finalizer;

public class Account {

  private String name;

  public Account(String name) {
    this.name = name;
    if ("차단인물".equals(this.name)) {
      throw new IllegalArgumentException("차단인물 접근 차단");
    }
  }

  public void transfer(int amount, String to) {
    System.out.printf("transfer %d from %s to %s ", amount, this.name, to);
  }
}
