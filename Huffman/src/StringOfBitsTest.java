import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests for the StringOfBits class
 * @version Fall 2015 (1)
 * @author Trever
 */
public class StringOfBitsTest {
	
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
	
	/** Basic test for the empty constructor. */
	@Test
	public void emptyConstructorTest() {
		StringOfBits sb = new StringOfBits();
		assertEquals(0, sb.length());
	}
	
	/** Basic test for the constructor that takes a String paramter. */
	@Test
	public void stringConstructorTest() {
		StringOfBits sb = new StringOfBits("10100");
		assertEquals(5, sb.length());
		sb = new StringOfBits("");
		assertEquals(0, sb.length());
	}
	
	/** Basic test for the copy constructor. */
	@Test
	public void copyConstructorTest() {
		StringOfBits sb = new StringOfBits("001");
		StringOfBits copy = new StringOfBits(sb);
		assertEquals(3, sb.length());
		assertEquals(3, copy.length());
		assertTrue(compareStringOfBits(sb, copy));
	}
	
	/** Test append using a boolean parameter */
	@Test
	public void appendBooleanTest() {
		StringOfBits sb = new StringOfBits("11");
		sb.append(false);
		assertTrue(compareStringOfBits(new StringOfBits("110"), sb));	
		sb = new StringOfBits("11");
		sb.append(true);
		assertTrue(compareStringOfBits(new StringOfBits("111"), sb));
	}
	
	/** Test append using a char parameter */
	@Test
	public void appendCharTest() {
		StringOfBits sb = new StringOfBits("001");
		sb.append('0');
		assertTrue(compareStringOfBits(new StringOfBits("0010"), sb));	
		sb = new StringOfBits("001");
		sb.append('1');
		assertTrue(compareStringOfBits(new StringOfBits("0011"), sb));
	}
	
	/** Test append using a int parameter */
	@Test
	public void appendIntTest() {
		StringOfBits sb = new StringOfBits("101");
		sb.append(0);
		assertTrue(compareStringOfBits(new StringOfBits("1010"), sb));	
		sb = new StringOfBits("101");
		sb.append(1);
		assertTrue(compareStringOfBits(new StringOfBits("1011"), sb));
	}
	
	/** Test append using a String parameter */
	@Test
	public void appendStringTest() {
		StringOfBits sb = new StringOfBits("1111");
		sb.append("0010");
		assertTrue(compareStringOfBits(new StringOfBits("11110010"), sb));	
		sb = new StringOfBits("1111");
		sb.append(new String(""));
		assertTrue(compareStringOfBits(new StringOfBits("1111"), sb));
	}
	
	/** Test append using a StringOfBits parameter */
	@Test
	public void appendStringOfBitsTest() {
		StringOfBits a = new StringOfBits("0011");
		StringOfBits b = new StringOfBits("1100");
		a.append(b);
		assertTrue(compareStringOfBits(new StringOfBits("00111100"), a));	
		b.append(a);
		assertTrue(compareStringOfBits(new StringOfBits("110000111100"), b));
		a = new StringOfBits("00");
		b = new StringOfBits("");
		a.append(b);
		assertTrue(compareStringOfBits(new StringOfBits("00"), a));
		b.append(a);
		assertTrue(compareStringOfBits(new StringOfBits("00"), b));
		a = new StringOfBits("");
		b = new StringOfBits("");
		a.append(b);
		assertTrue(compareStringOfBits(new StringOfBits(""), a));
	}
	
	/** Basic test for 'booleanAt' method */
	@Test
	public void booleanAtTest() {
		StringOfBits sb = new StringOfBits("0010");
		assertFalse(sb.booleanAt(0));
		assertFalse(sb.booleanAt(1));
		assertTrue(sb.booleanAt(2));
		assertFalse(sb.booleanAt(3));
	}
	
	/** Basic test for 'charAt' method */
	@Test
	public void charAtTest() {
		StringOfBits sb = new StringOfBits("100");
		assertEquals('1', sb.charAt(0));
		assertEquals('0', sb.charAt(1));
		assertEquals('0', sb.charAt(2));
	}
	
	/** Basic test for 'intAt' method */
	@Test
	public void intAtTest() {
		StringOfBits sb = new StringOfBits("1100");
		assertEquals(1, sb.intAt(0));
		assertEquals(1, sb.intAt(1));
		assertEquals(0, sb.intAt(2));
		assertEquals(0, sb.intAt(3));
	}
	
	/** Exception test for 'booleanAt' method */
	@Test (expected = IndexOutOfBoundsException.class)
	public void booleanAtExceptionTest() {
		StringOfBits sb = new StringOfBits("1010");
		sb.booleanAt(5);
	}
	
	/** Exception test for 'charAt' method */
	@Test (expected = IndexOutOfBoundsException.class)
	public void charAtExceptionTest() {
		StringOfBits sb = new StringOfBits("1010");
		sb.charAt(5);
	}
	
	/** Exception test for 'intAt' method */
	@Test (expected = IndexOutOfBoundsException.class)
	public void intAtExceptionTest() {
		StringOfBits sb = new StringOfBits("1010");
		sb.intAt(5);
	}
	
	/** Basic test for 'setBitAt' method using a boolean parameter */
	@Test
	public void setBitAtBooleanTest() {
		StringOfBits sb = new StringOfBits("0101");
		sb.setBitAt(0, true);
		assertTrue(compareStringOfBits(new StringOfBits("1101"), sb));
		assertEquals(sb.intAt(0), 1);
		sb.setBitAt(3, false);
		assertTrue(compareStringOfBits(new StringOfBits("1100"), sb));
		assertEquals(sb.intAt(3), 0);
	}
	
	/** Basic test for 'setBitAt' method using a char parameter */
	@Test
	public void setBitAtCharTest() {
		StringOfBits sb = new StringOfBits("0101");
		sb.setBitAt(0, '1');
		assertEquals(sb.intAt(0), 1);
		assertTrue(compareStringOfBits(new StringOfBits("1101"), sb));
		sb.setBitAt(3, '0');
		assertTrue(compareStringOfBits(new StringOfBits("1100"), sb));
		assertEquals(sb.intAt(3), 0);
	}
	
	/** Basic test for 'setBitAt' method using an int parameter */
	@Test
	public void setBitAtIntTest() {
		StringOfBits sb = new StringOfBits("0101");
		sb.setBitAt(0, 1);
		assertTrue(compareStringOfBits(new StringOfBits("1101"), sb));
		assertEquals(sb.intAt(0), 1);
		sb.setBitAt(3, 0);
		assertTrue(compareStringOfBits(new StringOfBits("1100"), sb));
		assertEquals(sb.intAt(3), 0);
	}
	
	/** Verifies the toString method returns a String. */
	@Test
	public void toStringTest() {
		StringOfBits sb = new StringOfBits("1000");
		assertNotNull(sb.toString());
		assertTrue("" != "" + sb);
		sb = new StringOfBits();
		assertNotNull(sb.toString());
		assertTrue("" != "" + sb);
	}
	
	/** Tests the class more extensively. The tests revolve around the length method and setting bits. */
	@Test
	public void extensiveTest() {
		StringOfBits a = new StringOfBits("1111");
		StringOfBits b = new StringOfBits("1010");
		assertEquals(a.length(), b.length());
		a.setBitAt(1, false);
		b.setBitAt(3, 1);
		assertTrue(compareStringOfBits(a, b));
		a.append(b);
		assertEquals(8, a.length());
		b.append(b);
		assertTrue(compareStringOfBits(a, b));
		b.setBitAt(0, '0');
		assertNotEquals(a, b);
		assertEquals(b.intAt(0), 0);
		assertEquals(a.booleanAt(0), true);
	}
}
