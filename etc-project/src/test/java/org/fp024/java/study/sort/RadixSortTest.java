package org.fp024.java.study.sort;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.fp024.java.study.sort.CommonUtils.printArray;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class RadixSortTest {
  private final RadixSort radixSort = new RadixSort();
  private static final SortTestHelper util = SortTestHelper.getInstance();
  private static final int[] ORIGIN_INT_ARRAY =
      RandomArrayUtil.createRandomArrayWithoutSet(10_000_000);

  @BeforeEach
  void beforeEach() {}

  @Test
  void testSort() {
    int[] array = new int[] {13, 212, 14, 7141, 10987, 6, 15};

    radixSort.sort(array, array.length, 5);

    printArray(array, 0, array.length - 1);

    assertArrayEquals(new int[] {6, 13, 14, 15, 212, 7141, 10987}, array);
  }

  /*
    테스트시 에러가 났을 때, 스텍 트레이스가 제대로 안나타나면 Gradle 테스트 리포트 html 까지 봐 보자!
  , heap 사이즈를 더 늘리면 99_999_999 도 될 것 같다.
  */
  @DisplayName("최대 8자리 정수가 담긴 랜덤 배열 정렬")
  @Test
  void testSort_BigRandomArray() {
    int[] intArray = Arrays.copyOf(ORIGIN_INT_ARRAY, ORIGIN_INT_ARRAY.length);
    util.processSort(() -> radixSort.sort(intArray, intArray.length, 8), intArray, false);
  }
}
