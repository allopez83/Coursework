package cs1b_20130318_monday;

/**
 * Animates an asterisk by moving it diagonally across the Applet window
 */
import java.awt.*;
import java.applet.Applet;

public class AppletExample extends Applet implements Runnable {
	private static final long serialVersionUID = 1L;
	
	private int height; // The height of the Applet window
	private int width; // The width of the Applet window
	private int x; // The current x position on the Applet
	private int y; // The current y position on the Applet
	private Thread runner; // Holds the thread for the animation

	public void start() {
		System.out.println("start() called");
		if (runner == null) {
			runner = new Thread(this); // Connects Main with the Thread "runner"
			runner.start(); // Calls Main.run()
		}
	}

	public void stop() {
		System.out.println("stop() called");
	}

	public void run() {
		System.out.println("run() called");
		while (runner != null) // Causes the animation to be continuous
		{
			repaint(); // Calls paint()
			try {
				Thread.sleep(500); // allows the image to stay on screen
			} catch (InterruptedException e) {
			}
		}
	}

	public void init() {
		System.out.println("init() called");
		width = this.getSize().width;
		height = this.getSize().height;
		x = 10;
		y = 10;
	}

	public void paint(Graphics screen) {
		System.out.println("paint() called");
		screen.drawString("*", x % width, y % height);
		x++; // this will eventually overflow the ints
		y++; // what is a better solution?
	}
}