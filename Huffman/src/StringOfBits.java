
/**
 * Represents a string of bit values (0 or 1)
 * @version Fall 2015 (1)
 * @author Trever
 */
public class StringOfBits {

	/** The Bit String that defines this StringOfBits object */
	private String bitString;
	
	/**
	 * Constructs the empty bit string; length()==0
	 */
	public StringOfBits() {
		this.bitString = "";
	}
	
	/**
	 * Copy Constructor.
	 * @param sb - the object to be cloned
	 */
	public StringOfBits(StringOfBits sb) {
		this.bitString = sb.bitString;
	}
	
	/**
	 * Constructs a bit string from String of '0' and '1' characters.
	 * @param charString - the string to convert into bits
	 */
	public StringOfBits(String charString) {
		this.bitString = charString;
	}
	
	/**
	 * Returns the length of this bit string.
	 * @return the number of bits in this string
	 */
	public int length() {
		return bitString.length();
	}
	
	/**
	 * Appends the bit string representation to the char argument to this 
	 * bit string. If the parameter is not '0' or '1' the result of 
	 * this method is undefined.
	 * @param c - a char
	 * @return a reference to this bit string
	 * @throws IllegalArgumentException if a char other than '0' or '1' is passed to this method
	 */
	public StringOfBits append(char c) throws IllegalArgumentException {
		if(c != '1' && c != '0') {
			throw new IllegalArgumentException();
		} else {
			this.bitString = this.bitString + c;
		}
		return this;
	}
	
	/**
	 * Appends the bit string representation of the boolean argument to this bit string;
	 * false corresponds to 0, true corresponds to 1.
	 * @param b - a boolean
	 * @return a reference to this bit string
	 */
	public StringOfBits append(boolean b) {
		if(b) {
			this.bitString = this.bitString + "1";
		}
		if(!b) {
			this.bitString = this.bitString + "0";
		}
		return this;
	}
	
	/**
	 * Appends the bit string representation of the int argument to this bit string.
	 * @param i - an int
	 * @return a reference to this bit string
	 * @throws IllegalArgumentException if an int other than 0 or 1 is passed to this method
	 */
	public StringOfBits append(int i) throws IllegalArgumentException {
		if(i != 1 && i != 0) {
			throw new IllegalArgumentException();
		} else {
			this.bitString = this.bitString + i;
		}
		return this;
	}
	
	/**
	 * Appends the bit string representation of the String argument to this bit string.
	 * Each substring of "0" corresponds to 0; each "1" corresponds to 1. If the string
	 * contains other than "0" and "1" the results of this method are undefined.
	 * @param str - a string
	 * @return a reference to this bit string
	 * @throws IllegalArgumentException if the passed string contains any characters other than '0' or '1'
	 */
	public StringOfBits append(String str) throws IllegalArgumentException {
		char[] temp = str.toCharArray();
		for(int i=0; i<str.length(); i++) {
			if(temp[i] != '1' && temp[i] != '0') {
				throw new IllegalArgumentException();
			}
		}
		this.bitString = this.bitString + str;
		return this;
		
	}
	
	/**
	 * Appends the bit string parameter to this bit string.
	 * @param bitstr - a bit string to be appended
	 * @return a reference to this bit string
	 */
	public StringOfBits append(StringOfBits bitstr) {
		this.bitString = this.bitString + bitstr.bitString;
		return this;
	}
	
	/**
	 * Returns a char corresponding to the bit at the specified index.
	 * @param index - the inde of the desired bit value
	 * @return the char value at the specified index
	 * @throws IndexOutOfBoundsException - if index is negative or
	 * greater than or equal to length();
	 */
	public char charAt(int index) throws IndexOutOfBoundsException {
		if(index >= bitString.length()) {
			throw new IndexOutOfBoundsException();
		}
		return bitString.charAt(index);
	}
	
	/**
	 * Returns an int corresponding to the bit at the specified index.
	 * @param index - the index of the desired bit value
	 * @return the int value at the specified index
	 * @throws IndexOutOfBoundsException - if index is negative or 
	 * greater than or equal to length().
	 */
	public int intAt(int index) throws IndexOutOfBoundsException {
		if(index >= bitString.length()) {
			throw new IndexOutOfBoundsException();
		}
		if(bitString.charAt(index) == '1') { return 1; }
		else { return 0; }
	}
	
	/**
	 * Returns a boolean corresponding to the bit at the specified index.
	 * @param index - the index of the desired bit value
	 * @return the boolean value at the specified index (0 == false; 1 == true)
	 * @throws IndexOutOfBoundsException - if index is negative or
	 * greater than or equal to length()
	 */
	public boolean booleanAt(int index) throws IndexOutOfBoundsException {
		if(index >= bitString.length()) {
			throw new IndexOutOfBoundsException();
		}
		if(bitString.charAt(index) == '1') { return true; }
		else { return false; }
	}
	
	/**
	 * the bit at the specified index is set to c
	 * @param index the index of the bit to modify
	 * @param c the new c value
	 * @throws IllegalArgumentException if a char other than '0' or '1' is passed to this method
	 */
	public void setBitAt(int index, char c) throws IllegalArgumentException {
		if(c != '1' && c != '0') { 
			throw new IllegalArgumentException(); 
		}
		char[] bsc = bitString.toCharArray();
		bsc[index] = c;
		this.bitString = String.valueOf(bsc);
	}
	
	/**
	 * The bit at the specified index is set to i.
	 * @param index - the index of the bit to modify
	 * @param i - the new value
	 * @throws IllegalArgumentException if an int other than 0 or 1 is passed to this method
	 */
	public void setBitAt(int index, int i) {
		if(i != 1 && i != 0) { 
			throw new IllegalArgumentException(); 
		}
		char[] bsc = bitString.toCharArray();
		if(i==1) {
			bsc[index] = '1';
		} else {
			bsc[index] = '0';
		}
		this.bitString = String.valueOf(bsc);
	}
	
	/**
	 * The bit at the specified index is set to b
	 * @param index - the index of the bit to modify
	 * @param b - the new value
	 */
	public void setBitAt(int index, boolean b) {
		char[] bsc = bitString.toCharArray();
		if(b) {
			bsc[index] = '1';
		} else {
			bsc[index] = '0';
		}
		this.bitString = String.valueOf(bsc);
	}
	
	/**
	 * Prints out the object as a string
	 * @Override toString in Object
	 */
	public String toString() {
		return bitString;
	}
}











