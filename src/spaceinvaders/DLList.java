package spaceinvaders;

import spaceinvaders.entity.Entity;

/**
 * <strong><em>Doubly Linked List</em></strong> data structure of <strong>Entity</strong> objects.
 * @author Osto Vargas
 *
 */
public class DLList {
	/**
	 * <strong><em>Node</em></strong> companion to <strong>LList</strong> class.
	 * @author Osto Vargas
	 *
	 */
	public class ListNode {
		private Entity e;
		private ListNode prev;
		private ListNode next;
		
		/**
		 * Constructor. Stores an <strong>Entity</strong> object.
		 * @param e
		 */
		public ListNode(Entity e) {
			this.e = e;
			prev = null;
			next = null;
		}
		
		/**
		 * Stores an <strong>Entity</strong> object into a <strong>ListNode</strong>.
		 * @param e
		 */
		public void setData(Entity e) {
			this.e = e;
		}
		
		/**
		 * Returns an <strong>Entity</strong> object stored in a <strong>ListNode</strong>.
		 * @return e
		 */
		public Entity getData() {
			return e;
		}
		
		/**
		 * Sets <em>previous</em> <strong>ListNode</strong>.
		 * @param node
		 */
		public void setPrev(ListNode node) {
			prev = node;
		}
		
		/**
		 * Returns <em>previous</em> <strong>ListNode</strong>.
		 * @return prev
		 */
		public ListNode getPrev() {
			return prev;
		}
		
		/**
		 * Sets <em>next</em> <strong>ListNode</strong>.
		 * @param node
		 */
		public void setNext(ListNode node) {
			next = node;
		}
		
		/**
		 * Returns <em>next</em> <strong>ListNode</strong>.
		 * @return next
		 */
		public ListNode getNext() {
			return next;
		}
	}
	
	/**
	 * Head node of <strong>List</strong>.
	 */
	private ListNode head;
	/**
	 * Size of <strong>List</strong>.
	 */
	private int size;
	
	/**
	 * Constructor. Initializes empty <strong>List</strong>.
	 * @param
	 */
	public DLList() {
		head = null;
		size = 0;
	}
	
	/**
	 * Returns <em>head</em> <strong>ListNode</strong>.
	 * @return head
	 */
	public ListNode getHead() {
		return head;
	}
	
	/**
	 * Returns <em>size</em> of <strong>List</strong>.
	 * @return
	 */
	public int size() {
		return size;
	}
	
	/**
	 * Inserts <strong>ListNode</strong> into the end of <strong>List</strong>.
	 * @param newE
	 */
	public void insert(Entity newE) {
		if (head == null) {
			head = new ListNode(newE);
		} else {
			ListNode temp = head;
			while (temp.getNext() != null) {
				temp = temp.getNext();
			}
			temp.setNext(new ListNode(newE));
			temp.getNext().setPrev(temp);
		}
		
		size++;
	}
	
	/**
	 * Removes <strong>ListNode</strong> in <strong>List</strong>
	 * @param node
	 */
	public void remove(ListNode node) {
		if (head.equals(node)) head = head.getNext();	// previous head is now unreachable -- collected by jvm
		else {
			node.getPrev().setNext(node.getNext());
			if (node.getNext() != null) 
				node.getNext().setPrev(node.getPrev());
		}
		
		size--;
	}
}
