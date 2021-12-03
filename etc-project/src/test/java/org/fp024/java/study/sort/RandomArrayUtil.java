package org.fp024.java.study.sort;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

public class RandomArrayUtil {
  private static final Random RANDOM = new Random();

  /** 중복 검사를 `Set`에 넣고 검사하는 식으로 해서, 대용량으로는 못쓴다. */
  public static int[] createRandomArray(final int maxValue) {
    int[] intArray = new int[maxValue];
    Set<Integer> set = new HashSet<>();
    int i = 0;
    while (i < maxValue) {
      int randomValue = RANDOM.nextInt(maxValue) + 1;
      if (set.add(randomValue)) {
        intArray[i++] = randomValue;
      }
    }
    return intArray;
  }

  /** 정렬된 배열을 섞어도 잘 섞이게 되는 것 같다. */
  public static int[] createRandomArrayWithoutSet(final int maxValue) {
    List<Integer> list = IntStream.rangeClosed(1, maxValue).boxed().collect(toList());
    Collections.shuffle(list);
    return list.stream().mapToInt(Integer::intValue).toArray();
  }
}
