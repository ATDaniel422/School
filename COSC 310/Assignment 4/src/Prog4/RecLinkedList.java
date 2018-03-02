package Prog4;

/** 
 *  COSC 310-001  Project 4
 *  RecLinkedList.java
 *  
 *  Program to create a Recursive Linked List class with only recursive methods,
 *  read in a list of employees, and create a Recursive Linked List
 *  from that file. Then, carry out operations on that Linked List
 *  by using RecLinkedList methods and ListIterator methods.
 *  
 *  @author Drew Daniel
 *  
 */

import java.util.*;
import java.io.*;


public class RecLinkedList<E> implements ListIterator {
	private Node<E> head;
	private Node<E> tail;
//	private int size;
	
	
		
	private static class Node<E> {
		private E data;
		private Node<E> next = null;
		private Node<E> prev = null;
		
		private Node(E dataItem) {
			data = dataItem;
		}
	}
	
	/** Finds the size of a list.
	 * @param head The head of the current list
	 * @return The size of the current list
	 */
	private int size(Node<E> head) {
		if (head == null) {
			return 0;
		}
		else {
			return 1 + size(head.next);
		}
	}
	
	/** Wrapper method for finding the size of a list.
	 * @return The size of the list
	 */
	public int size() {
		return size(head);
	}
	
	/** Adds a new node to the end of a list.
	 * @param head The head of the current list
	 * @param data The data for the new node
	 */
	private void addRec(Node<E> head, E data) {
		if (head.next == null) {
			head.next = new Node<E>(data);
			tail = head.next;
		}
		else {
			addRec(head.next, data);
		}
	}
	
	/** Wrapper method for adding a new node to the end of a list
	 * @param data The data for the new node
	 */
	public void addRec(E data) {
		if (head == null) {
			head = new Node<E>(data);
		}
		else {
			addRec(head, data);
		}
	}
	
	/** Removes a node from a list.
	 * post: The first occurrence of target is removed
	 * @param head The head of the current list
	 * @param pred The predecessor of the list head
	 * @param target The data to be removed
	 * @return true if the item is removed and false if not.
	 */
	private boolean remove(Node<E> head, Node<E> pred, E target) {
		if (head == null) {
			return false;
		}
		else if (head.data.equals(target)){
			pred.next = head.next;
			return true;
		}
		else {
			return remove(head.next, head, target);
		}
	}
	
	/** Wrapper method for removing a node
	 *  post: The first occurrence of target is removed
	 *  @param target The data to be removed
	 *  @return true if the item is removed and false if not.
	 */
	public boolean remove(E target) {
		if (head == null) {
			return false;
		}
		else if (head.data.equals(target)) {
			head = head.next;
			return true;
		}
		else {
			return remove(head.next, head, target);
		}
	}
	
	/** Returns the string representation of the list
	 *  @param head - The head of the current list
	 *  @return The state of the current list
	 */
	private String toString(Node<E> head) {
		if (head == null) {
			return "";
		}
		else {
			return head.data + "\n" + toString(head.next);
		}
	}
	
	/** Wrapper method for returning the string representation of a 
	 *  list.
	 *  @return The string representation of the list
	 */
	public String toString() {
		return toString(head);
	}
	/** Returns the string representation of the list
	 *  @param head - The head of the current list
	 *  @return The state of the current list
	 */
	private String toStringE(Node<E> head) {
		if (head == null) {
			return "";
		}
		else {
			return head.data + " " + toStringE(head.next);
		}
	}
	
	/** Wrapper method for returning the string representation of a 
	 *  list.
	 *  @return The string representation of the list
	 */
	public String toStringE() {
		return toStringE(head);
	}
	
	/** Replaces all occurrences of target with replacement.
	 *  post: EAch occurrence of target has been replaced by replacement
	 *  @param head The head of the current list
	 *  @param target The object to be replaced
	 *  @param replacement The replacement object
	 */
	private void replace(Node<E> head, E target, E replacement) {
		if (head != null) {
			if(target.equals(head.data)) {
				head.data = replacement;
			}
			replace(head.next, target, replacement);
		}
	}
	
	/** Wrapper method for replacing target with replacement
	 *  post: Each occurrence of target has been replaced by replacement.
	 *  @param target The object being replaced
	 *  @param replacement The replacement object
	 */
	public void replace(E target, E replacement) {
		replace(head, target, replacement);
	}
	
	public E getFirst() {
		return head.data;
	}
	
	public E getLast() {
		return tail.data;
	}

	
	
	//Methods for ListIterator Interface.
	
	@Override
	public void add(Object arg0) {
		
		
	}

	@Override
	public boolean hasNext() {
		if (head.next == null) {
			return true;
		}
		else {
		return false;
		}
	}

	@Override
	public boolean hasPrevious() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Object next() {
		
		return null;
	}

	@Override
	public int nextIndex() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Object previous() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int previousIndex() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void remove() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void set(Object arg0) {
		// TODO Auto-generated method stub
		
	}

	public ListIterator<E> listIterator() {
		Node<E> data;
		int counter;
		
		
		return null;
	}
	
}
