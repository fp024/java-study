package org.fp024.java.study.finalizer;

public class BrokenAccount extends Account {

  public BrokenAccount(String name) {
    super(name);
  }

  @Override
  protected void finalize() throws Throwable {
    this.transfer(1_000_000, "일반인B");
  }
}
