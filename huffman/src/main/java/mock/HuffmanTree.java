
/**
 * A HuffmanTree is a specialized BinaryTree used for developing and
 * storing a Huffman Code. Note that there is no empty tree constructor.
 * @version Fall 2015 (1)
 * @author Trever
 * @see Serialized Form
 *
 */

public class HuffmanTree extends BinaryTree<HuffmanTreeNodeValues> implements Comparable<HuffmanTree> {
	
	/** Maximum Difference to accept two double values as equal. 
	 * @see Constant Field Values
	 */
	static double EPSILON;
	
	/**
	 * Constructor for leaf node.
	 * @param data - an object containing the symbol, frequency, and code for this node
	 */
	public HuffmanTree(HuffmanTreeNodeValues data) {
		super(data);
	}
	
	/**
	 * Constructor for internal node.
	 * @param data - an object containing the symbol, code, and frequency for this node
	 * @param leftChild - the left child for this node
	 * @param rightChild - the right child for this node
	 */
	public HuffmanTree(HuffmanTreeNodeValues data, HuffmanTree leftChild, HuffmanTree rightChild) {
		super(data, leftChild, rightChild);
	}
	
	/** Parameterized constructor for leaf node.
	 * @param symbol - the symbol stored in this node
	 * @param frequency - the code stored in this node
	 * @param code - the frequency stored in this node
	 */
	public HuffmanTree(Character symbol, Double frequency, StringOfBits code) {
		super(new HuffmanTreeNodeValues(symbol, frequency, code));
	}
	
	/**
	 * Fully parameterized constructor.
	 * @param symbol - the symbol stored in this node
	 * @param frequency - the code stored in this node
	 * @param code - the frequency stored in this node
	 * @param leftChild - the left child for this node
	 * @param rightChild - the right child for this node
	 */
	public HuffmanTree(Character symbol, Double frequency, StringOfBits code,
			HuffmanTree leftChild, HuffmanTree rightChild) {
		super(new HuffmanTreeNodeValues(symbol, frequency, code), leftChild, rightChild);
	}
	
	/**
	 * Frequency and children constructor; useful for internal nodes. Sets symbol and code to null.
	 * @param frequency
	 * @param leftChild
	 * @param rightChild
	 */
	public HuffmanTree(Double frequency, HuffmanTree leftChild, HuffmanTree rightChild) {
		super(new HuffmanTreeNodeValues(null, frequency, null), leftChild, rightChild);
	}
	
	/**
	 * Returns the left child of this tree.
	 * @Overrides getLeftChild in class BinaryTree<HuffmanTreeNodeValues>
	 * @return the left child; null if no such child
	 */
	@Override
	public HuffmanTree getLeftChild() {
		return (HuffmanTree) super.getLeftChild();
	}
	
	/**
	 * Returns the right child of this tree.
	 * @Overrides getRightChild in class BinaryTree<HuffmanTreeNodeValues>
	 * @return the right child; null if no such child
	 */
	@Override
	public HuffmanTree getRightChild() {
		return (HuffmanTree) super.getRightChild();
	}
	
	/**
	 * Retrieve the symbol stored in this root.
	 * @return the symbol stored in this root
	 */
	public Character getSymbol() {
		return super.getValue().getSymbol();
	}
	
	/**
	 * Retrieve the code stored in this root.
	 * @return the code stored in this root
	 */
	public StringOfBits getCode() {
		return super.getValue().getCode();
	}
	
	/**
	 * Retrieve the frequency stored in this root.
	 * @return the frequency stored in this root
	 */
	public Double getFrequency() {
		return super.getValue().getFrequency();
	}
	
	/**
	 * Store the given parameter as the symbol of this tree root.
	 * @param symbol - the new symbol for this root
	 */
	public void setSymbol(Character symbol) {
		super.setValue(new HuffmanTreeNodeValues(symbol, this.getFrequency(), this.getCode()));
	}
	
	/**
	 * Store the given parameter as the code of this tree root.
	 * @param code - the new code for this root
	 */
	public void setCode(StringOfBits code) {
		super.setValue(new HuffmanTreeNodeValues(this.getSymbol(), this.getFrequency(), code));
	}
	
	/**
	 * Store the given parameter as the frequency of this tree root.
	 * @param frequency - the new frequency for this root
	 */
	public void setFrequency(Double frequency) {
		super.setValue(new HuffmanTreeNodeValues(this.getSymbol(), frequency, this.getCode()));
	}
	
	/**
	 * Equals predicate considers the symbol and frequency only
	 * @Override equals in class BinaryTree<HuffmanTreeNodeValues>
	 * @param o - the object to check for equality
	 * @return true if both the symbol and the frequency agree; false otherwise
	 * @see BinaryTree.hashCode()
	 */
	@Override
	public boolean equals(Object obj) {
		if(this.getSymbol() == null && ((HuffmanTree) obj).getSymbol() == null) { return true; }
		else if(this.getSymbol() == null || ((HuffmanTree) obj).getSymbol() == null) { return false; }
		if(this.getFrequency() == null && ((HuffmanTree) obj).getSymbol() == null) { return true; }
		else if(this.getFrequency() == null || ((HuffmanTree) obj).getSymbol() == null) { return false; }
		if(this.getSymbol().equals((((HuffmanTree) obj).getSymbol())) &&
				this.getFrequency().equals(((HuffmanTree) obj).getFrequency())) { return true; }
		return false;
	}
	
	/**
	 * Returns a hash code value for the object. Supported for the benefit of hashtables.
	 * @Override hashCode in class BinaryTree<HuffmanTreeNodeValues>
	 * @return a hash code value for this object
	 * @see BinaryTree.equals(Object o)
	 */
	@Override
	public int hashCode() {
		return (int) (this.getSymbol().hashCode() * this.getFrequency());
	}
	
	/**
	 * Compares this HuffmanTree with the parameter for order. Returns a negative integer,
	 * zero, or a positive integer as this object is less than, equal to, or greater than
	 * the specified object. Comparison considers frequency only; null is considered to be
	 * lower than any other frequency value.
	 * <p>
	 * Ensures that sgn(x.compareTo(y)) == -sgn(y.compareTo(x)) for all x and y. 
	 * (This implies that x.compareTo(y) must throw an exception if and only if 
	 * y.compareTo(x) throws an exception.)
	 * <p>
	 * The relation is transitive: (x.compareTo(y)>0 && y.compareTo(z)>0) implies x.compareTo(z)>0.
	 * <p>
	 * Ensures that x.compareTo(y)==0 implies that sgn(x.compareTo(z)) == sgn(y.compareTo(z)), for all z.
	 * <p>
	 * The natural ordering is consistent with {@link equals}, that is, (x.compareTo(y)=0) == (x.equals(y)).
	 * <p>
	 * In the foregoing description, the notation sgn(expression) designates the mathematical signum 
	 * function, which is defined to return one of -1, 0, or 1 according to whether the value of 
	 * expression is negative, zero, or positive.
	 * <p>
	 * @param ht - the object to be compared
	 * @return a negative integer, zero, or a positive integer as this object is less than, equal to,
	 * or greater than the specified object.
	 */
	@Override
	public int compareTo(HuffmanTree ht) {
		if(ht.getFrequency() == null && this.getFrequency() == null) {
			return 0;
		}
		if(this.getFrequency() == null || this.getFrequency() < ht.getFrequency()) {
			return -1;
		}
		if(ht.getFrequency() == null || this.getFrequency() > ht.getFrequency()) {
			return 1;
		}
		return 0;
	}	
}
