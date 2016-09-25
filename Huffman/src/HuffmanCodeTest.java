import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

/**
 * Tests for the HuffmanCode class
 * @version Fall 2015 (1)
 * @author Trever Mock
 */
public class HuffmanCodeTest {
	
	/** Method that compares to see if two StringOfBits objects
	 * are equal. Returns true if they are equal and false if they
	 * are not equal.
	 * @param a - first StringOfBits object to be compared
	 * @param b - second StringOfBits object to be compared
	 * @retun true if a == b or false if a != b
	 */
	private boolean compareStringOfBits(StringOfBits a, StringOfBits b) {
		if(a.length() != b.length()) {
			return false;
		}
		for(int i=0; i<a.length(); i++) {
			if(a.charAt(i) != b.charAt(i)) {
				return false;
			}
		}
		return true;
	}
	
	/** Verifies that the createHuffmanCode method that takes a String
	 * parameter returns a map that contains the characters of that string.
	 */
	@Test
	public void createCodeWithSeedTest() {	
		Map<Character,StringOfBits> map = new HashMap<Character,StringOfBits>();
		map = HuffmanCode.createHuffmanCode("Hello");
		assertTrue(map.containsKey('H'));
		assertTrue(map.containsKey('e'));
		assertTrue(map.containsKey('l'));
		assertTrue(map.containsKey('o'));
	}
	
	/** Verifies that the createHuffmanCode method that takes in a Map of 
	 * symbols and frequencies returns a map that contains the characters of
	 * those symbols.
	 */
	@Test
	public void createCodeWithTableTest() {
		Map<Character,StringOfBits> outputMap = new HashMap<Character,StringOfBits>();
		Map<Character,Double> inputMap = new HashMap<Character,Double>();
		inputMap.put('C', new Double(1));
		inputMap.put('o', new Double(3));
		inputMap.put('l', new Double(1));
		inputMap.put('r', new Double(1));
		inputMap.put('a', new Double(1));
		inputMap.put('d', new Double(1));
		outputMap = HuffmanCode.createHuffmanCode(inputMap);
		assertTrue(outputMap.containsKey('C'));
		assertTrue(outputMap.containsKey('o'));
		assertTrue(outputMap.containsKey('l'));
		assertTrue(outputMap.containsKey('r'));
		assertTrue(outputMap.containsKey('a'));
		assertTrue(outputMap.containsKey('d'));	
	}
	
	/** Basic test for the encode method using a short string */
	@Test
	public void simpleEncodeTest() {
		String state = "Mississippi";
		HashMap<Character,StringOfBits> map = new HashMap<Character,StringOfBits>();
		map.put('s', new StringOfBits("00"));
		map.put('i', new StringOfBits("01"));
		map.put('p', new StringOfBits("10"));
		map.put('M', new StringOfBits("11"));
		assertTrue(compareStringOfBits(HuffmanCode.encode(state, map), new StringOfBits("1101000001000001101001")));	
	}
	
	/** Complex test for the encode method using a long string */
	@Test
	public void complexEncodeTest() {
		String s = "Sally Sells Seashells by the Seashore";
		StringOfBits answer = new StringOfBits("10110001001001100010101000010010001100101"
				+ "0100010000110111000010010001100101111011000101110011100000101010001000"
				+ "011011111011100000");
		HashMap<Character,StringOfBits> map = new HashMap<Character,StringOfBits>();
		map.put('b', new StringOfBits("1111"));
		map.put('t', new StringOfBits("1110"));
		map.put('o', new StringOfBits("1101"));
		map.put('r', new StringOfBits("1100"));
		map.put('S', new StringOfBits("101"));
		map.put('a', new StringOfBits("100"));
		map.put('h', new StringOfBits("0111"));
		map.put('l', new StringOfBits("010"));
		map.put('y', new StringOfBits("0110"));
		map.put('s', new StringOfBits("0011"));
		map.put('e', new StringOfBits("000"));
		map.put(' ', new StringOfBits("0010"));
		assertTrue(compareStringOfBits(HuffmanCode.encode(s, map), answer));
	}
	
	/** Basic test for the decode method using a short string */
	@Test
	public void simpleDecodeTest() {
		String answer = "Mississippi";
		StringOfBits code = new StringOfBits("1101000001000001101001");
		HashMap<Character,StringOfBits> map = new HashMap<Character,StringOfBits>();
		map.put('s', new StringOfBits("00"));
		map.put('i', new StringOfBits("01"));
		map.put('p', new StringOfBits("10"));
		map.put('M', new StringOfBits("11"));
		assertEquals(HuffmanCode.decode(code, map), answer);	
	}
	
	/** Complex test for the decode method using a long string */
	@Test
	public void complexDecodeTest() {
		String answer = "Sally Sells Seashells by the Seashore";
		StringOfBits s = new StringOfBits("10110001001001100010101000010010001100101"
				+ "0100010000110111000010010001100101111011000101110011100000101010001000"
				+ "011011111011100000");
		HashMap<Character,StringOfBits> map = new HashMap<Character,StringOfBits>();
		map.put('b', new StringOfBits("1111"));
		map.put('t', new StringOfBits("1110"));
		map.put('o', new StringOfBits("1101"));
		map.put('r', new StringOfBits("1100"));
		map.put('S', new StringOfBits("101"));
		map.put('a', new StringOfBits("100"));
		map.put('h', new StringOfBits("0111"));
		map.put('l', new StringOfBits("010"));
		map.put('y', new StringOfBits("0110"));
		map.put('s', new StringOfBits("0011"));
		map.put('e', new StringOfBits("000"));
		map.put(' ', new StringOfBits("0010"));
		assertEquals(HuffmanCode.decode(s, map), answer);
	}
	
	/** Complex test that uses encode and decode methods to test creating a map using a seed */
	@Test 
	public void complexCodeWithSeedTest() {
		HashMap<Character,StringOfBits> map = new HashMap<Character,StringOfBits>();
		map = (HashMap<Character, StringOfBits>) HuffmanCode.createHuffmanCode("Mississippi.");
	}
	
	/** Extreme test for the create code using a table method */
	@Test
	public void extremeTableTest() {
		Map<Character,StringOfBits> outputMap = new HashMap<Character,StringOfBits>();
		Map<Character,Double> table = new HashMap<Character,Double>();
		table.put('.', new Double(1));
		table.put('r', new Double(1));
		table.put('o', new Double(1));
		table.put('t', new Double(1));
		table.put('b', new Double(1));
		table.put('y', new Double(2));
		table.put('h', new Double(3));
		table.put('a', new Double(3));
		table.put('S', new Double(4));
		table.put('s', new Double(4));
		table.put(' ', new Double(5));
		table.put('l', new Double(6));
		table.put('e', new Double(6));
		outputMap = HuffmanCode.createHuffmanCode(table);
	}
	
	/** Complex test that uses encode and decode methods to test creating a map using a table */
	@Test 
	public void complexCodeWithTableTest() {
		Map<Character,StringOfBits> outputMap = new HashMap<Character,StringOfBits>();
		Map<Character,Double> table = new HashMap<Character,Double>();
		table.put('M', new Double(1));
		table.put('i', new Double(4));
		table.put('s', new Double(4));
		table.put('p', new Double(2));
		table.put('.', new Double(1));
		outputMap = HuffmanCode.createHuffmanCode(table);
	}
	
	/** Master test that tests the full capability of the code */
	@Test
	public void masterTestOne() {
		HashMap<Character,StringOfBits> map = new HashMap<Character,StringOfBits>();
		map = (HashMap<Character, StringOfBits>) HuffmanCode.createHuffmanCode("Flower");
		StringOfBits code = HuffmanCode.encode("Flower", map);
		assertEquals(HuffmanCode.decode(code, map), "Flower");
	}
	
	/** Master test that tests the full capability of the code */
	@Test
	public void masterTestTwo() {
		HashMap<Character,StringOfBits> map = new HashMap<Character,StringOfBits>();
		map = (HashMap<Character, StringOfBits>) HuffmanCode.createHuffmanCode("Sally Sells Seashells by the Seashore");
		StringOfBits code = HuffmanCode.encode("Sally Sells Seashells by the Seashore", map);
		assertEquals(HuffmanCode.decode(code, map), "Sally Sells Seashells by the Seashore");
	}
	
	/** Master test that tests the full capability of the code */
	@Test
	public void masterTestThree() {
		HashMap<Character,StringOfBits> map = new HashMap<Character,StringOfBits>();
		map = (HashMap<Character, StringOfBits>) HuffmanCode.createHuffmanCode("Today trever has been working on his program sense we woke up. He has been running test after test, shifting through his brain to try to find the right answers");
		StringOfBits code = HuffmanCode.encode("Today trever has been working on his program sense we woke up. He has been running test after test, shifting through his brain to try to find the right answers", map);
		assertEquals(HuffmanCode.decode(code, map), "Today trever has been working on his program sense we woke up. He has been running test after test, shifting through his brain to try to find the right answers");
	}
}
