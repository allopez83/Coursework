package assignment_08.corrections;

/**
 * Keeps track of information regarding a manager by storing the information in a
 * Manager object
 * 
 * @author HW
 */
public class Manager extends Employee {
	//TODO Set to false when turning in
	private static final boolean TESTING_INITIALIZE = false,
								 TESTING_CONSTRUCTOR = false,
								 TESTING_DEFAULT_CONSTRUCTOR = false;
	/**
	 * Serial Version ID for Manager, subclass of Employee
	 */
	private static final long serialVersionUID = 61885L;
	
	private String title;
	private double bonus;
	
	// Manager Title
	/**
	 * Returns a manager's title
	 * @return manager's title
	 */
	private String title(){
		return title;
	}
	/**
	 * Sets a manager's title
	 * @param newTitle manager's title
	 */
	private void title(String newTitle){
		title = newTitle;
	}
	
	// Manager Bonus
	/**
	 * Returns a manager's bonus
	 * @return manager's bonus
	 */
	private double bonus() {
		return bonus;
	}
	/**
	 * Sets a manager's bonus
	 * @param bonus amount of annual bonus
	 */
	private void bonus(double bonus) {
		this.bonus = bonus;
	}
	
	/**
	 * Creates a Manager object with the provided parameters
	 * @param initName name of the manager
	 * @param initSSN ssn of the manager
	 * @param initSalary salary of the manager
	 * @param initBonus annual bonus of the manager
	 * @param initTitle title of the manager
	 */
	private void initialize(String initName, String initSSN, double initSalary, double initBonus, String initTitle){
		if (TESTING_INITIALIZE) System.out.println(" > Manager.initialize() reached");
		super.name(initName);
		super.ssn(initSSN);
		super.salary(initSalary);
		bonus(initBonus);
		title(initTitle);
	}
	
	// Constructors
	/**
	 * Creates a Manager object using default parameters
	 */
	public Manager(){
		if (TESTING_DEFAULT_CONSTRUCTOR) System.out.println(" > Manager default constructor reached");
		initialize("unknown_name", "000000000", 0.00, 0.00, "unknown_title");
	}

	/**
	 * Creates a Manager object with the provided parameters
	 * @param initName name of the manager
	 * @param initSSN ssn of the manager
	 * @param initSalary salary of the manager
	 * @param initBonus annual bonus of the manager
	 * @param initTitle title of the manager
	 */
	public Manager(String initName, String initSSN, double initSalary, double initBonus, String initTitle){
		if (TESTING_CONSTRUCTOR) System.out.println(" > Manager constructor reached");
		initialize(initName, initSSN, initSalary, initBonus, initTitle);
	}
	
	public String toString(){
		return ("Name: '" + name() + "', SSN: " + ssn() + ", Salary: " + salary() + ", Bonus: " + bonus() + ", Title: " + title());
	}
}
