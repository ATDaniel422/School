package Prog2;

/** 
 *  COSC 310-001  Project 2
 *  DrewDanielLinkedList.java
 *  
 *  Program to read in a list of employees, and create a Linked List
 *  from that file. Then, carry out operations on that Linked List
 *  by using LinkedList methods and ListIterator methods.
 *  
 *  @author Drew Daniel
 *  
 */


import java.util.*;
import java.io.*;

public class DrewDanielLinkedList {
	public static void main(String[] args) {
		Scanner inFile = null;
		int j = 0;
		LinkedList<String> employees = new LinkedList<String>();
		
		// Read employees.txt file into program.
		try {
			inFile = new Scanner(new File("employees.txt"));
		}
		catch (FileNotFoundException e) {
			System.err.println("File not found");
			System.exit(1);
		}
		
		// Populate the linked list.
		while (inFile.hasNextLine()) {
			try {
				String temp = inFile.next();
				employees.add(j, temp);
				j++;			
			}
			catch (InputMismatchException e) {
				System.err.println("File read error");
				System.exit(1);
			}
		}
		
		// Display employee list with traditional loop
		System.out.println("The current employees are: ");
		for (int i = 0; i < employees.size(); i++) {
			System.out.print("Name of employee #" + i + " is: ");
			System.out.println(employees.get(i));
		}
		System.out.println();
		
		// Display first and last employees in LinkedList
		System.out.println("The frist employee in the list is: " + 
				employees.getFirst());
		System.out.println("The last employee in the list is: " + 
				employees.getLast());
		System.out.println();
		
		// Remove last element in the list and display new size.
		employees.removeLast();
		System.out.println("The last item in the list has been removed."
				+ " The new size of the list is: " + employees.size());
		System.out.println();
		
		// Display list using enhanced for loop.
		System.out.println("The new list, displayed using an enhanced " + 
				"for loop is: ");
		for (String name: employees) {
			System.out.println(name);
		}
		System.out.println();
		
		
		// Create Iterator to traverse employee LinkedList.
		ListIterator<String> iter = employees.listIterator();
		
		// Advance the Iterator five spaces and display current contents.
		iter.next();
		iter.next();
		iter.next();
		iter.next();
		System.out.println("The current employee after " + 
				"advancing five times is: " + iter.next());
		System.out.println();
		
		//Insert new employee named Kelly at current iterator location.
		iter.add("Kelly");
		System.out.println("Displaying Employee list after inserting Kelly " + 
				" after the fifth position: ");
		for (String name: employees) {
			System.out.print(name + " ");
		}
		System.out.println();
		System.out.println();
		
		//Setting previous element to "Nancy".
		iter.previous();
		iter.set("Nancy");
		System.out.println("Displaying Employee list after replacing Kelly " + 
				" with Nancy: ");
		for (String name: employees) {
			System.out.print(name + " ");
		}
		System.out.println();
		System.out.println();
		
		//Moving iterator back one, then removing the element.
		iter.previous();
		iter.remove();
		System.out.println("Displaying Employee list after removing element: ");
		for (String name: employees) {
			System.out.print(name + " ");
		}
		System.out.println();
		System.out.println();
		
		
		// Creating a HelpLists object.
		HelpLists help = new HelpLists();
		
		// Calling reverse method from HelpLists object to reverse order.
		System.out.println("Displaying Employee list after calling the " + 
				"Reverse method: ");
		help.reverse(employees);
		for (String name: employees) {
			System.out.print(name + " ");
		}
		System.out.println();
		System.out.println();
		
		// Calling reverse() method again to regain original order.
		System.out.println("Displaying Employee list after calling the " + 
				"reverse() method once more: ");
		help.reverse(employees);
		for (String name: employees) {
			System.out.print(name + " ");
		}
		System.out.println();
		System.out.println();
		
		// Calling downsize() method to remove employee at 2nd position
		System.out.println("Displaying Employee List after calling the " + 
				"downsize() method with n=3: ");
		help.downsize(employees, 3);
		for (String name: employees) {
			System.out.print(name + " ");
		}
		System.out.println();
		System.out.println();
	}
}