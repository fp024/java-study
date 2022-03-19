package org.fp024.java.study.finalizer;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

/*
 * https://www.youtube.com/watch?v=6kNzL1bl1kI 가
 * 무슨의미인가 해서 따라해보았다. 😄😄😄
 *
 * 이펙티브 자바 3판 40쪽
 */
class AccountTest {

  @Test
  void 일반_사람() {
    Account account = new Account("일반인A");
    account.transfer(100, "일반인B");
  }

  @Test
  void 차단된_사람() {
    assertThrows(
        IllegalArgumentException.class,
        () -> {
          Account account = new Account("차단인물");
          account.transfer(100, "일반인B");
        });
  }

  @Test
  void 만들다만_객체() throws InterruptedException {
    Account account = null;

    try {
      account = new BrokenAccount("차단인물");
    } catch (Exception e) {
      System.out.println("차단인물은 안됨!!!");
    }

    System.gc();
    Thread.sleep(3000L);

    assertNull(account, "null은 유지되었지만, BrokenAccount의 transfer메서드는 실행되었다.");
  }
}
