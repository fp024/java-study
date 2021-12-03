package org.fp024.java.study.sort;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import static org.fp024.java.study.sort.CommonUtils.printArray;
import static org.fp024.java.study.sort.CommonUtils.swap;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
class QuickSort {
  /**
   * 파티션 <br>
   * 1. pivot에 해당하는 값의 제 위치를 찾아 배치.<br>
   * 2. pivot의 인덱스 반환.<br>
   *
   * @param array pivot을 처리할 배열
   * @param left 처리 범위의 맨 왼쪽
   * @param right 처리 범위의 맨 오른쪽
   * @return 자리를 찾은 pivot 인덱스 번호
   */
  static int partition(int[] array, final int left, final int right) {
    int pivot = array[left];
    int low = left + 1;
    int high = right;

    while (low <= high) {
      // low의 오른쪽 방향 이동
      while (low <= right && pivot >= array[low]) {
        low++;
      }

      // high의 왼쪽 방향 이동
      while (high >= left + 1 && pivot <= array[high]) {
        high--;
      }

      if (low < high) {
        swap(array, low, high);
      }

      LOGGER.info("low:{}, high:{}", low, high);
      printArray(array, left, right);
    }

    // 피벗의 제 위치 채워주기
    swap(array, left, high);

    return high;
  }

  /**
   * 퀵 정렬
   *
   * @param array 정렬할 배열
   * @param left 정렬 대상의 가장 왼쪽 인덱스
   * @param right 정렬 대상의 가장 오른쪽 인덱스
   */
  static void sort(int[] array, int left, int right) {
    LOGGER.info("left:{}, right:{}", left, right);

    if (left <= right) {
      int pivotIndex = partition(array, left, right);
      sort(array, left, pivotIndex - 1);
      sort(array, pivotIndex + 1, right);
    }

  }
}
