package assignment_04.examples;

//import java.awt.Point;

public class Main {
	public static void main(String args[]){
		System.out.println("Start of class\n");
		
		Name myName = new Name();
		myName.setFirst("Albert");
		System.out.println("Printing out myName...");
		System.out.println(myName);
		
		Name yourName = myName;
		System.out.println("\nPrinting out reference copy of myName, called yourName...");
		System.out.println(yourName);
		myName.setFirst("Bob");
		System.out.println("Change made to myName, printing yourName...");
		System.out.println(yourName);
		
		// Proper way of making a copy
		yourName = (Name)(myName.clone());
		System.out.println("\nyourName set as clone, printing results...");
		System.out.println(yourName);
		myName.setFirst("Charlie");
		System.out.println("myName changed, printing results in yourName");
		System.out.println(yourName);
		
		yourName.setFirst("Dave");
		System.out.println("\nyourName changed, printing yourName...");
		System.out.println(yourName);
		
		System.out.println("\n End of class");
	}
}
