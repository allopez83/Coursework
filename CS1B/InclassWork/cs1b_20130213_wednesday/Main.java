package cs1b_20130213_wednesday;

/**
 * Factorial practice in class
 */
public class Main {
	public static int factorial(int n) {
		if (n >= 1) {
			System.out.print(n + ", ");
			return n * factorial(n - 1);
		} else
			return 1;
	}

	public static void main(String[] args) {
		System.out.println("Factorial of a number");
		for (int i = -1; i < 6; i++) {
			System.out.println("\nf(" + i + ") ->" + factorial(i));
		}
	}
}
