package assignment_04.mainpackage;

/**
 * Tests cloning ability of EmployeeList
 */
public class Main {
	public static void main(String[] args) {
		EmployeeList originalList = new EmployeeList(3);

		Employee Albert, Bill, Calvin, David;
		Albert = new Employee("Albert", "111-111-111", 100.00);
		Bill = new Employee("Bill", "111-111-222", 150.00);
		Calvin = new Employee("Calvin", "111-111-333", 200.00);
		David = new Employee("David", "111-111-444", 250.00);
		originalList.add(Albert);
		originalList.add(Bill);
		
		EmployeeList cloneList = (EmployeeList) (originalList.clone());
		
		/*
		cloneList is a clone of originalList, so they should be two
		independent copies. In other words, cloneList should not be
		containing references to originalList. Therefore, a change
		in orignialList should not affect cloneList, and vice-versa
		*/
		System.out.println("__________ Original __________\n"+
		"originalList:\n" + originalList + "cloneList:\n" + cloneList);	//This is how the two EmployeeLists look in the beginning
		
		Bill.initialize("Bill.edited", "999-999-999", 99.99);
		System.out.println("__________ Change 'Bill' in originalList __________\n"+
		"originalList:\n" + originalList + "cloneList:\n" + cloneList);	//Bill should only be changed in originalList
		
		cloneList.add(Calvin);
		System.out.println("__________ Add 'Calvin' to cloneList __________\n"+
		"originalList:\n" + originalList + "cloneList:\n" + cloneList);	//Calvin should only appear in cloneList
		
		originalList.add(David);
		System.out.println("__________ Add 'David' to originalList __________\n"+
		"originalList:\n" + originalList + "cloneList:\n" + cloneList);	//David should only appear in originalList
	}
}

/************PASTE FROM CONSOLE************
__________ Original __________
originalList:
Name: 'Albert', SSN: 111-111-111, Salary: 100.0
Name: 'Bill', SSN: 111-111-222, Salary: 150.0
cloneList:
Name: 'Albert', SSN: 111-111-111, Salary: 100.0
Name: 'Bill', SSN: 111-111-222, Salary: 150.0

__________ Change 'Bill' in originalList __________
originalList:
Name: 'Albert', SSN: 111-111-111, Salary: 100.0
Name: 'Bill.edited', SSN: 999-999-999, Salary: 99.99
cloneList:
Name: 'Albert', SSN: 111-111-111, Salary: 100.0
Name: 'Bill', SSN: 111-111-222, Salary: 150.0

__________ Add 'Calvin' to cloneList __________
originalList:
Name: 'Albert', SSN: 111-111-111, Salary: 100.0
Name: 'Bill.edited', SSN: 999-999-999, Salary: 99.99
cloneList:
Name: 'Albert', SSN: 111-111-111, Salary: 100.0
Name: 'Bill', SSN: 111-111-222, Salary: 150.0
Name: 'Calvin', SSN: 111-111-333, Salary: 200.0

__________ Add 'David' to originalList __________
originalList:
Name: 'Albert', SSN: 111-111-111, Salary: 100.0
Name: 'Bill.edited', SSN: 999-999-999, Salary: 99.99
Name: 'David', SSN: 111-111-444, Salary: 250.0
cloneList:
Name: 'Albert', SSN: 111-111-111, Salary: 100.0
Name: 'Bill', SSN: 111-111-222, Salary: 150.0
Name: 'Calvin', SSN: 111-111-333, Salary: 200.0


**************END OF TEST RUN*************/