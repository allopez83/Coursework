package cs1b_20130318_monday;

/**
 * Tests the class PrintThread by creating and running a number of Thread
 * objects.
 */
public class ThreadExample {
	public static void main(String args[]) {
		PrintThread thread1;
		PrintThread thread2;
		PrintThread thread3;
		PrintThread thread4;
		thread1 = new PrintThread();
		thread2 = new PrintThread();
		thread3 = new PrintThread();
		thread4 = new PrintThread();
		thread1.start(); // DOESN'T HAVE TO RETURN BEFORE CONTINUING THIS
							// SEQUENCE
		thread2.start();
		thread3.start();
		thread4.start();
	}
}

/**
 * A Thread class. Each object created from this class starts a process running
 * and then puts that process to sleep.
 */
class PrintThread extends Thread {
	private int sleepTime;

	public PrintThread()
	/* Constructor */
	{
		sleepTime = (int) (Math.random() * 5000);
		System.out.println("Just constructed Name: " + getName() + " sleep: " + sleepTime);
	}

	/**
	 * Executes the Thread, which just puts the Thread to sleep and then prints
	 * its name.
	 */
	public void run() {
		try {
			Thread.sleep(sleepTime);
		} catch (Exception e) {
			System.out.println("Error in " + getName());
		}
		System.out.println(getName() + " just woke up!");
	}
}