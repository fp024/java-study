package org.fp024.java.study.sort;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class QuickSortTest {
  private final QuickSort quickSort = new QuickSort();
  private static final SortTestHelper util = SortTestHelper.getInstance();
  private static final int[] ORIGIN_INT_ARRAY =
      RandomArrayUtil.createRandomArrayWithoutSet(1_000_000);

  @BeforeEach
  void beforeEach() {
    quickSort.setShowLog(false);
    quickSort.setDisableMedianPivot(false);
  }

  @Test
  void testPartition() {
    quickSort.setShowLog(true);
    //             0  1  2  3  4  5  6  7  8
    int[] array = {5, 1, 3, 7, 9, 2, 4, 6, 8};

    // 중간 값 적용이 있어 무조건 첫번째 인덱스를 pivot을 선택한 것과 비교하여 흐름이 바뀌였다.
    //  {8, 1, 3, 7, 9, 2, 4, 6, 5}
    //               l           h
    //  {8, 1, 3, 7, 5, 2, 4, 6, 9}
    //                        h  l
    //  {6, 1, 3, 7, 5, 2, 4, 8, 9}
    //   p
    int pivotIndex = quickSort.partition(array, 0, array.length - 1);

    int[] expect = {6, 1, 3, 7, 5, 2, 4, 8, 9};

    assertArrayEquals(expect, array);
    assertEquals(7, pivotIndex);
  }

  @Test
  void testSort() {
    quickSort.setShowLog(true);
    //             0  1  2  3  4  5  6  7  8
    int[] array = {5, 1, 3, 7, 9, 2, 4, 6, 8};

    quickSort.sort(array, 0, array.length - 1);

    int[] expect = {1, 2, 3, 4, 5, 6, 7, 8, 9};

    assertArrayEquals(expect, array);
  }

  @Test
  void testSort_SameValue() {
    quickSort.setShowLog(true);
    //             0  1  2  3  4  5  6  7  8
    int[] array = {3, 3, 3};

    quickSort.sort(array, 0, array.length - 1);

    int[] expect = {3, 3, 3};

    assertArrayEquals(expect, array);
  }

  /**
   * 예전 Java 프로그래머를 위한 알고리즘과 자료구조 할 때, 뭔가 더 빡시게 한 것 같다 ㅠㅠ, 지금 다시 보려니 기억이 잘 안남 ㅠㅠ <br>
   * https://github.com/fp024/java-programmer-no-tameno-algorithm-to-data-kouzou/blob/master/src/test/java/org/fp024/study/algorithm/part04/chapter14/QuickSortTest.java
   */
  @Test
  void testQuickSort() {
    // 정렬할 랜덤 배열을 한번만 만들고, 그것을 복제해서 정렬 테스트를 수행하자.
    int[] intArray = Arrays.copyOf(ORIGIN_INT_ARRAY, ORIGIN_INT_ARRAY.length);
    util.processSort(() -> quickSort.sort(intArray, 0, intArray.length - 1), intArray, false);
  }

  /** 순서대로 정렬된 배열에서 pivot 값을 치우친 값으로 사용하면 재귀가 많이 일어나서 1만개 정렬를 못버틴다. 스택오버플로우남... */
  @DisplayName("순서대로 정렬되고, 중간값 사용하지 않을 때...")
  @Test
  void testQuickSort_WorstCase_With_MedianValue() {
    quickSort.setDisableMedianPivot(true);
    int[] intArray = IntStream.rangeClosed(1, 5_000).toArray();
    util.processSort(() -> quickSort.sort(intArray, 0, intArray.length - 1), intArray, false);
  }

  /** 순서대로 정렬된 배열에서 pivot 값을 중간 값으로 사용하면 100만개도 정렬도 버틴다. */
  @DisplayName("순서대로 정렬되고, 중간값 사용할 때...")
  @Test
  void testQuickSort_WorstCase_Without_MedianValue() {
    int[] intArray = IntStream.rangeClosed(1, 1_000_000).toArray();
    util.processSort(() -> quickSort.sort(intArray, 0, intArray.length - 1), intArray, false);
  }
}
