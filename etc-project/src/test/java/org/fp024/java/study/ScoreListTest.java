package org.fp024.java.study;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ScoreListTest {
  private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
  private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
  private final PrintStream originalOut = System.out;
  private final PrintStream originalErr = System.err;

  @TempDir private static File tempDir;

  private static final String FILE_NAME = "data1.txt";

  private static final String CONTENT =
      "학생1 98"
          + System.lineSeparator()
          + "학생2 92"
          + System.lineSeparator()
          + "학생3 18"
          + System.lineSeparator()
          + "학생4 98"
          + System.lineSeparator();

  /** 테스트를 위한 임시 파일 생성 */
  @BeforeEach
  void beforeEach() throws IOException {
    File testFile = new File(tempDir, FILE_NAME);
    if (testFile.exists()) {
      if (!testFile.delete()) {
        throw new IllegalStateException("이전 임시파일이 남아있고, 삭제에 실패함.");
      }
    }

    Files.writeString(
        testFile.toPath(), CONTENT, StandardCharsets.UTF_8, StandardOpenOption.CREATE_NEW);

    // System.out.println 의 출력 캡쳐 설정
    System.setOut(new PrintStream(outContent));
    System.setErr(new PrintStream(errContent));
  }

  /** System.in, System.out 원상태로 복구 */
  @AfterEach
  void afterEach() {
    System.setOut(originalOut);
    System.setErr(originalErr);
  }

  // ===== 테스트 대상 코드 =====
  void scoreList() {
    int sum = 0;
    int cnt = 0;

    // (1) 텍스트 파일을 다루므로 Reader 부류 클래스 들을 사용
    // (2) 자동으로 close 되도록 Try-with-resources 구문 형식으로 사용하기
    try (BufferedReader reader = new BufferedReader(new FileReader(new File(tempDir, FILE_NAME)))) {
      String s;
      while ((s = reader.readLine()) != null) {
        String[] data = s.split("\\s+"); // (3) 공백을 기준으로 문자열을 쪼개기 위해서는 정규식 패턴으로 입력하기
        sum += Integer.parseInt(data[1]);
        cnt++;
      }
      System.out.printf(
          "average: %.2f%n", (float) sum / cnt); // (4) 정수 나눗셈을 하면 소수부가 버려지므로, (float)로 형변환 후 나눔
    } catch (FileNotFoundException e) {
      System.out.println("파일 없음");
    } catch (IOException e) {
      System.out.println("입출력 오류");
    }
  }
  // ===========================

  /** 테스트 및 검증 실행 */
  @Test
  void testLoadScoreList() {
    scoreList();
    assertEquals("average: 76.50" + System.lineSeparator(), outContent.toString());
  }
}
