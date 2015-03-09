package assignment_10.examples;
import javax.swing.*;
import java.awt.event.*;

public class ChangeScrollBarValue{
	JFrame frame;
	JPanel panel;
	JTextArea area;
	JTextField field;
	JScrollPane spane;
	public static void main(String[] args) {
		@SuppressWarnings("unused")
		ChangeScrollBarValue v = new ChangeScrollBarValue();
	}

	public ChangeScrollBarValue(){
		String str = "Wellcome to RoseIndia.net. \n " + "This is a web site \ndevelopement company. \n " + "This is responsible for \nyour given tasks .\n " + "It always full- fill to \nyour requirements \n ";
		frame = new JFrame("Show the Value of Scroll Bar in Java Swing");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panel = new JPanel();
		area = new JTextArea(str, 5, 20);
		spane = new JScrollPane(area);
		spane.getHorizontalScrollBar().addAdjustmentListener(new MyAction());
		spane.getVerticalScrollBar().addAdjustmentListener(new MyAction());
		field = new JTextField(20);
		panel.add(field);
		panel.add(spane);
		frame.add(panel);
		frame.setSize(400, 400);
		frame.setVisible(true);
	}

	public class MyAction implements AdjustmentListener{
		public void adjustmentValueChanged(AdjustmentEvent ae){
			int value = ae.getValue();
			String st = Integer.toString(value);
			field.setText(st);
		}
	}
}