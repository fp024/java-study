package org.fp024.java.study;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class LambdaTest {
  private static int staticA;
  private int memberB;

  @BeforeEach
  void init() {
    staticA = 1;
    memberB = 1;
  }

  @DisplayName("람다식 내부에서 참조하는 static 변수를 람다식 외부 및 내부에서 변경하고 있음 -> 실행 가능")
  @Test
  void testStatic() {
    --staticA;
    int[] array = IntStream.rangeClosed(1, 3).map(i -> i + (++staticA)).toArray();
    assertArrayEquals(new int[] {2, 4, 6}, array);
    assertEquals(3, staticA);
  }

  @DisplayName("람다식 내부에서 참조하는 인스턴스 변수를 람다식 외부 및 내부에서 변경하고 있음 -> 실행 가능")
  @Test
  void testMember() {
    --memberB;
    int[] array = IntStream.rangeClosed(1, 3).map(i -> i + (++memberB)).toArray();
    assertArrayEquals(new int[] {2, 4, 6}, array);
    assertEquals(3, memberB);
  }

  @DisplayName("람다식 내부에서 참조하는 로컬변수를 람다식 내부에서 변경하고 있음 -> 컴파일 오류")
  @Test
  void testLocal01() {
    /*
    int localC = 1;
    int[] array = IntStream.rangeClosed(1, 3).map(i -> i + (++localC)).toArray();
    assertArrayEquals(new int[] {3, 5, 7}, array);
    assertEquals(4, localC);
    */
  }

  @DisplayName("람다식 내부에서 참조하는 로컬변수를 람다식 외부 및 내부에서 변경하지 않음 -> 실행 가능")
  @Test
  void testLocal02() {
    int localD = 1; // 암묵적인 final
    int[] array = IntStream.rangeClosed(1, 3).map(i -> i + (localD)).toArray();
    assertArrayEquals(new int[] {2, 3, 4}, array);
    assertEquals(1, localD);
  }

  @DisplayName("람다식 내부에서 참조하는 로컬변수를 람다식 외부에서 변경하고 있음 -> 컴파일 오류")
  @Test
  void testLocal03() {
    /*
    int localE = 1;
    localE--; // 람다식 내에서 사용하는 변수의 변경시도만 있어도 컴파일 오류 처리
    int[] array = IntStream.rangeClosed(1, 3).map(i -> i + (localE)).toArray();
    assertArrayEquals(new int[] {1, 2, 3}, array);
    assertEquals(1, localE);
    */
  }
}
