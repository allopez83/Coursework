package assignment_10.mainpackage;

/**
 * The EmployeeNode Object is an individual element, or node, of LinkedList.
 * Each node object contains an Employee Object 'nodeEmpl', and a pointer
 * 'nextNode' pointing the next node after it
 * 
 * @author HW
 * 
 */
public class EmployeeNode {
	private Employee nodeEmpl;
	EmployeeNode nextNode;
	
	/**
	 * Returns the Employee Object in a specified node
	 * @return Employee Object in a specified node
	 */
	Employee getNodeEmpl() {
		return nodeEmpl;
	}
	/**
	 * Changes the Employee Object in a specified EmployeeNode Object
	 * @param newEmpl Employee Object that will replace the current one
	 */
	void setNodeEmpl(Employee newEmpl) {
		nodeEmpl = newEmpl;
	}
	
	/**
	 * Initializes a new EmployeeNode Object
	 * @param inputEmpl Employee Object that will be contained inside the Node
	 * @param inputNode Reference to the next EmployeeNode
	 */
	private void initialize(Employee inputEmpl, EmployeeNode inputNode) {
		nodeEmpl = inputEmpl;
		nextNode = inputNode;
	}
	
	/**
	 * Creates a new EmployeeNode Object
	 * @param inputEmpl Employee Object that will be contained inside the Node
	 */
	public EmployeeNode(Employee inputEmpl) {
		initialize(inputEmpl, null);
	}
	/**
	 * Creates a new EmployeeNode Object that will point to a specified Node
	 * @param inputEmpl Employee Object that will be contained inside the Node
	 * @param inputNode Reference to the next EmployeeNode
	 */
	public EmployeeNode(Employee inputEmpl, EmployeeNode inputNode) {
		initialize(inputEmpl, inputNode);
	}
	
	/**
	 * Returns the Employee Object contained inside the Node as a String
	 */
	public String toString() {
		return nodeEmpl + "";
	}
	
}
