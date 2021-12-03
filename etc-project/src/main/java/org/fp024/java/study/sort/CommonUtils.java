package org.fp024.java.study.sort;

/**
 * 공통 유틸리티 메서드<br>
 * 중복으로 사용하는 메서드를 몰아두자!
 */
class CommonUtils {
  static void printArray(int[] array, int start, int end) {
    for (int i = start; i <= end; i++) {
      System.out.printf("%d ", array[i]);
    }
    System.out.println();
  }

  static void swap(int[] array, int aIndex, int bIndex) {
    int temp = array[aIndex];
    array[aIndex] = array[bIndex];
    array[bIndex] = temp;
  }

  /**
   * 특정 배열 범위의 중간 값 찾기
   *
   * @param array   중간값을 찾으려는 배열
   * @param left    시작 범위 인덱스
   * @param right   종료 범위 인덱스
   * @return 중간 값
   */
  static int getMedianIndex(int[] array, int left, int right) {
    if (right - left < 2) {
      return left;
    }

    int mid = (left + right) / 2;
    int aValue = array[left];
    int bValue = array[mid];
    int cValue = array[right];

    if (aValue > bValue && cValue > aValue) {
      return left;
    } else if (cValue > bValue && bValue > aValue) {
      return mid;
    } else {
      return right;
    }
  }
}
