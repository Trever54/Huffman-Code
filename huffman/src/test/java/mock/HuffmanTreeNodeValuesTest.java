import static org.junit.Assert.*;

import org.junit.Test;


/**
 * Tests for the HuffmanTreeNodeValues class
 * @version Fall 2015 (1)
 * @author Trever Mock
 */
public class HuffmanTreeNodeValuesTest {
	
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
	
	/** Basic tests for the empty constructor */
	@Test
	public void emptyConstructorTest() {
		HuffmanTreeNodeValues nv = new HuffmanTreeNodeValues();
		assertNull(nv.getSymbol());
		assertNull(nv.getFrequency());
		assertNull(nv.getCode());
	}
	
	/** Basic tests for the parameterized constructor */
	@Test
	public void parameterizedConstructorTest() {
		HuffmanTreeNodeValues nv = new HuffmanTreeNodeValues('c', new Double(10), new StringOfBits("100"));
		assertTrue(nv.getSymbol().equals('c'));
		assertTrue(nv.getFrequency().equals(new Double(10)));	
		assertTrue(compareStringOfBits(nv.getCode(), new StringOfBits("100")));
	}
	
	/** Verify that the set methods work for symbol, frequency, and StringOfBits variables. */
	@Test
	public void setVariableTest() {
		HuffmanTreeNodeValues nv = new HuffmanTreeNodeValues('c', new Double(100), new StringOfBits("10010"));
		assertTrue(nv.getSymbol().equals('c'));
		nv.setSymbol('f');
		assertTrue(nv.getSymbol().equals('f'));
		nv.setSymbol('1');
		assertTrue(nv.getSymbol().equals('1'));	
		assertTrue(nv.getFrequency().equals(new Double(100)));	
		nv.setFrequency(new Double(1000));
		assertTrue(nv.getFrequency().equals(new Double(1000)));
		nv.setFrequency(new Double(0));
		assertTrue(nv.getFrequency().equals(new Double(0)));	
		assertTrue(compareStringOfBits(nv.getCode(), new StringOfBits("10010")));
		nv.setCode(new StringOfBits("101010"));
		assertTrue(compareStringOfBits(nv.getCode(), new StringOfBits("101010")));
	}
	
	/** A secondary set test that sets a HuffmanTreeNodeValues objects variables to null */
	@Test
	public void setToNullTest() {
		HuffmanTreeNodeValues nv = new HuffmanTreeNodeValues('c', new Double(100), new StringOfBits("10010"));
		nv.setSymbol(null);
		assertNull(nv.getSymbol());
		nv.setFrequency(null);
		assertNull(nv.getFrequency());
		nv.setCode(null);
		assertNull(nv.getCode());
	}
	
	/** Verifies the toString method returns a String */
	@Test
	public void toStringTest() {
		HuffmanTreeNodeValues nv = new HuffmanTreeNodeValues('c', new Double(10), new StringOfBits("101"));
		assertNotNull(nv.toString());
		assertTrue("" != "" + nv);
		nv = new HuffmanTreeNodeValues();
		assertNotNull(nv.toString());
		assertTrue("" != "" + nv);
	}
	
}
