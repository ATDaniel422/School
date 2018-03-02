package Prog5;
/**
 * COSC 310-001
 * Prog5.java
 * 
 * Program containing main method to read in a text file,
 * Alice in Wonderland, and use it to test the 
 * MainIndexingClass class and methods.
 * 
 * @author Drew Daniel
 */

import java.util.*;
import java.io.*;

public class Prog5 {

	public static void main(String[] args) {
		Scanner in = null;
		
		// Read text file into scanner.
		try {
			in = new Scanner(new File("alice30.txt")); //.useDelimiter("^A-Za-z+");
		}
		catch (FileNotFoundException e){
			System.err.println("File not found.");
			System.exit(1);
		}
		
		// Create an instance of MainIndexingClass() and test its methods.
		MainIndexingClass alice = new MainIndexingClass();
		alice.buildIndex(in);
		alice.printIndex();
		
	}

}
