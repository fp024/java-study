# 여러가지 연습용 테스트 코드 모음 프로젝트

### 환경
* Java 17+
* Gradle 7.2+

### 실행

* Appplication 실행 - `App.mamin() 실행`
  * 이 클래스에서는 System.in으로 받아서 처리할만한 코드를 넣어보자. 	

  ```bash
  gradlew -q --console plain run
  ```

* 테스트 실행

  ```bash
  gradlew test --tests *테스트_클래스_명.테스트_메서드_명
  ```