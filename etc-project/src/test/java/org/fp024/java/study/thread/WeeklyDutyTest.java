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
    while (studentList.size() > 0) {
      Runnable runnable = new WeeklyDuty(classSelectThread, studentList);
      new Thread(runnable).start();
    }
  }

  void print(List<String> studentList) {
    int index = (int) (Math.random() * 10);
    synchronized (this) {
      // 동기화 블록 안에서, 인덱스가 초과하는지에 대해, 배열의 크기를 검사해주면 try 블록은 없어도 되겠다.
      // 30번 반복안에서 반드시 10명의 학생을 뽑아내지 못 할 수 있다.
      // 30번 돌리는 반복을 List 가 0이 될때 반복을 종료하게 바꾸면 항상 10개를 뽑아낼 수 있다.
      if (index < studentList.size()) {
        System.out.println(studentList.get(index));
        studentList.remove(index);
      }
    }
  }
}
