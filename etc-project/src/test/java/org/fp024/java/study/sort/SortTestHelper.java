package org.fp024.java.study.sort;

import lombok.extern.slf4j.Slf4j;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

/** 정렬 테스트 도움 클래스 */
@Slf4j
public class SortTestHelper {
  private SortTestHelper() {
    // 싱글톤 처리를 위해 private 생성자
  }

  public static SortTestHelper getInstance() {
    return InnerInstanceClazz.instance;
  }

  public void processSort(Runnable r, int[] intArray) {
    processSort(r, intArray, IntStream.rangeClosed(1, intArray.length).toArray(), true);
  }

  public void processSort(Runnable r, int[] intArray, boolean showElementLog) {
    processSort(r, intArray, IntStream.rangeClosed(1, intArray.length).toArray(), showElementLog);
  }

  public void processSort(Runnable r, int[] intArray, int[] expectArray, boolean showElementLog) {
    if (showElementLog) {
      LOGGER.info("\n정렬 전: {}", intArray);
    }
    long start = System.nanoTime();
    r.run();
    long elapsed = System.nanoTime() - start;
    if (showElementLog) {
      LOGGER.info("\n정렬 후: {}", intArray);
    }
    LOGGER.info("수행시간: {} seconds", elapsed / 1_000_000_000.0);
    assertArrayEquals(expectArray, intArray);
  }

  /**
   * 정렬 코드의 수행 시간만 재는 메서드
   *
   * <p>연결리스트를 이용한 머지 소트에서는 기본 배열형을 안쓰다보니 기존 코드를 쓰기가 어려웠다. 시간만 재는 부분을 두고, 결과값을 리턴 받아 호출처에서 검증함.
   *
   * @param f 실행 함수
   * @param <T> 함수 입력값 타입
   * @return 정렬된 결과
   */
  public <T, R> R processSortOnlyTime(Function<T, R> f, T t) {
    long start = System.nanoTime();
    R r = f.apply(t);
    long elapsed = System.nanoTime() - start;
    LOGGER.info("수행시간: {} seconds", elapsed / 1_000_000_000.0);
    return r;
  }

  /**
   * 시간만 재는데 정렬결과를 반환하지 않는 메서드
   *
   * @param c 컨슈머
   * @param t 컨슈머 인자 (정렬할 배열)
   * @param <T> 컨슈머 인자 타입
   */
  public <T> void processSortOnlyTime(Consumer<T> c, T t) {
    long start = System.nanoTime();
    c.accept(t);
    long elapsed = System.nanoTime() - start;
    LOGGER.info("수행시간: {} seconds", elapsed / 1_000_000_000.0);
  }

  private static class InnerInstanceClazz {
    private static final SortTestHelper instance = new SortTestHelper();
  }
}
