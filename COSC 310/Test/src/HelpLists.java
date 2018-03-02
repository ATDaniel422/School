package Prog2;

/**
 * COSC 310-001
 * HelpLists.java
 * 
 * Helper Class for DrewDanielLinkedLists that provides methods for 
 * reversing the order of a Linked List and deleting employees on an 
 * interval.
 * 
 * @author Drew Daniel
 */


import java.util.*; 

public class HelpLists {
	
	/**
	 * Method to reverse the order of a LinkedList
	 * @param strings LinkedList to be reversed.
	 */
	 
	public void reverse(LinkedList<String> strings) {
		LinkedList<String> temp = new LinkedList<String>();
		for (String name: strings) {
			temp.add(name);
		}
		ListIterator iter1 = temp.listIterator(temp.size());
		ListIterator iter2 = strings.listIterator();
		for (int i = 0; i < strings.size(); i++) {
			iter2.next();
			iter2.set(iter1.previous());
		}
	}
	
	/**
	 * Method to delete an object on a given interval.
	 * @param employeeNames LinkedList of Strings to traverse
	 * @param n Interval on which to delete employee
	 */
	public void downsize(LinkedList<String> employeeNames, int n) {
		ListIterator iter = employeeNames.listIterator();
		while (iter.hasNext()) {
			for (int i = 0; i < n; i++) {
				if (iter.hasNext()) {
					iter.next();
				}
			}
			iter.remove();
		}
	}
}
