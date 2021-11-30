package org.fp024.java.study;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class FileInputOutputStreamTest {
  @TempDir private static File tempDir;

  private static final String INPUT_FILE_NAME = "in.txt";
  private static final String OUTPUT_FILE_NAME = "out.txt";

  /*
    <FileInputStream>
    FileInputStream은 파일 시스템의 파일에서 입력 바이트를 얻습니다.
    호스트 환경에 따라 사용할 수 있는 파일이 달라집니다.
    FileInputStream은 이미지 데이터와 같은 원시 바이트 스트림을 읽기 위한 것입니다.
    문자 스트림을 읽으려면 FileReader 사용을 고려하세요.

    <FileOutputStream>
    파일 출력 스트림은 파일 또는 FileDescriptor에 데이터를 쓰기 위한 출력 스트림입니다.
    파일을 사용할 수 있는지 여부는 기본 플랫폼에 따라 다릅니다.
    특히 일부 플랫폼에서는 한 번에 하나의 FileOutputStream(또는 다른 파일 쓰기 객체)만 쓰기 위해 파일을 열 수 있습니다.
    이러한 상황에서 관련 파일이 이미 열려 있으면 이 클래스의 생성자가 실패합니다.
    FileOutputStream은 이미지 데이터와 같은 원시 바이트 스트림을 쓰기 위한 것입니다.
    문자 스트림을 작성하려면 FileWriter 사용을 고려하세요.
  */
  private static final String CONTENT =
      "<FileInputStream>"
          + System.lineSeparator()
          + "A FileInputStream obtains input bytes from a file in a file system."
          + System.lineSeparator()
          + "What files are available depends on the host environment."
          + System.lineSeparator()
          + "FileInputStream is meant for reading streams of raw bytes such as image data."
          + System.lineSeparator()
          + "For reading streams of characters, consider using FileReader."
          + System.lineSeparator()
          + System.lineSeparator()
          + "<FileOutputStream>"
          + System.lineSeparator()
          + "A file output stream is an output stream for writing data to a File or to a FileDescriptor. "
          + System.lineSeparator()
          + "Whether or not a file is available or may be created depends upon the underlying platform. "
          + System.lineSeparator()
          + "Some platforms, in particular, allow a file to be opened for writing by only one FileOutputStream (or other file-writing object) at a time."
          + System.lineSeparator()
          + "In such situations the constructors in this class will fail if the file involved is already open."
          + System.lineSeparator()
          + "FileOutputStream is meant for writing streams of raw bytes such as image data."
          + System.lineSeparator()
          + "For writing streams of characters, consider using FileWriter."
          + System.lineSeparator();

  /** 테스트를 위한 임시 파일 생성 */
  @BeforeEach
  void beforeEach() throws IOException {
    File inputFile = new File(tempDir, INPUT_FILE_NAME);
    if (inputFile.exists() && !inputFile.delete()) {
      throw new IllegalStateException("이전 입력 임시파일이 남아있고, 삭제에 실패함.");
    }
    File outputFile = new File(tempDir, OUTPUT_FILE_NAME);
    if (outputFile.exists() && !outputFile.delete()) {
      throw new IllegalStateException("이전 출력 임시파일이 남아있고, 삭제에 실패함.");
    }

    Files.writeString(
        inputFile.toPath(), CONTENT, StandardCharsets.UTF_8, StandardOpenOption.CREATE_NEW);
  }

  // ===== 테스트 대상 코드 =====
  void target() {
    try (FileInputStream fis = new FileInputStream(new File(tempDir, INPUT_FILE_NAME));
        FileOutputStream fos = new FileOutputStream(new File(tempDir, OUTPUT_FILE_NAME))) {
      int c;
      while ((c = fis.read()) != -1) {
        fos.write(Character.toUpperCase(c));
      }
    } catch (IOException e) {
      throw new IllegalStateException("I/O 예외", e);
    }
  }

  // ===========================

  /** 테스트 및 검증 실행 */
  @Order(1)
  @Test
  void testTarget() throws IOException {
    LOGGER.info("testTarget() - 실행");
    // === in.txt 파일을 읽어 대문자 변환후 out.txt 파일로 저장 ===
    target();
    // =======================================================

    // target()이 대문자로 바꿔서 저장한 파일을 다시 읽어서, StringBuilder에 한바이트씩 담음.
    // 여기서 문제로 느낄 수 있는게... 한 바이트 범위로 표현할 수 없는 문자라면,..
    // 당연히 깨질 듯... 그래서 JavaDocs 권고 사항에서 보듯이 문자를 다룬다면 Reader, Writer 클래스 부류를 쓰는게 낫다.
    StringBuilder sb = new StringBuilder();
    try (FileInputStream fis = new FileInputStream(new File(tempDir, OUTPUT_FILE_NAME))) {
      int c;
      while ((c = fis.read()) != -1) {
        sb.append((char) c);
      }
    }

    assertEquals(CONTENT.toUpperCase(), sb.toString(), "대문자로 정상 변경되었는지 확인");
    LOGGER.info("\n* 대문자 변경 전 내용:\n{}\n", CONTENT);
    LOGGER.info("\n* 대문자 변경 후 내용:\n{}\n", sb);
  }

  @Order(2)
  @Test
  void testIsAlphabetic() {
    assertFalse(Character.isAlphabetic('>'));
    assertFalse(Character.isAlphabetic(' '));
    assertFalse(Character.isAlphabetic('<'));

    assertTrue(Character.isAlphabetic('a'));
    assertTrue(Character.isAlphabetic('Z'));

    assertTrue(Character.isAlphabetic('가'), "한글 결과가 false일 줄 알았는데, true이다");
    assertTrue(Character.isAlphabetic('하'));
  }
}
