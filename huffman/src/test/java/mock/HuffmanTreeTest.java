import static org.junit.Assert.*;

import org.junit.Test;


/**
 * Tests for the HuffmanTree class
 * @version Fall 2015 (1)
 * @author Trever
 */
public class HuffmanTreeTest {
	
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
	
	/** Basic tests for the leaf node constructor */
	@Test
	public void leafNodeConstructorTest() {
		HuffmanTree ht = new HuffmanTree(new HuffmanTreeNodeValues());
		assertEquals(ht.getSymbol(), null);
		assertEquals(ht.getFrequency(), null);
		assertEquals(ht.getCode(), null);
		ht = new HuffmanTree(new HuffmanTreeNodeValues('c', new Double(10), new StringOfBits("100")));
		assertTrue(ht.getSymbol().equals('c'));
		assertTrue(ht.getFrequency().equals(new Double(10)));
		assertTrue(compareStringOfBits(ht.getCode(), new StringOfBits("100")));
	}
	
	/** Basic tests for the internal node constructor. */
	@Test
	public void internalNodeConstructorTest() {
		HuffmanTree leftChild = new HuffmanTree(new HuffmanTreeNodeValues('L', new Double(100), new StringOfBits("001")));
		HuffmanTree rightChild = new HuffmanTree(new HuffmanTreeNodeValues('R', new Double(1), new StringOfBits("01")));
		HuffmanTree ht = new HuffmanTree(new HuffmanTreeNodeValues('M', new Double(10), new StringOfBits("100")),
				leftChild, rightChild);
		assertTrue(compareStringOfBits(ht.getCode(), new StringOfBits("100")));
		assertTrue(ht.getSymbol().equals('M'));
		assertTrue(ht.getFrequency().equals(new Double(10)));
		assertEquals(ht.getLeftChild(), leftChild);
		assertEquals(ht.getRightChild(), rightChild);
		assertTrue(compareStringOfBits(ht.getLeftChild().getCode(), new StringOfBits("001")));
		assertTrue(ht.getLeftChild().getSymbol().equals('L'));
		assertTrue(ht.getLeftChild().getFrequency().equals(new Double(100)));
		assertTrue(compareStringOfBits(ht.getRightChild().getCode(), new StringOfBits("01")));
		assertTrue(ht.getRightChild().getSymbol().equals('R'));
		assertTrue(ht.getRightChild().getFrequency().equals(new Double(1)));
	}
	
	/** Secondary tests the internal node constructor for if a child is null */
	@Test
	public void nullChildTest() {
		HuffmanTree ht = new HuffmanTree(new HuffmanTreeNodeValues('M', new Double(10), new StringOfBits("100")),
				null, null);
		assertEquals(ht.getLeftChild(), null);
		assertEquals(ht.getRightChild(), null);
	}
	
	/** Tests for the parameterized constructor for a leaf node */
	@Test
	public void parameterizedLeafNodeConstructor() {
		HuffmanTree ht = new HuffmanTree('c', new Double(5), new StringOfBits("1001"));
		assertTrue(compareStringOfBits(ht.getCode(), new StringOfBits("1001")));
		assertTrue(ht.getSymbol().equals('c'));
		assertTrue(ht.getFrequency().equals(new Double(5)));
	}
	
	/** Tests the fully parameterized constructor */
	@Test
	public void fullyParameterizedConstructor() {
		HuffmanTree leftChild = new HuffmanTree(new HuffmanTreeNodeValues('L', new Double(1), new StringOfBits("1")));
		HuffmanTree rightChild = new HuffmanTree(new HuffmanTreeNodeValues('R', new Double(1), new StringOfBits("01")));
		HuffmanTree ht = new HuffmanTree('c', new Double(10), new StringOfBits("0101"),
				leftChild, rightChild);
		assertTrue(ht.getSymbol().equals('c'));
		assertTrue(ht.getFrequency().equals(new Double(10)));
		assertTrue(compareStringOfBits(ht.getCode(), new StringOfBits("0101")));
		assertEquals(ht.getLeftChild(), leftChild);
		assertEquals(ht.getRightChild(), rightChild);	
	}
	
	/** Tests the frequency and children constructor */
	@Test
	public void childrenAndFrequencyConstructor() {
		HuffmanTree leftChild = new HuffmanTree(new HuffmanTreeNodeValues('L', new Double(1), new StringOfBits("1")));
		HuffmanTree rightChild = new HuffmanTree(new HuffmanTreeNodeValues('R', new Double(1), new StringOfBits("01")));
		HuffmanTree ht = new HuffmanTree(new Double(2), leftChild, rightChild);
		assertTrue(ht.getFrequency().equals(new Double(2)));
		assertEquals(ht.getLeftChild(), leftChild);
		assertEquals(ht.getRightChild(), rightChild);	
	}
	
	/** Verifies the compareTo method. */
	@Test
	public void compareToTest() {
		HuffmanTree A = new HuffmanTree(new HuffmanTreeNodeValues('A', new Double(10), new StringOfBits("10101010")));
		HuffmanTree eqA = new HuffmanTree(new HuffmanTreeNodeValues('A', new Double(10), new StringOfBits("10101010")));
		HuffmanTree ltA = new HuffmanTree(new HuffmanTreeNodeValues('A', new Double(0), new StringOfBits("10101010")));
		HuffmanTree gtA = new HuffmanTree(new HuffmanTreeNodeValues('A', new Double(100), new StringOfBits("10101010")));
		HuffmanTree nA = new HuffmanTree(new HuffmanTreeNodeValues('A', null, new StringOfBits("10101010")));	
		HuffmanTree nB = new HuffmanTree(new HuffmanTreeNodeValues('A', null, new StringOfBits("10101010")));	
		HuffmanTree B = new HuffmanTree(new HuffmanTreeNodeValues('A', new Double(10), new StringOfBits("10101010")));
		assertTrue(A.compareTo(eqA) == 0);
		assertTrue(A.compareTo(ltA) > 0);
		assertTrue(A.compareTo(gtA) < 0);
		assertTrue(nA.compareTo(ltA) < 0);
		assertTrue(ltA.compareTo(A) < 0);
		assertTrue(B.compareTo(A) == 0);
		assertTrue(B.compareTo(gtA) < 0);
		assertTrue(nA.compareTo(nB) == 0);
		assertTrue(A.equals(B));
	}
	
	/** Verifies the Overridden equals method. Note that if this test is failing,
	 * then other tests using assertEquals will likely fail because of an improper
	 * implementation of the equals method. 
	 */
	@Test
	public void equalsTest() {
		HuffmanTree a = new HuffmanTree(new HuffmanTreeNodeValues('C', new Double(10), new StringOfBits("10101010")));
		HuffmanTree b = new HuffmanTree(new HuffmanTreeNodeValues('C', new Double(10), new StringOfBits("10101010")));
		HuffmanTree diffChar = new HuffmanTree(new HuffmanTreeNodeValues('B', new Double(10), new StringOfBits("10101010")));
		HuffmanTree diffFreq = new HuffmanTree(new HuffmanTreeNodeValues('C', new Double(1), new StringOfBits("10101010")));
		HuffmanTree diffSb = new HuffmanTree(new HuffmanTreeNodeValues('C', new Double(10), new StringOfBits("1010101")));
		HuffmanTree diffAll = new HuffmanTree(new HuffmanTreeNodeValues('Z', new Double(110), new StringOfBits("1010")));
		assertTrue(a.equals(b));
		assertTrue(b.equals(a));
		assertFalse(a.equals(diffChar));
		assertFalse(a.equals(diffFreq));
		assertTrue(a.equals(diffSb));
		assertFalse(a.equals(diffAll));
	}
	
	/** Verifies the Overridden HashCode method. That is, that two equal objects return
	 * the same hashcode value. 
	 */
	@Test
	public void hashCodeTest() {
		HuffmanTree a = new HuffmanTree(new HuffmanTreeNodeValues('C', new Double(10), new StringOfBits("10101010")));
		HuffmanTree b = new HuffmanTree(new HuffmanTreeNodeValues('C', new Double(10), new StringOfBits("10101010")));
		HuffmanTree diffChar = new HuffmanTree(new HuffmanTreeNodeValues('B', new Double(10), new StringOfBits("10101010")));
		HuffmanTree diffFreq = new HuffmanTree(new HuffmanTreeNodeValues('C', new Double(1), new StringOfBits("10101010")));
		HuffmanTree diffSb = new HuffmanTree(new HuffmanTreeNodeValues('C', new Double(10), new StringOfBits("1010101")));
		HuffmanTree diffAll = new HuffmanTree(new HuffmanTreeNodeValues('Z', new Double(110), new StringOfBits("1010")));
		assertEquals(a.hashCode(), b.hashCode());
		assertEquals(a.hashCode(), diffSb.hashCode());
		assertNotEquals(a.hashCode(), diffChar.hashCode());
		assertNotEquals(a.hashCode(), diffFreq.hashCode());
		assertNotEquals(a.hashCode(), diffAll.hashCode());
	}
	
	/** Verifies the set methods work correctly for a HuffmanTree */
	@Test
	public void setVariablesTest() {
		HuffmanTree ht = new HuffmanTree(new HuffmanTreeNodeValues('C', new Double(10), new StringOfBits("10101010")));
		assertTrue(ht.getSymbol().equals('C'));
		ht.setSymbol('h');
		assertTrue(ht.getSymbol().equals('h'));
		ht.setSymbol('1');
		assertTrue(ht.getSymbol().equals('1'));
		assertTrue(ht.getFrequency().equals(new Double(10)));
		ht.setFrequency(new Double(100101));
		assertTrue(ht.getFrequency().equals(new Double(100101)));
		ht.setFrequency(new Double(0));
		assertTrue(ht.getFrequency().equals(new Double(0)));
		assertTrue(compareStringOfBits(ht.getCode(), new StringOfBits("10101010")));
		ht.setCode(new StringOfBits("10"));
		assertTrue(compareStringOfBits(ht.getCode(), new StringOfBits("10")));	
	}
}
