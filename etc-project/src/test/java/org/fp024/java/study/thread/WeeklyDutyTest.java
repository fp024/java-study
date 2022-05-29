package org.fp024.java.study.thread;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class WeeklyDutyTest {
  @Test
  void testClassSelectThread() {
    ClassSelectThread.main(null);
  }
}

class WeeklyDuty implements Runnable {
  private final ClassSelectThread classSelectThread;
  private final List<String> studentList;

  WeeklyDuty(ClassSelectThread classSelectThread, List<String> studentList) {
    this.classSelectThread = classSelectThread;
    this.studentList = studentList;
  }

  @Override
  public void run() {
    classSelectThread.print(studentList);
  }
}

class ClassSelectThread {
  public static void main(String[] args) {
    ClassSelectThread classSelectThread = new ClassSelectThread();

    List<String> studentList = new ArrayList<>();
    studentList.add("박신혜");
    studentList.add("이영애");
    studentList.add("박지성");
    studentList.add("김수지");
    studentList.add("차승범");
    studentList.add("김지우");
    studentList.add("김미경");
    studentList.add("서영민");
    studentList.add("백설현");
    studentList.add("최영화");

    System.out.println("주번 리스트");
    for (int i = 0; i < 30; i++) {
      Runnable runnable = new WeeklyDuty(classSelectThread, studentList);
      new Thread(runnable).start();
    }
  }

  void print(List<String> studentList) {
    int index = (int) (Math.random() * 10);
    try {
      synchronized (this) {
        System.out.println(studentList.get(index));
        studentList.remove(index);
      }
    } catch (Exception e) {
      // 배열에서 이미 지워진 요소를 지우려할 때, 예외가 발생하는데..
      // 이 부분에 대해서는 따로 처리해줘야할 것이 없음.
    }
  }
}
