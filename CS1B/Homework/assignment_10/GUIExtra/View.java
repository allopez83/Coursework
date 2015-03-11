package assignment_10.GUIExtra;

import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * Contains a program that manages Employee Objects. Call constructor to start program.
 * 
 * @author HW
 *
 */
public class View extends JFrame {
	private static final long serialVersionUID = 1L;
	private static final int WINDOW_WIDTH = 600,
							 WINDOW_HEIGHT = 450,
							 CONTROL_PANEL_WIDTH = 150,
							 WINDOWS7_TITLEBAR = 30,
							 WINDOWS7_BORDER = 8;
	private static final Dimension CONTROL_PANEL_LABEL = new Dimension(CONTROL_PANEL_WIDTH, 15),
								   CONTROL_PANEL_CHECKBOX = new Dimension(CONTROL_PANEL_WIDTH-20, 15),
								   CONTROL_PANEL_BUTTON = new Dimension(CONTROL_PANEL_WIDTH-30, 30),
								   CONTROL_PANEL_TEXTFIELD = new Dimension(CONTROL_PANEL_WIDTH-20, 25);
	
	//Essential components
	private JPanel mainPanel;
	private JPanel controlPanel;
	private JTextArea mainTextArea;
	private JScrollPane scrollTextArea;
	private JButton showMainMenuButton;
	//Main menu components
	private JButton emplCreateButton,
					raiseButton,
					printListButton;
	//First Employee Creation menu components
	private JLabel directions;
	private CheckboxGroup selectEmplType;
	private Checkbox checkboxEmployee,
					  checkboxManager,
					  checkboxWorker;
	private JButton confirmEmplTypeButton;
	//Second Employee Creation menu components
	private JLabel nameLabel,
				   ssnLabel,
				   salaryLabel,
				   titleLabel,
				   bonusLabel,
				   bossLabel,
				   departmentLabel;
	private JTextField nameField,
					   ssnField,
					   salaryField,
					   titleField,
					   bonusField,
					   bossField,
					   departmentField;
	private JButton confirmEmplCreateButton;
	//Raise menu components
	private JLabel raiseLabel;
	private JTextField raiseField;
	private JButton confirmRaiseButton;
	
	/**
	 * Constructor, creates window for GUI components to be placed on
	 */
	public View() {
		setContentPane(windowContent());	//Sets windowContent() as the panel which will show components on the window
		
		//A window's size includes the borders of the window, too
		setSize(WINDOW_WIDTH + WINDOWS7_BORDER, WINDOW_HEIGHT + WINDOWS7_TITLEBAR);
		setTitle("Employee Manager");
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	/**
	 * Main JPanel, where all of the components that will be used by the program will be placed on
	 * @return mainPanel, containing all GUI components
	 */
	private JPanel windowContent() {
		mainPanel = new JPanel();
		mainPanel.setLayout(null);
		
		/**
		 * controlPanel is going to contain fields, buttons, and labels.
		 * Components added to this panel are associated with the user input
		 * input. It is ok for it to have a generated layout.
		 */
		controlPanel = new JPanel();
		controlPanel.setSize(CONTROL_PANEL_WIDTH, WINDOW_HEIGHT-50);
		controlPanel.setLocation(WINDOW_WIDTH-CONTROL_PANEL_WIDTH, 0);
		controlPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		mainPanel.add(controlPanel);
		
		//Text box that will contain the Employee Objects when the are printed out
		final String welcome = //Welcome needs to be final for inner class to be used
							   "\n--------------------------------------------------\n" +
							   "This is the main text area. Instructions and messages will appear here\n" +
							   "when available. Use the buttons on the right of the window to start\n" +
							   "managing employees.\n\n" +
							   "New Employee: add a new employee to the program\n" +
							   "Give Raise: raise the salary of all employees by a user-decided percentage\n" +
							   "Print List: prints out the list of employees in the main text area\n" +
							   "Main Menu: resets the app, returning to the main menu screen. Any text that\n" +
							   "is shown in the main text area will also be replaced.\n" +
							   "(Note that using the Main Menu button will remove data left in fields)";
		mainTextArea = new JTextArea("Welcome!" + welcome, 0, 0);	//Scroll feature will override the size
		mainTextArea.setEditable(false);
		mainPanel.add(mainTextArea);
		//Allows user to scroll through mainTextArea
		scrollTextArea = new JScrollPane(mainTextArea,
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollTextArea.setBounds(10, 10,
				WINDOW_WIDTH - 150 - 16,
				WINDOW_HEIGHT - 16);	//Vertical and horizontal both -16, taking consideration of the scroll bar
		scrollTextArea.getHorizontalScrollBar();
		scrollTextArea.getVerticalScrollBar();
		mainPanel.add(scrollTextArea);
		
		drawMainMenu();		//Draws main menu buttons on controlPanel
		
		//Button to return to main menu
		showMainMenuButton = new JButton("Main Menu");
		showMainMenuButton.setSize(100, 30);
		showMainMenuButton.setLocation(WINDOW_WIDTH - 125, WINDOW_HEIGHT - 40);
		showMainMenuButton.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (emplCreateButton.isValid() && raiseButton.isValid() && printListButton.isValid()) {}
						else drawMainMenu();	//Only draw the main menu if user is NOT already on the main menu
						mainTextArea.setText("Welcome Back!" + welcome);	//Change text whether or not on main menu
					}
				});mainPanel.add(showMainMenuButton);

		return mainPanel;
	}
	
	/*//// GUI Draw Menu Methods  ////*/
	/**
	 * Draws the main menu
	 */
	private void drawMainMenu() {
		final String listEmpty = "No employees found.\n" +
								 "Start by creating a new employee with the 'New Employee' button to the right.";
		controlPanel.removeAll();
		
		//Button for adding employees
		emplCreateButton = new JButton("New Employee");
		emplCreateButton.setPreferredSize(CONTROL_PANEL_BUTTON);
		emplCreateButton.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						drawEmplChooseMenu();
					}
				});
		controlPanel.add(emplCreateButton);
		//Button for giving employees a raiseButton
		raiseButton = new JButton("Give Raise");
		raiseButton.setPreferredSize(CONTROL_PANEL_BUTTON);
		raiseButton.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (Model.LinkedList().toString().equals(""))
							mainTextArea.setText(listEmpty);
						else
							drawRaiseMenu();
					}
				});
		controlPanel.add(raiseButton);
		//Button to print out the Employee Linked List
		printListButton = new JButton("Print List");
		printListButton.setPreferredSize(CONTROL_PANEL_BUTTON);
		printListButton.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String linkedEmployees = Model.LinkedList().toString();
						if (linkedEmployees.equals(""))
							mainTextArea.setText(listEmpty);
						else
							mainTextArea.setText("Employees:\n" +
												 "--------------------------------------------------\n" +
												 linkedEmployees);
					}
				});
		controlPanel.add(printListButton);
		
		controlPanel.repaint();
		controlPanel.validate();
	}

	/**
	 * Draws menu for choosing an employee type the user wants to create.
	 * Displayed immediately after the 'emplCreateButton' JButton is pressed.
	 */
	private void drawEmplChooseMenu() {
		controlPanel.removeAll();
		
		mainTextArea.setText("Choose Employee Type\n" +
							 "--------------------------------------------------\n" +
							 "There are three types of employees available. Each will require different\n" +
							 "information. Make sure the checkbox next to your desired choice is selected\n" +
							 "before proceeding\n");
		directions = new JLabel("Employee type:");
		directions.setPreferredSize(CONTROL_PANEL_LABEL);
		directions.setHorizontalAlignment(0);
		controlPanel.add(directions);
		
		selectEmplType = new CheckboxGroup();
		checkboxEmployee = new Checkbox("Employee", selectEmplType, true);
		checkboxEmployee.setPreferredSize(CONTROL_PANEL_CHECKBOX);
		controlPanel.add(checkboxEmployee);
		
		checkboxManager = new Checkbox("Manager", selectEmplType, false);
		checkboxManager.setPreferredSize(CONTROL_PANEL_CHECKBOX);
		controlPanel.add(checkboxManager);
		
		checkboxWorker = new Checkbox("Worker", selectEmplType, false);
		checkboxWorker.setPreferredSize(CONTROL_PANEL_CHECKBOX);
		controlPanel.add(checkboxWorker);
		
		confirmEmplTypeButton = new JButton("Confirm");
		confirmEmplTypeButton.setPreferredSize(CONTROL_PANEL_BUTTON);
		confirmEmplTypeButton.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						drawEmplCreateMenu();
					}
				});
		controlPanel.add(confirmEmplTypeButton);
		
		controlPanel.repaint();
		controlPanel.validate();
	}
	
	/**
	 * Draws menu for user to input data that will be used in creating a new
	 * Employee Object. Displayed immediately after the user chooses an employee
	 * type to create
	 */
	private void drawEmplCreateMenu() {
		String emplCreateDirections = "Employee Creator Input Parameters\n" +
									  "--------------------------------------------------\n" +
									  "Fill in all the fields that are displayed.\n" +
									  "Name: enter upper and lower case letters only.\n" +
									  "SSN: enter numbers only\n" +
									  "Salary: enter a numbers only, can be a decimal\n";
		controlPanel.removeAll();
		employeeCreateFields();		//Draws fields that Employee, Manager, and Worker all need
		if (selectEmplType.getSelectedCheckbox() == checkboxManager) {
			emplCreateDirections += "Title: enter letters and spaces only\n" +
									"Bonus: enter numbers only, can be a decimal\n";
			managerCreateFields();	//Draws fields unique to Managers, for creating a Manager
		} else if (selectEmplType.getSelectedCheckbox() == checkboxWorker) {
			emplCreateDirections += "Boss: enter letters and spaces only\n" +
									"Department: enter letters and spaces only\n";
			workerCreateFields();	//Draws fields unique to Workers, for creating a Worker
		}
		mainTextArea.setText(emplCreateDirections);
		
		//Button confirming Employee Object creation
		confirmEmplCreateButton = new JButton("Create");
		confirmEmplCreateButton.setPreferredSize(CONTROL_PANEL_BUTTON);
		confirmEmplCreateButton.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent ae) {
						Employee newEmployee;
						try {
							if (selectEmplType.getSelectedCheckbox() == checkboxEmployee)
								newEmployee = Model.inputToEmployee(nameField.getText(), ssnField.getText(), salaryField.getText());
							else if (selectEmplType.getSelectedCheckbox() == checkboxManager)
								newEmployee = Model.inputToManager(nameField.getText(), ssnField.getText(), salaryField.getText(), bonusField.getText(), titleField.getText());
							else
								newEmployee = Model.inputToWorker(nameField.getText(), ssnField.getText(), salaryField.getText(), bossField.getText(), departmentField.getText());
							Model.addEmplToList(newEmployee);
							mainTextArea.setText("Employee successfully added!");
							drawMainMenu();		//Employee has been created and added; return user to the main menu
						} catch (Exception e) {
							mainTextArea.setText("Employee creation failed to complete, check your inputs!\n\n" +
												 "Error message:\n" + e.getMessage());
						}
					}
				});
		controlPanel.add(confirmEmplCreateButton);
		
		controlPanel.repaint();
		controlPanel.validate();
	}

	/**
	 * Draws a menu that allows the user to give employees a raise
	 */
	private void drawRaiseMenu() {
		controlPanel.removeAll();
		
		mainTextArea.setText("Raise Instructions\n" +
							 "--------------------------------------------------\n" +
							 "Enter numbers only, decimals are allowed. The value will represent how\n" +
							 "much you want to raise the Employees' salaries. This applies to all\n" +
							 "employees. Keep in mind that the percent is in decimal form, meaning that\n" +
							 "to raise a salary by 25%, a value of 0.25 needs to be entered as opposed\n" +
							 "to 25.");
		//Raise label
		raiseLabel = new JLabel("Enter raise amount:");
		raiseLabel.setHorizontalAlignment(0);
		raiseLabel.setPreferredSize(CONTROL_PANEL_LABEL);
		controlPanel.add(raiseLabel);
		//Raise text field
		raiseField = new JTextField();
		raiseField.setHorizontalAlignment(0);
		raiseField.setPreferredSize(CONTROL_PANEL_TEXTFIELD);
		controlPanel.add(raiseField);
		//Confirm raise button
		confirmRaiseButton = new JButton("Confirm");
		confirmRaiseButton.setPreferredSize(CONTROL_PANEL_BUTTON);
		confirmRaiseButton.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent ae) {
						try {
							Model.raiseEmployees(Model.retrieveDouble(raiseField.getText()));
							mainTextArea.setText("Employees\n" +
												 "--------------------------------------------------\n" +
												 Model.LinkedList().toString());
							drawMainMenu();		//Raise has been done; return user to the main menu
						} catch (Exception e) {
							mainTextArea.setText("Raise operation failed to complete, check your input!\n" +
												 "Error message:\n" + e.getMessage());
						}
					}
				});
		controlPanel.add(confirmRaiseButton);
		
		controlPanel.repaint();
		controlPanel.validate();
	}
	
	/*//// Employee Creation Fields ////*/
	/**
	 * Fields and labels needed to create an Employee.
	 */
	private void employeeCreateFields() {
		//Employee name
		nameLabel = new JLabel("Enter employee name:");
		nameLabel.setHorizontalAlignment(0);
		nameLabel.setPreferredSize(CONTROL_PANEL_LABEL);
		controlPanel.add(nameLabel);
		nameField = new JTextField();
		nameField.setHorizontalAlignment(0);
		nameField.setPreferredSize(CONTROL_PANEL_TEXTFIELD);
		controlPanel.add(nameField);
		//Employee SSN
		ssnLabel = new JLabel("Enter employee SSN:");
		ssnLabel.setHorizontalAlignment(0);
		ssnLabel.setPreferredSize(CONTROL_PANEL_LABEL);
		controlPanel.add(ssnLabel);
		ssnField = new JTextField();
		ssnField.setHorizontalAlignment(0);
		ssnField.setPreferredSize(CONTROL_PANEL_TEXTFIELD);
		controlPanel.add(ssnField);
		//Employee salary
		salaryLabel = new JLabel("Enter employee salary:");
		salaryLabel.setHorizontalAlignment(0);
		salaryLabel.setPreferredSize(CONTROL_PANEL_LABEL);
		controlPanel.add(salaryLabel);
		salaryField = new JTextField();
		salaryField.setHorizontalAlignment(0);
		salaryField.setPreferredSize(CONTROL_PANEL_TEXTFIELD);
		controlPanel.add(salaryField);
	}
	/**
	 * Fields and labels needed specifically for creating Managers
	 */
	private void managerCreateFields() {
		//Manager title
		titleLabel = new JLabel("Enter manager title:");
		titleLabel.setHorizontalAlignment(0);
		titleLabel.setPreferredSize(CONTROL_PANEL_LABEL);
		controlPanel.add(titleLabel);
		titleField = new JTextField();
		titleField.setHorizontalAlignment(0);
		titleField.setPreferredSize(CONTROL_PANEL_TEXTFIELD);
		controlPanel.add(titleField);
		//Manager bonus
		bonusLabel = new JLabel("Enter manager bonus:");
		bonusLabel.setHorizontalAlignment(0);
		bonusLabel.setPreferredSize(CONTROL_PANEL_LABEL);
		controlPanel.add(bonusLabel);
		bonusField = new JTextField();
		bonusField.setHorizontalAlignment(0);
		bonusField.setPreferredSize(CONTROL_PANEL_TEXTFIELD);
		controlPanel.add(bonusField);
	}
	/**
	 * Fields and labels needed specifically for creating Workers
	 */
	private void workerCreateFields() {
		//Worker boss
		bossLabel = new JLabel("Enter worker's boss:");
		bossLabel.setHorizontalAlignment(0);
		bossLabel.setPreferredSize(CONTROL_PANEL_LABEL);
		controlPanel.add(bossLabel);
		bossField = new JTextField();
		bossField.setHorizontalAlignment(0);
		bossField.setPreferredSize(CONTROL_PANEL_TEXTFIELD);
		controlPanel.add(bossField);
		//Worker department
		departmentLabel = new JLabel("Enter department:");
		departmentLabel.setHorizontalAlignment(0);
		departmentLabel.setPreferredSize(CONTROL_PANEL_LABEL);
		controlPanel.add(departmentLabel);
		departmentField = new JTextField();
		departmentField.setHorizontalAlignment(0);
		departmentField.setPreferredSize(CONTROL_PANEL_TEXTFIELD);
		controlPanel.add(departmentField);
	}
	
}
