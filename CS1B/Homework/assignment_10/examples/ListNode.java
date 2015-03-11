package assignment_10.examples;
/** 
 * One object of this class represents one node in the linked list, class List
 */
class ListNode 
{	private Object data;	// Holds whatever data is in each node 

	// don't want these to be public!! Make it default instead
	ListNode next; // Holds the reference to the next node in the List	

	public ListNode (Object d)   	
	{	initialize (d, null);
	}
	public ListNode (Object d, ListNode n)	
	{	initialize(d, n);
	}
	private void initialize(Object d, ListNode n)
	{	data = d;
		next = n;
	}
	public String toString ()	// Returns the data in one node
	{	return ""+data;  	// this automatically calls data().toString()
	}
}
