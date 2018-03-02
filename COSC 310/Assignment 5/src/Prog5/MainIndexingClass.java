package Prog5;
/**
 * COSC 310-001
 * MainIndexingClass.java
 * 
 * Program containing constructors and methods to take
 * a scanner with a text file and create an index of 
 * every word in the file relating it to every chapter 
 * it's in. 
 * 
 * @author Drew Daniel
 */

import java.util.*;

public class MainIndexingClass {
	// Class attributes
	private TreeMap<String, TreeSet<Integer>> index;
	
	// Constructor for MainIndexingClass
	public MainIndexingClass() {
		index = new TreeMap<>();
	}
	
	/** Reads in a list of words from a text file, stores it
	 *  into a TreeMap, and creates a TreeSet representing each
	 *  chapter of the file the word occurs in.
	 * 
	 * @param scan Scanner object containing the text file
	 */
	public void buildIndex(Scanner scan) {
		int chapNum = 0;
		
		while (scan.hasNextLine()) {
			String token;
			while ((token = scan.findInLine("[A-Za-z]+")) != null) {	
				token = token.toLowerCase();
				if (token.equals("chapter")) {
					chapNum++;
				}
				TreeSet<Integer> chapter = index.get(token);
				if (chapter == null) {
					chapter = new TreeSet<Integer>();
				}
				chapter.add(chapNum);
				index.put(token, chapter);
			}
			scan.nextLine();
		}
	}
	
	/** Prints the contents of the TreeMap and each
	 *  chapter number of its occurrence.
	 */
	public void printIndex() {	
		Set<String> keys = index.keySet();
		for(String key: keys) {
			System.out.println(key + " " + index.get(key));
		}
		System.out.println(index.size());
	}
}
