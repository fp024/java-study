package org.fp024.java.study.sort;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import static org.fp024.java.study.sort.CommonUtils.printArray;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
class MergeSort {
  static void sort(int[] array, int left, int right) {
    int mid = (left + right) / 2;

    if (left < right) {
      sort(array, left, mid);
      sort(array, mid + 1, right);
      merge(array, left, mid, right);
    }
  }

  static void merge(int[] array, final int left, final int mid, final int right) {
    System.out.print("병합 전 왼쪽 => ");
    printArray(array, left, mid);

    System.out.print("병합 전 오른쪽 => ");
    printArray(array, mid + 1, right);

    int[] sortedArray = new int[right + 1];

    int leftAreaIndex = left;
    int rightAreaIndex = mid + 1;
    int sortedIndex = left;

    // 1. 정렬 수행
    while (leftAreaIndex <= mid && rightAreaIndex <= right) {
      if (array[leftAreaIndex] > array[rightAreaIndex]) {
        sortedArray[sortedIndex] = array[rightAreaIndex++];
      } else {
        sortedArray[sortedIndex] = array[leftAreaIndex++];
      }
      sortedIndex++;
    }

    // 2. 왼쪽 또는 오른쪽 남은 부분 정렬 배열에 포함시키기
    if (leftAreaIndex > mid) { // 배열의 앞부분이 모두 옮겨짐
      for (int i = rightAreaIndex; i <= right; i++, sortedIndex++) {
        sortedArray[sortedIndex] = array[i];
      }
    } else { // 배열의 뒷 부분이 모두 옮겨짐
      for (int i = leftAreaIndex; i <= mid; i++, sortedIndex++) {
        sortedArray[sortedIndex] = array[i];
      }
    }

    // 3. 정렬된배열을 그대로 원본 배열에 복사
    System.arraycopy(sortedArray, left, array, left, right + 1 - left);

    System.out.print("병합 후 => ");
    printArray(array, left, right);
  }
}
