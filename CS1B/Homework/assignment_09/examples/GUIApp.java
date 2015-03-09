package assignment_09.examples;

/**
 * A GUI Application that interacts with the user via push Buttons and ActionListener
 * interface.
 */
import java.awt.event.*;
import javax.swing.*;
import java.awt.FlowLayout;

@SuppressWarnings("serial")
public class GUIApp extends JFrame implements ActionListener, MouseListener {
	private JLabel prompt;
	private JButton hello;
	private JButton goodbye;
	private JTextField userNameField;
	private JLabel coordinates;

	private void initialize() {
		this.setLayout(new FlowLayout());
		this.setSize(300, 200);
		this.setTitle("Example Title");
		
		hello = new JButton("Hello");
		this.add(hello);
		hello.addActionListener(this);
		// Waits for this action to be performed, then triggers actionPerformed()
		// When hello is clicked by user, this class's actionPerformed() methods should be called 
		
		goodbye = new JButton("Goodbye");
		this.add(goodbye);
		goodbye.addActionListener(this);

		userNameField = new JTextField("Insert your name", 15);
		this.add(userNameField);
		
		this.setDefaultCloseOperation(GUIApp.EXIT_ON_CLOSE);
		this.setVisible(true);

		coordinates = new JLabel("Click the mouse");
		this.setLayout(new FlowLayout());
		this.setSize(200, 200);
		this.add(coordinates);
		this.setVisible(true);
		this.setDefaultCloseOperation(GUIApp.EXIT_ON_CLOSE);
		addMouseListener(this);
		
	}

	public GUIApp() {
		this.initialize();
	}

	public void actionPerformed(ActionEvent evt) {
		String name = userNameField.getText();
		
		System.out.println("GUIApp actionPerformed");	// Writes to console, separate from GUI
		if (prompt != null){
			this.remove(prompt);
			this.repaint();		// Redraws the window, getting rid of old content
		}
		if (evt.getSource() == hello) {
			prompt = new JLabel("Hello " + name +"!");
			this.add(prompt);
		} else {
			prompt = new JLabel("Goodbye "+ name +"!");
			this.add(prompt);
		}
		validate();
	}

	
	public void mouseClicked (MouseEvent me)
	{ coordinates.setText("("+ me.getPoint().x + ", " + me.getPoint().y+ ")");
	}
	public void mouseEntered (MouseEvent me)
	{ }
	public void mouseExited (MouseEvent me)
	{ }
	public void mousePressed (MouseEvent me)
	{ }
	public void mouseReleased (MouseEvent me)
	{ }
	
}









// Extra scrolling space