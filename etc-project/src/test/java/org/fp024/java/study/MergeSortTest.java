package org.fp024.java.study;

import lombok.extern.slf4j.Slf4j;
import org.fp024.java.study.sort.MergeSort;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

@Slf4j
class MergeSortTest {
  @Test
  void testMergeSort() {
    int[] array = new int[] {3, 2, 4, 1, 7, 6, 5};

    MergeSort.sort(array, 0, array.length - 1);

    LOGGER.info(Arrays.toString(array));

    assertArrayEquals(new int[] {1, 2, 3, 4, 5, 6, 7}, array);
  }
}
