package cs1b_20130311_monday;
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
	
	public void recursiveAddAtEnd(Object d) {
		if (first == null)
			recursiveAddAtEnd(d,first);
		else;
			//Check online for this part
	}
	private void recursiveAddAtEnd(Object d, ListNode startNode) {
		if (startNode == null){
			startNode = new ListNode(d);
		} else {
			recursiveAddAtEnd(d, startNode.next);
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
