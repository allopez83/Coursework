package project.p4_11;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class InitialView extends JFrame
{
    JPanel mPanel, textPanel, colorPanel, buttonPanel;
    JLabel labelA, labelB, colorLabel;
    JTextField userA, userB;
    
    JButton start, quit , c1 , c2, c3;
    
    public InitialView()
    {
        // Frame
        
        this.setLocationRelativeTo(null);
//        this.setSize(new Dimension(80,20));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("New Game");
        this.setLayout(new GridLayout(2,1));
        
        //Main Panel
        
        mPanel = new JPanel();
        mPanel.setLayout(new GridLayout(1,2));
        
        //Text Panel
        
        textPanel = new JPanel();
        textPanel.setLayout(new GridLayout(2,2));
        
        labelA = new JLabel("Player 1");
        labelA.setHorizontalAlignment(SwingConstants.CENTER);
        labelB = new JLabel("Player 2");
        labelB.setHorizontalAlignment(SwingConstants.CENTER);
          
        userA = new JTextField();
        userB = new JTextField();
        
        textPanel.add(labelA);
        textPanel.add(userA);
        textPanel.add(labelB);
        textPanel.add(userB);
        
        //Buttons
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(2,1));
        
        start = new JButton("Start");
        quit = new JButton("Quit");
        buttonPanel.add(start);
        buttonPanel.add(quit);
        
        // Color 
        
        colorPanel = new JPanel();
        colorPanel.setLayout(new GridLayout(1,3));
        
        c1 = new JButton("Desert");
        c1.setBackground(Color.YELLOW);
        c1.setContentAreaFilled(false);
        c1.setOpaque(true);
        c2 = new JButton("Beach");
        c2.setBackground(Color.BLUE);
        c2.setContentAreaFilled(false);
        c2.setOpaque(true);
        c3 = new JButton("Jungle");
        c3.setBackground(Color.GREEN);
        c3.setContentAreaFilled(false);
        c3.setOpaque(true);
        
        colorPanel.add(c1);
        colorPanel.add(c2);
        colorPanel.add(c3);
        
        // Add to Frame
        
        mPanel.add(textPanel);
        mPanel.add(colorPanel);
        this.add(mPanel);
        this.add(buttonPanel);
        
        this.setVisible(true);
        this.setSize(500, 150);
        this.pack();
        
    }
    
    //Start and Quit Listeners
    
    void addStartListener(ActionListener al)
    {
        start.addActionListener(al);
    }
        
    void addQuitListener(ActionListener al)
    {
        quit.addActionListener(al);
    }
    
    // Color Listeners
    
    void addc1Listener(ActionListener al)
    {
        c1.addActionListener(al);
    }
    
    void addc2Listener(ActionListener al)
    {
        c2.addActionListener(al);
    }
    
    void addc3Listener(ActionListener al)
    {
        c3.addActionListener(al);
    }
}
