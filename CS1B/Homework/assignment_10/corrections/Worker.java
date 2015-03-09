package assignment_10.corrections;

/**
 * Keeps track of information regarding a worker by storing the information in
 * a Worker object
 * 
 * @author HW
 */
public class Worker extends Employee {
	private static final boolean TESTING_INITIALIZE = false,
								 TESTING_CONSTRUCTOR = false,
								 TESTING_DEFAULT_CONSTRUCTOR = false;
	/**
	 * Serial Version ID for Manager, subclass of Employee
	 */
	private static final long serialVersionUID = 61885L;
	
	private String boss;
	private String department;
	
	// Worker Boss
	/**
	 * Returns a worker's boss
	 * @return worker's boss
	 */
	private String boss() {
		return boss;
	}
	/**
	 * Sets a worker's boss
	 * @param newBoss worker's boss
	 */
	private void boss(String newBoss) {
		boss = newBoss;
	}
	
	// Worker Department
	/**
	 * Returns a worker's department
	 * @return worker's department
	 */
	private String department() {
		return department;
	}
	/**
	 * Sets a worker's department
	 * @param newDepartment worker's department
	 */
	private void department(String newDepartment) {
		department = newDepartment;
	}
	
	/**
	 * Creates a worker object with the provided parameters
	 * @param initName name of the worker
	 * @param initSSN ssn of the worker
	 * @param initSalary salary of the worker
	 * @param initBoss boss of the worker
	 * @param initDepartment department of the worker
	 */
	void initialize(String initName, String initSSN, double initSalary, String initBoss, String initDepartment){
		if (TESTING_INITIALIZE) System.out.println(" > Worker.initialize() reached");
		initialize(initName, initSSN, initSalary);
		boss(initBoss);
		department(initDepartment);
	}
	
	// Constructors
	/**
	 * Creates a worker object using default parameters
	 */
	public Worker(){
		if (TESTING_DEFAULT_CONSTRUCTOR) System.out.println(" > Worker default constructor reached");
		initialize("unknown_name", "000000000", 0.00, "unknown_boss", "unknown_department");
	}
	/**
	 * Creates a worker object with the provided parameters
	 * @param initName name of the worker
	 * @param initSSN ssn of the worker
	 * @param initSalary salary of the worker
	 * @param initBoss boss of the worker
	 * @param initDepartment department of the worker
	 */
	public Worker(String initName, String initSSN, double initSalary, String initBoss, String initDepartment){
		if (TESTING_CONSTRUCTOR) System.out.println(" > Worker constructor reached");
		initialize(initName, initSSN, initSalary, initBoss, initDepartment);
	}
	
	public String toString(){
		return super.toString() + ", Boss: " + boss() + ", Department: " + department();
	}
}
