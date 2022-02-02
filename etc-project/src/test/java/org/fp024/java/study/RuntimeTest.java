package org.fp024.java.study;

import org.junit.jupiter.api.Test;

class RuntimeTest {
  private long getMegaByte(long byteUnit) {
    return byteUnit / 1024 / 1024;
  }

  private void printMemory(String label) {
    System.out.printf("%s%n", label);

    long freeMemory = Runtime.getRuntime().freeMemory();
    long maxMemory = Runtime.getRuntime().maxMemory();
    long totalMemory = Runtime.getRuntime().totalMemory();

    System.out.printf(
        "free Memory: %,dMB / max Memory: %,dMB / total Memory: %,dMB%n",
        getMegaByte(freeMemory), getMegaByte(maxMemory), getMegaByte(totalMemory));
    System.out.printf("실제 사용중인 메모리: %,dMB%n", getMegaByte(totalMemory - freeMemory));
    System.out.printf("할당되지 않았으나 앞으로 사용할 수 있는 메모리: %,dMB%n", getMegaByte(maxMemory - totalMemory));
    System.out.printf("앞으로 사용가능한 메모리: %,dMB%n", getMegaByte(maxMemory - totalMemory + freeMemory));
    System.out.println();
  }

  @Test
  void test() throws InterruptedException {
    printMemory("초기 상태...");

    long[] longArray = new long[2_141_000_000];

    printMemory("매우 큰 배열 할당 후...");

    Runtime.getRuntime().gc();
    printMemory("배열 참조를 유지한 채, 명시적 GC 수행 이후...");

    longArray = null;
    Runtime.getRuntime().gc();
    printMemory("배열 참조를 끊은 후 명시적 GC 수행...");

    longArray = new long[2_142_000_000];
    printMemory("매우 큰 배열 다시 할당 후...");
  }
}
