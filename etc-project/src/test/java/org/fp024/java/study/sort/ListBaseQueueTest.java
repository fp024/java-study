package org.fp024.java.study.sort;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ListBaseQueueTest {
  static ListBaseQueue<Integer> listBaseQueue = new ListBaseQueue<>();

  @BeforeEach
  void beforeEach() {
    listBaseQueue.clear();
  }

  @Test
  void testQueue() {
    listBaseQueue.enqueue(1);

    assertFalse(listBaseQueue.isEmpty());

    listBaseQueue.enqueue(2);
    listBaseQueue.enqueue(3);

    assertEquals(1, listBaseQueue.peek());
    assertEquals(1, listBaseQueue.dequeue());

    assertEquals(2, listBaseQueue.peek());
    assertEquals(2, listBaseQueue.dequeue());

    assertEquals(3, listBaseQueue.peek());
    assertEquals(3, listBaseQueue.dequeue());

    assertTrue(listBaseQueue.isEmpty());
  }
}
