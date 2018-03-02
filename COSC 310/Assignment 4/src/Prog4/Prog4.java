package Prog4;

/**
 * COSC 310-001
 * Prog4.java
 * 
 * Program containing main method to test RecLinkedList and
 * HelpRecLists classes. It sorts empNames into a RecLinkedList
 * and performs various modifications.
 * 
 * @author Drew Daniel
 */

import java.io.*;
import java.util.*;

public class Prog4 {

	public static void main(String[] args) {
		//Print Banner with my name
		for (int i = 0; i < 15; i++) {
			System.out.print(" ");
		}
		for (int i = 0; i < 49; i++) {
			System.out.print("#");
		}
		System.out.println();
		for (int i = 0; i < 15; i++) {
			System.out.print(" ");
		}
		System.out.println("#### This program was written by Drew Daniel ####");
		for (int i = 0; i < 15; i++) {
			System.out.print(" ");
		}
		for (int i = 0; i < 49; i++) {
			System.out.print("#");
		}
		System.out.println();
		System.out.println();
		
		// Create RecLinkedList and read in file empNames.txt
		Scanner inFile = null;
		int j = 0;
		RecLinkedList<String> empList = new RecLinkedList<String>();

		// Read employees.txt file into program.
		try {
			inFile = new Scanner(new File("empNames.txt"));
		}
		catch (FileNotFoundException e) {
			System.err.println("File not found");
			System.exit(1);
		}

		// Populate the linked list.
		while (inFile.hasNextLine()) {
			try {
				String temp = inFile.next();
				empList.addRec(temp);					
			}
			catch (InputMismatchException e) {
				System.err.println("File read error");
				System.exit(1);
				e.printStackTrace();
			}
		}
		
		// Display employees using traditional loop.
		System.out.println("       Displaying List content using traditional for loop");
		System.out.println(empList.toString());
		
		//Printing first and last element
		System.out.println("The first element in the list is: " + empList.getFirst());
		System.out.println("The last element in the list is: " + empList.getLast());
		System.out.println();
		
		//Removing last element and displaying new size and contents.
		empList.remove(empList.getLast());
		System.out.println("The size of the list after removing last element is: " + 
				empList.size());
		System.out.println();
		System.out.println("The new list after deleting the last element is:");
		System.out.println(empList.toStringE());
		System.out.println();
		
		//Replacing fifth element, Juliet,  with Nancy
		System.out.println("The List after replacing Juliet with Nancy: ");
		empList.replace("Juliet", "Nancy");
		System.out.println(empList.toStringE());
		System.out.println();
		
		//Creating HelpRecLists object to use in modifications.
		HelpRecLists help = new HelpRecLists();
		
		//Invoking the reverse() method of the helper class
		System.out.println("The list after calling the reverse() method of the helper class is:  ");
		help.reverse(empList);
		System.out.println();
		System.out.println();
		
		//Invoking the FIFO() method of the helper class
		System.out.println("The list after calling the FIFO() method of the helper class is:  ");
		help.FIFO(empList);
	}
}
