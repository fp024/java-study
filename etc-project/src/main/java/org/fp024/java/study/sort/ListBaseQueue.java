package org.fp024.java.study.sort;

import java.util.LinkedList;

/**
 * 리스트 기반 큐
 *
 * <p>LinkedList 를 그대로 활용해서 큐 클래스를 만들자!
 *
 * @param <T> 큐에 저장될 타입
 */
class ListBaseQueue<T> {
  private final LinkedList<T> linkedList = new LinkedList<>();

  /**
   * 큐에 데이터 넣기
   *
   * @param t 데이터
   */
  public void enqueue(T t) {
    linkedList.add(t);
  }

  /**
   * 큐에서 데이터 빼기
   *
   * @return 마지막 데이터
   */
  public T dequeue() {
    return linkedList.removeFirst();
  }

  /**
   * 큐의 마지막 데이터 얻기
   *
   * @return 마지막 데이터
   */
  public T peek() {
    return linkedList.getFirst();
  }

  /** 큐 비우기 */
  public void clear() {
    linkedList.clear();
  }

  /**
   * 큐가 비었는지의 여부
   *
   * @return 큐가 비었는지 여부
   */
  public boolean isEmpty() {
    return linkedList.isEmpty();
  }
}
