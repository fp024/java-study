package org.fp024.java.study;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * FileInputStream 테스트
 *
 * <p>그런데... 2021년 현재... 라팔 잘팔리고 있음 ㅎㅎ
 *
 * <p>기타 참조: https://www.baeldung.com/junit-5-temporary-directory
 *
 * @author fp024
 */
@Slf4j
class FileInputStreamTest {
  @TempDir private static File tempDir;

  static final String FILE_NAME = "data1.txt";

  static final String CONTENT =
      "라팔아"
          + System.lineSeparator()
          + "팔렸니"
          + System.lineSeparator()
          + "아니요"
          + System.lineSeparator();

  @BeforeEach
  void beforeEach() throws IOException {
    File testFile = new File(tempDir, FILE_NAME);
    testFile.delete();
    Files.writeString(
        testFile.toPath(), CONTENT, StandardCharsets.UTF_8, StandardOpenOption.CREATE_NEW);
    /*
    try (OutputStreamWriter writer =
        new OutputStreamWriter(new FileOutputStream(testFile), StandardCharsets.UTF_8)) {
      writer.write(CONTENT);
    }
    */
  }

  String main() throws IOException {
    byte[] byteArray = new byte[CONTENT.getBytes(StandardCharsets.UTF_8).length];

    try (FileInputStream fis =
        new FileInputStream(tempDir.getAbsolutePath() + File.separator + FILE_NAME)) {
      int ch;
      int i = 0;
      while ((ch = fis.read()) != -1) {
        System.out.print((char) ch); // 한글을 1바이트씩 쪼개서 보여주기 때문에 출력내용이 깨진다.
        byteArray[i++] = (byte) ch;
      }

      LOGGER.info("i:{}", i);
    }
    return new String(byteArray);
  }

  @Test
  void testExists() {
    File file = new File(tempDir.getAbsolutePath() + File.separator + FILE_NAME);
    assertTrue(file.exists());
    assertTrue(file.canWrite());
  }

  @Test
  void testMain() throws IOException {
    String mainResult = main();
    System.out.println("==============================================");
    System.out.print(mainResult);
    assertEquals(CONTENT, mainResult);
  }
}
