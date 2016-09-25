
/**
 * The collected values stored at a HuffmanTree node which include: 
 * Character symbol, Double frequency, and StringOfBits code.
 * @version Fall 2015 (1)
 * @author Trever
 */
public class HuffmanTreeNodeValues {

	/** The symbol for this node. */
	private Character symbol;
	
	/** The frequency for this node. */
	private Double frequency;
	
	/** The bit code for this node */
	private StringOfBits code;
	
	/**
	 * Constructor that sets all values to null.
	 */
	public HuffmanTreeNodeValues() {
		this.symbol = null;
		this.frequency = null;
		this.code = null;
	}
	
	/**
	 * Fully parameterized constructor
	 * @param symbol - the symbol
	 * @param frequency - the frequency of the symbol
	 * @param code - the code for the symbol
	 */
	public HuffmanTreeNodeValues(Character symbol, Double frequency, StringOfBits code) {
		this.symbol = symbol;
		this.frequency = frequency;
		this.code = code;
	}
	
	/**
	 * Accesses the symbol
	 * @return the symbol
	 */
	public Character getSymbol() {
		return symbol;
	}
	
	/**
	 * Accesses the code.
	 * @return the code
	 */
	public StringOfBits getCode() {
		return code;
	}
	
	/**
	 * Accesses the frequency
	 * @return the frequency
	 */
	public Double getFrequency() {
		return frequency;
	}
	
	/**
	 * Modifies the symbol
	 * @param newsymbol - the replacement symbol
	 */
	public void setSymbol(Character newsymbol) {
		this.symbol = newsymbol;
	}
	
	/**
	 * Modifies the code.
	 * @param newcode - the replacement code
	 */
	public void setCode(StringOfBits newcode) {
		this.code = newcode;
	}
	
	/**
	 * Modifies the frequency.
	 * @param newfrequency - the replacement frequency
	 */
	public void setFrequency(Double newfrequency) {
		this.frequency = newfrequency;
	}
	
	/**
	 * Renders the HuffmanTreeNodeValues object as a String.
	 */
	@Override
	public String toString() {
		return "Symbol: " + this.getSymbol()
				+ ", Frequency: " + this.getFrequency()
				+ ", Code: " + this.getCode();
	}
}