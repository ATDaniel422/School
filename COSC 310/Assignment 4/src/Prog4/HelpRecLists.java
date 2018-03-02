package Prog4;

/**
 * COSC 310-001
 * HelpRecLists.java
 * 
 * Helper Class for RecLinkedLists that provides methods for 
 * reversing the order of a RecLinkedList using a Stack and 
 * a FIFO method to push the RecLinkedList through a Queue
 * 
 * @author Drew Daniel
 */


import java.util.*;

public class HelpRecLists {
	
	/**
	 * Method to reverse the order of a LinkedList
	 * @param strings RecLinkedList to be reversed.
	 */
	public void reverse(RecLinkedList<String> strings) {
		LinkedList<String> newList = new LinkedList<>();
		Stack<String> nameStack = new Stack<String>();
		String[] tokens = strings.toStringE().split(" ");
		for (int i = 0; i < tokens.length ; i++) {
			newList.add(tokens[i]);
		}
		ListIterator<String> iter = newList.listIterator();
		while (iter.hasNext()) {
			nameStack.push(iter.next());
		}
		iter = newList.listIterator();
		while (!(nameStack.isEmpty())) {
			System.out.print(nameStack.pop() + " ");
		}
	}
	
	/**
	 * Method to push a RecLinkedList into a Queue and retrieve data
	 * @param strings RecLinkedList to be used
	 */
	public void FIFO(RecLinkedList<String> strings) {
		LinkedList<String> newList = new LinkedList<>();
		Queue<String> nameQueue = new LinkedList<String>();
		String[] tokens = strings.toStringE().split(" ");
		for (int i = 0; i < tokens.length ; i++) {
			newList.add(tokens[i]);
		}
		ListIterator<String> iter = newList.listIterator();
		while (iter.hasNext()) {
			nameQueue.offer(iter.next());
		}
		iter = newList.listIterator();
		while (!(nameQueue.isEmpty())) {
			System.out.print(nameQueue.poll() + " ");
		}
	}
}
