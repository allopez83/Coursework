package project.p4_11;


import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;



public class Controller 
{
    InitialView iV;
    GameView gV;
    
    
    
    Model model;
    
    public Controller()
    {
        iV = new InitialView();
        iV.setVisible(true);
        
        gV = new GameView();
        // Add InitialView Listeners
        
        iV.addStartListener(new IVStartListener());
        iV.addQuitListener(new IVQuitListener());
        iV.addc1Listener(new IVc1Listener());
        iV.addc2Listener(new IVc2Listener());
        iV.addc3Listener(new IVc3Listener());
        
        // Add GameView Listeners
        
        gV.addMenuListener(new GVMenuListener());
        gV.addStyleListener(new GVStyleListener());
        gV.addUndoListener(new GVUndoListener());
        gV.addQuitListener(new GVQuitListener());
        
        // Style Menu
        
        gV.styleMenu.addDesertListener(new IVc1Listener());
        gV.styleMenu.addBeachListener(new IVc2Listener());
        gV.styleMenu.addJungleListener(new IVc3Listener());
        
        // Model
        
        model = new Model(gV);
        
    }
    
    // InitialView Listeners
    
    class IVStartListener implements ActionListener
    { 
        public void actionPerformed(ActionEvent e) 
        {
            
            if(iV.userA.getText().compareTo("") == 0 
                    || iV.userB.getText().compareTo("") == 0)
            {
                 JOptionPane.showMessageDialog(new JFrame()
                         , "Please Enter Players Names!", "No Players"
                         , JOptionPane.ERROR_MESSAGE);
            }
            else
            {
                open();
            }
        }
    }
    
    class IVQuitListener implements ActionListener
    { 
        public void actionPerformed(ActionEvent e) 
        {
            JOptionPane.showMessageDialog(new JFrame(), "Thank You for Playing!");
            System.exit(0);
        }
    }
    
    class IVc1Listener implements ActionListener
    { 
        public void actionPerformed(ActionEvent e) 
        {
             changeY();
        }
    }
    
    class IVc2Listener implements ActionListener
    { 
        public void actionPerformed(ActionEvent e) 
        {
            changeB();
        }
    }
    
    class IVc3Listener implements ActionListener
    { 
        public void actionPerformed(ActionEvent e) 
        {
            changeG();
        }
    }
    
    //GameView Listeners
    
    class GVMenuListener implements ActionListener
    { 
        public void actionPerformed(ActionEvent e) 
        {
            JOptionPane.showMessageDialog(new JFrame(), "Returning To Menu!");
            close();
        }
    }
    
    class GVStyleListener implements ActionListener
    { 
        public void actionPerformed(ActionEvent e) 
        {
            gV.styleMenu.setVisible(true);
        }
    }
    
    class GVUndoListener implements ActionListener
    { 
        public void actionPerformed(ActionEvent e) 
        {
            
        }
    }
    
    class GVQuitListener implements ActionListener
    { 
        public void actionPerformed(ActionEvent e) 
        {
            JOptionPane.showMessageDialog(new JFrame(), "Thank You for Playing!");
            System.exit(0);
        }
    }
    
    
    public void open()
    {
        model.setUsers(iV.userA.getText(), iV.userB.getText());
        gV.infoArea.setText("Mancala\n" + model.userA + " vs " + model.userB);
        iV.setVisible(false);
        gV.setVisible(true);
    }
    
    public void close()
    {
        gV.setVisible(false);
        iV.setVisible(true);
    }
    
    public void changeY()
    {
        iV.textPanel.setBackground(Color.YELLOW);
        iV.textPanel.setOpaque(true);
        iV.buttonPanel.setBackground(Color.YELLOW);
        iV.buttonPanel.setOpaque(true);
        iV.repaint();
        gV.buttonPanel.setBackground(Color.YELLOW);
        gV.infoPanel.setBackground(Color.YELLOW);
        gV.mB.setDesert();
    }
    
    public void changeB()
    {
        iV.textPanel.setBackground(Color.BLUE);
        iV.textPanel.setOpaque(true);
        iV.buttonPanel.setBackground(Color.BLUE);
        iV.buttonPanel.setOpaque(true);
        iV.repaint();
        gV.buttonPanel.setBackground(Color.BLUE);
        gV.infoPanel.setBackground(Color.BLUE);
        gV.mB.setBeach();
    }
    
    public void changeG()
    {
        iV.textPanel.setBackground(Color.GREEN);
        iV.textPanel.setOpaque(true);
        iV.buttonPanel.setBackground(Color.GREEN);
        iV.buttonPanel.setOpaque(true);
        iV.repaint();
        gV.buttonPanel.setBackground(Color.GREEN);
        gV.infoPanel.setBackground(Color.GREEN);
        gV.mB.setJungle();       
    }
    
}

