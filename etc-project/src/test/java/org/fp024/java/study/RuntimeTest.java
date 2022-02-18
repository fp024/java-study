package org.fp024.java.study;

import java.util.List;
import java.util.stream.IntStream;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
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
    System.out.printf("앞으로 사용 가능한 메모리: %,dMB%n", getMegaByte(maxMemory - totalMemory + freeMemory));
    System.out.println();
  }

  @Getter
  @Setter
  @RequiredArgsConstructor
  @ToString
  class PersonalInfo {
    private final String pid;
    private final String name;
    private final String phoneNumber;
  }

  private List<PersonalInfo> makePersonalList() {
    return IntStream.rangeClosed(1, 1_000_000)
        .mapToObj(
            i -> {
              return new PersonalInfo("000000" + i, "홍길동" + i, "123-123-" + i);
            })
        .toList();
  }

  @Test
  void test() {
    printMemory("초기 상태...");

    @SuppressWarnings("unused")
    List<PersonalInfo> personalInfoList = makePersonalList();

    printMemory("개인정보 목록 생성해서 할당 후...");

    Runtime.getRuntime().gc();
    printMemory("개인정보 목록 참조를 유지한 채, 명시적 GC 수행 이후...");

    personalInfoList = null;
    Runtime.getRuntime().gc();
    printMemory("개인정보 목록 참조를 끊은 후 명시적 GC 수행...");

    personalInfoList = makePersonalList();
    printMemory("개인정보 목록 다시 생성해서 할당 후...");
  }
}
