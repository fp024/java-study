package org.fp024.java.study;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import lombok.extern.slf4j.Slf4j;

/**
 * 단순 시저 암호문 해독 문제
 *
 */
@Slf4j
class CaesarChiperTest {
	private final static String DECODE_DICTIONARY = createDictionary(1);
	//private final static String ENCODE_DICTIONARY = createDictionary(-1);

	@Test
	@DisplayName("암호 문자열 해독, 암호화 테스트 - 시저 암호문")
	void testDecode() {
		//
		// 주어진 사전 내용을 보면 단순하게 알파벳을 한칸씩 뒤로 회전하게 했고, 공백은 그대로 공백으로 되어있음.
		//
		// {
		// "a":"b","b":"c","c":"d","d":"e","e":"f","f":"g","g":"h","h":"i","i":"j","j":"k","k":"l"
		// ,"l":"m","m":"n","n":"o","o":"p","p":"q","q":"r","r":"s","s":"t","t":"u","u":"v","v":"w"
		// ,"w":"x","x":"y","y":"z","z":"a","
		// ":" "
		// }
		//
		LOGGER.debug(DECODE_DICTIONARY);
		
		
		//LOGGER.debug(ENCODE_DICTIONARY);

		String passwordText = "h knud xnt";
		String plainText = decode(passwordText);

		LOGGER.debug(plainText);

		assertEquals("i love you", plainText);
		
		//assertEquals(passwordText, encode(plainText));
	}	
	
	/**
	 * 사전 생성
	 * 
	 * @param shift 시프트
	 * @return JSON 형식 사전 문자열
	 */
	private static String createDictionary(int shift) {
		JsonObject jsonObject = new JsonObject();
		IntStream.rangeClosed('a', 'z').forEach(
				key -> jsonObject.addProperty(String.format("%c", key), String.format("%c", getCode(key, shift))));
		jsonObject.addProperty(" ", " ");
		return jsonObject.toString();
	}

	/**
	 * 사전이 회전 되게 만들어봄 , TODO: 오른쪽 회전만 잘됨. 왼쪽으로 회전할때를 위해 수정이 필요함.
	 */	
	private static int getCode(int key, int shift) {
		// (key - shift) 가 음수가 되는 경우를 양수로 바꿔줘야할 것 같다.
		
		int i = (key - 'a' + shift); 
		
		if ((key + shift) < 'a') {
			i = 'z' - 'a' - i;
		}
		
		return i % ('z' - 'a' + 1) + 'a';
		
	}

	@Test
	void testGetCodeShiftRight() {
		assertEquals('z', getCode('z', 0));
		assertEquals('a', getCode('a', 0));
		
		assertEquals('a', getCode('z', 1));
		assertEquals('b', getCode('a', 1));
	}
	
	@Test
	@Disabled("왼쪽으로 시프트하려면 어떻게 해야할지 식을 못바꿈..ㅠㅠ")
	void testGetCodeShiftLeft() {
		// assertEquals('y', getCode('z', -1));
		assertEquals('z', getCode('a', -1));
	}
	
	private static String decode(String passwordText) {
		return processText(passwordText, DECODE_DICTIONARY);
	}

//	private static String encode(String plainText) {
//		return processText(plainText, ENCODE_DICTIONARY);
//	}

	private static String processText(String text, String dictionary) {
		JsonElement element = JsonParser.parseString(dictionary);
		JsonObject jsonObject = element.getAsJsonObject();

		return text.chars().mapToObj(key -> jsonObject.get(String.format("%c", key)).getAsString())
				.collect(Collectors.joining());
	}

}
