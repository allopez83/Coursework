package cs1b_20130313_wednesday;
/**
 * One object of this class represents a linked list of ListNode objects, above. 
 */
public class List
{	private ListNode first;   	// A reference to the first node in the list

	public List ()	
	{	first = null;
	}
	
	/**
	 * Makes a new node containing "d" and links it to the end of List.
	 */
	public void addAtEnd (Object d)	
	{	if (first == null)
			first = new ListNode ( d );
		else if (first.next == null)
			first.next = new ListNode ( d );
		else {
			ListNode temp = first.next;
			while (temp.next != null)		// We know that (temp != null)
				temp = temp.next;
			temp.next = new ListNode(d);
		}
	}

	public void addAtStart(Object newElement) {
		ListNode tempRef = first;
		first = new ListNode(newElement);
		first.next = tempRef;
		
		/*
		ListNode temp;
		temp = first;
		first = new ListNode(d);
		first.next = temp;
		 */
		
		/*
		first.next = newNode;
		newNode.next = first;
		*/
		
	}
	
	public void recursiveAddAtEnd(Object d) {
		System.out.println("#1");
		if (first == null) {
			first = new ListNode(d);
			System.out.println("#7");
		} else {
			System.out.println("#6");
			recursiveAddAtEnd(d, first);
			System.out.println("#5");
		}
		System.out.println("#2");
	}
	private void recursiveAddAtEnd(Object d, ListNode currentNode) {
		if (currentNode.next == null) {
			System.out.println("#3");
			currentNode.next = new ListNode(d);
		} else {
			System.out.println("#4");
			recursiveAddAtEnd(d, currentNode.next);
		}
	}

	public String toString()	
	{	ListNode temp;
		temp = first;
		String value = "";
		while (temp != null)
		{	value = value + " " + temp;
			temp = temp.next;
		}
		return value;
	}
	
	/**
	 * Uses System.out.println to print out the List
	 */
	public void print() {
		print(first);
	}
	
	/**
	 * Uses recursion to print out the list recursively
	 * @param node
	 */
	private void print(ListNode startNode) {
		if (startNode != null){
			System.out.println(startNode);
			this.print(startNode.next);
		}
	}
}
