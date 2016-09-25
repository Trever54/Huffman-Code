import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Random;

/**
 * Utility class for creating and using Huffman codes.
 * @version Fall 2015 (1)
 * @author Trever
 */
public class HuffmanCode {
	
	/**
	 * Create a Huffman code for a given seed string
	 * @param seed - the string from which the code is generated
	 * @return the Huffman code as a map
	 */
	public static Map<Character,StringOfBits> createHuffmanCode(String seed) {
		Map<Character,Double> map = new HashMap<Character,Double>();
		Double temp;
		for(int i=0; i<seed.length(); i++) {
			if(map.containsKey(seed.charAt(i))) {
				temp = map.get(seed.charAt(i));
				temp++;
				map.put(seed.charAt(i), temp);
			} else {
				map.put(seed.charAt(i), new Double(1));
			}
		}
		return createHuffmanCode(map);	
	}
	
	/**
	 * Create a Huffman code for a given frequency table
	 * @param table - the symbol frequency table from which the code is generated
	 * @return the Huffman code as a map
	 */
	public static Map<Character,StringOfBits> createHuffmanCode(Map<Character,Double> table) {	
		HuffmanTree ht;
		PriorityQueue<HuffmanTree> SQ = new PriorityQueue<HuffmanTree>();
		PriorityQueue<HuffmanTree> TQ = new PriorityQueue<HuffmanTree>();
		for (Map.Entry<Character,Double> entry : table.entrySet()) {
			ht = new HuffmanTree(entry.getKey(), entry.getValue(), new StringOfBits());
			SQ.add(ht);
		}
		HuffmanTree treeA;
		HuffmanTree treeB;
		HuffmanTree treeP;
		HuffmanTree resultTree;	
		while(!SQ.isEmpty()){
			if(SQ.size() > 1) {
				treeA = SQ.remove();
				treeB = SQ.remove();
				treeP = new HuffmanTree(treeA.getFrequency() + treeB.getFrequency(), treeA, treeB);
				TQ.add(treeP);
			} else {
				treeP = SQ.remove();
				TQ.add(treeP);
			}
		}
		while(TQ.size() > 1) {
			treeA = TQ.remove();
			treeB = TQ.remove();
			treeP = new HuffmanTree(treeA.getFrequency() + treeB.getFrequency(), treeA, treeB);
			TQ.add(treeP);
		}	
		resultTree = TQ.remove();
		
		Map<Character,StringOfBits> outputMap = new HashMap<Character,StringOfBits>();
		List<BinaryTree<HuffmanTreeNodeValues>> rawList = resultTree.postorderSubtrees();
		List<HuffmanTree> hList = convertToHuffmanList(rawList);
		StringOfBits tempBits;
		Character tempSymbol;	
		while(!containsAllKeys(outputMap, hList)) {
			tempBits = randomFind(resultTree);
			tempSymbol = findSymbol(resultTree, tempBits);
			if(!outputMap.containsKey(tempSymbol)) {
				outputMap.put(tempSymbol, tempBits);
			}
		}	
		return outputMap;
	}
	
	/**
	 * The Huffman-encoded version of the parameter
	 * @param inputString - the string to be encoded
	 * @param huffmanCode - the Huffman code map
	 * @return the Huffman-encoded version of the parameter
	 */
	public static StringOfBits encode(String inputString, Map<Character,StringOfBits> huffmanCode)
		throws IllegalArgumentException {
		StringOfBits output = new StringOfBits();
		for(int i=0; i<inputString.length(); i++) {
			if(!huffmanCode.containsKey(inputString.charAt(i))) {
				throw new IllegalArgumentException();
			}
			output.append(huffmanCode.get(inputString.charAt(i)));
		}
		return output;
	}
	
	/**
	 * Decode a bit string (0s and 1s) using the Huffman code provided.
	 * @param encodedString - the string to be decoded
	 * @param huffmanCode - the Huffman code map
	 * @return the decoded version of the parameter
	 */
	public static String decode(StringOfBits encodedString, Map<Character,StringOfBits> huffmanCode) {
		StringOfBits currentValue = new StringOfBits("");
		String output = "";
		int i=0;
		while(i<encodedString.length()) {
			currentValue.append(encodedString.intAt(i));
			if(containsStringOfBitsValue(huffmanCode, currentValue)) {
				encodedString = shiftLeft(encodedString, currentValue.length());
				output = getKeyFromValue(huffmanCode, currentValue) + decode(encodedString, huffmanCode);
			}
			i++;
		}
		return output;
	}
	
	/**
	 * Private helper method that returns true if a given StringOfBits s is
	 * one of the values in a Map m
	 * @param m - the Map to be checked
	 * @param s - the StringOfBits
	 * @return true if the StringOfBits is a value in the Map; false otherwise.
	 */
	private static boolean containsStringOfBitsValue(Map<Character,StringOfBits> m, StringOfBits v) {	
		for(Object o : m.values()) {
			if(compareStringOfBits((StringOfBits) o, v)) {
				return true;
			}
		}	
		return false;
	}
	
	/** Helper method that compares to see if two StringOfBits objects
	 * are equal. Returns true if they are equal and false if they
	 * are not equal.
	 * @param a - first StringOfBits object to be compared
	 * @param b - second StringOfBits object to be compared
	 * @retun true if a == b or false if a != b
	 */
	private static boolean compareStringOfBits(StringOfBits a, StringOfBits b) {
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
	
	/**
	 * Private helper method that gets the Key for a value given the map
	 * @param m - the Map of keys and values
	 * @param v - the value that corresponds with the desired key
	 * @return the key that corresponds with v
	 */
	private static Object getKeyFromValue(Map<Character,StringOfBits> m, StringOfBits v) {
		for(Object o : m.keySet()) {
			if(compareStringOfBits((StringOfBits) m.get(o), v)) {
				return o;
			}
		}
		return null;
	}
	
	/**
	 * Private helper method that shifts a StringOfBits s to the left by n characters
	 * @param s - the StringOfBits to be shifted to the left
	 * @param n - the amount that s will be shifted to the left
	 * @return the shifted StringOfBits
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static StringOfBits shiftLeft(StringOfBits s, int n) {
		StringOfBits shiftedS = new StringOfBits();
		ArrayList temp = new ArrayList();
		for(int i=n; i<s.length(); i++) {
			temp.add(s.charAt(i));
		}
		for(int i=0; i<temp.size(); i++) {
			shiftedS.append((char) temp.get(i));
		}	
		return shiftedS;
	}	
	
	/**
	 * Private helper method that converts a list of BinaryTree<HuffmanTreeNodeValues> to
	 * a List of HuffmanTree objects.
	 * @param rawList - the list of generic BinaryTree<HuffmanTreeNodeValues> to be converted
	 * @return the list of HuffmanTree objects
	 */
	private static List<HuffmanTree> convertToHuffmanList(List<BinaryTree<HuffmanTreeNodeValues>> rawList) {
		List<HuffmanTree> hList = new ArrayList<HuffmanTree>();
		for(int i=0; i<rawList.size(); i++) {
			hList.add(new HuffmanTree(rawList.get(i).getValue()));
		}
		return hList;
	}
	
	/**
	 * Private helper method that checks a map to see if it contains all symbols 
	 * as keys for a list of HuffmanTree objects.
	 * @param map - the map to be checked for if it contains all of the symbols
	 * @param hList - the list of HuffmanTree objects used to check the map
	 * @return true if the map contains all symbols; false otherwise
	 */
	private static boolean containsAllKeys(Map<Character,StringOfBits> map, List<HuffmanTree> hList) {
		for(int i=0; i<hList.size(); i++) {
			if(hList.get(i).getSymbol() != null &&
					!map.containsKey(hList.get(i).getSymbol())) {
				return false;
			}
		}
		return true;	
	}
	
	/**
	 * Private helper method that finds a random leaf node in the HuffmanTree and
	 * creates a StringOfBits path to that leaf node.
	 * @param ht - the HuffmanTree object to be searched
	 * @return the path to a random leaf node in the HuffmanTree ht
	 */
	private static StringOfBits randomFind(HuffmanTree ht) {
		Random random = new Random();
		StringOfBits bits = new StringOfBits();
		if(!ht.isLeaf()) {
			if(random.nextInt(2) == 1) {
				bits.append(0);
				if(ht.getLeftChild() != null) {
					bits.append(randomFind(ht.getLeftChild()));
				}
			} else {
				bits.append(1);
				if(ht.getRightChild() != null) {
					bits.append(randomFind(ht.getRightChild()));
				}
			}
		}
		return bits;
	}
	
	/**
	 * Private helper method that finds a symbol given a StringOfBits path.
	 * @param ht - the tree to be searched
	 * @param path - the path that the tree will go through to find the symbol
	 * @return the desired symbol based on the StringOfBits path
	 */
	private static Character findSymbol(HuffmanTree ht, StringOfBits path) {	
		for(int i=0; i<path.length(); i++) {
			if(path.intAt(i) == 1) {
				ht = ht.getRightChild();
			}
			if(path.intAt(i) == 0) {
				ht = ht.getLeftChild();
			}
		}
		return ht.getSymbol();	
	}
}
