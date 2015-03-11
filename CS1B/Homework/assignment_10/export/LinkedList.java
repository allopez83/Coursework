package assignment_10.export;

/**
 * A LinkedList specifically for Employee Objects. Each individual element, or
 * node, is an EmployeeNode Object containing an Employee Object and a pointer
 * to the next node. This LinkedList is infinitely expandable.
 * 
 * @author HW
 * 
 */
public class LinkedList {
	private EmployeeNode firstNode;

	/**
	 * Creates an empty LinkedEmployeeList
	 */
	public LinkedList() {
		firstNode = null;
	}

	/**
	 * Adds an Employee Object to the end of a LinkedEmployeeList using recursion
	 * @param inputEmpl Employee Object to be added to the end of a LinkedEmployeeList
	 */
	public void addAtEnd(Employee inputEmpl) {
		if (firstNode == null)
			firstNode = new EmployeeNode(inputEmpl);
		else
			addAtEnd(inputEmpl, firstNode);
	}
	/**
	 * Recursion method for addAtEnd() with single Employee parameter
	 * @param inputEmpl Employee Object to be added to the end of a LinkedEmployeeList
	 * @param currentNode Node that the recursion is currently inspecting
	 */
	private void addAtEnd(Employee inputEmpl, EmployeeNode currentNode) {
		if (currentNode.nextNode == null)
			currentNode.nextNode = new EmployeeNode(inputEmpl);
		else
			addAtEnd(inputEmpl, currentNode.nextNode);
	}
	
	/**
	 * Converts a LinkedEmployeeList to a String containing the information of all contained nodes
	 */
	public String toString() {
		EmployeeNode currentNode = firstNode;
		String result = "";
		while (currentNode != null) {
			result += currentNode.toString() + "\n";
			currentNode = currentNode.nextNode;
		}
		return result;
	}
	
	/**
	 * Uses Employee.raise() to raise the salary of all employees in a list by a given percent
	 * @param raisePercent percent by which to raise the salary; use 'decimal form' i.e. to raise 25% enter 0.25
	 */
	public void raise(Double raisePercent) {
		EmployeeNode currentNode = firstNode;
		while (currentNode != null) {
			currentNode.getNodeEmpl().raise(raisePercent);
			currentNode = currentNode.nextNode;
		}
	}

}
