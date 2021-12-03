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
}
