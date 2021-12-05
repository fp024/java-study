package org.fp024.java.study.sort;

/**
 * 기수 정렬
 *
 * <p>큐는 Java의 LinkedList를 활용하자!
 */
class RadixSort {

  private static final int BUCKET_NUM = 10;

  void sort(int[] array, int num, int maxLength) {
    // maxLength에는 정렬대상 중 가장 긴 데이터의 길이정보 전달
    @SuppressWarnings("unchecked")
    ListBaseQueue<Integer>[] buckets = new ListBaseQueue[BUCKET_NUM];

    int divfac = 1;
    int radix;

    // 총 BUCKET_NUM 개의 버킷 초기화
    for (int bi = 0; bi < BUCKET_NUM; bi++) {
      buckets[bi] = new ListBaseQueue<>();
    }

    // 가장 긴 데이터의 길이만큼 반복
    for (int pos = 0; pos < maxLength; pos++) {

      // 정렬 대상의 수만큼 반복
      for (int di = 0; di < num; di++) {
        // N번째 자리의 숫자 추출
        radix = (array[di] / divfac) % 10;

        // 추출한 숫자를 근거로 버킷에 데이터 저장
        buckets[radix].enqueue(array[di]);
      }

      // 버킷의 수만 큼 반복
      for (int bi = 0, di = 0; bi < BUCKET_NUM; bi++) {
        // 버킷에 저장된 것 순서대로 다 꺼내서 array에 저장
        while (!buckets[bi].isEmpty()) {
          array[di++] = buckets[bi].dequeue();
        }
      }

      // N번째 자리의 숫자 추출을 위한 피제수의 증가
      divfac *= 10;
    }
  }
}
