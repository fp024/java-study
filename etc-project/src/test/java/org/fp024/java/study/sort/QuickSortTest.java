package org.fp024.java.study.sort;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class QuickSortTest {

  @Test
  void testPartition() {
    //             0  1  2  3  4  5  6  7  8
    int[] array = {5, 1, 3, 7, 9, 2, 4, 6, 8};

    int pivotIndex = QuickSort.partition(array, 0, array.length - 1);

    int[] expect = {2, 1, 3, 4, 5, 9, 7, 6, 8};

    assertArrayEquals(expect, array);
    assertEquals(4, pivotIndex);
  }

  @Test
  void testSort() {
    //             0  1  2  3  4  5  6  7  8
    int[] array = {5, 1, 3, 7, 9, 2, 4, 6, 8};

    QuickSort.sort(array, 0, array.length - 1);

    int[] expect = {1, 2, 3, 4, 5, 6, 7, 8, 9};

    assertArrayEquals(expect, array);
  }

  @Test
  void testSort_SameValue() {
    //             0  1  2  3  4  5  6  7  8
    int[] array = {3, 3, 3};

    QuickSort.sort(array, 0, array.length - 1);

    int[] expect = {3, 3, 3};

    assertArrayEquals(expect, array);
  }
}
