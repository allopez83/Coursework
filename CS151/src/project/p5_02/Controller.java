package project.p5_02;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Controller 
{
    InitialView iV;
    GameView gV;
    Model model;
    Styler styler;
    
    public Controller()
    {
        iV = new InitialView();
        iV.setVisible(true);
        gV = new GameView();
        model = new Model(gV);

        JComponent[] color = { iV.textPanel, iV.buttonPanel, gV.buttonPanel,
                gV.infoPanel };
        styler = new Styler();
        styler.setComponents(color);
        
//        for (int i= 0 ; i < model.getPits().length ; i++)
//        {
//        	model.getPits()[i] = i+1;
//        }
        
//        ***************
        
//        AbstractBoard board = new SquareBoard(model);

        // Add InitialView Listeners
        
        iV.addStartListener(new IVStartListener());
        iV.addQuitListener(new IVQuitListener());
        iV.addc1Listener(new YellowStyleListener());
        iV.addc2Listener(new BlueStyleListener());
        iV.addc3Listener(new GreenStyleListener());
        
   
        // Add GameView Listeners
        
        gV.addMenuListener(new GVMenuListener());
        gV.addStyleListener(new GVStyleListener());
        gV.addUndoListener(new GVUndoListener());
        gV.addQuitListener(new GVQuitListener());
        gV.myBoard.addPitListener(new GVPitListener());
       
        // Style Menu
        
        gV.styleMenu.addDesertListener(new YellowStyleListener());
        gV.styleMenu.addBeachListener(new BlueStyleListener());
        gV.styleMenu.addJungleListener(new GreenStyleListener());
        
        model.setGameView(gV);
        model.initilizeGame();
        model.setView();
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
    
    class YellowStyleListener implements ActionListener
    { 
        public void actionPerformed(ActionEvent e) 
        {
        	changeToYellow();
        }
    }
    
    class BlueStyleListener implements ActionListener
    { 
        public void actionPerformed(ActionEvent e) 
        {
        	changeToBlue();
        }
    }
    
    class GreenStyleListener implements ActionListener
    { 
        public void actionPerformed(ActionEvent e) 
        {
        	changeToGreen();
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
            model.tester();
            model.setView();
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
    
    class GVPitListener extends MouseAdapter
    {

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void mousePressed(MouseEvent e) {
			
			 Pit thePit = (Pit)e.getSource();
			JOptionPane.showMessageDialog(new JFrame(), "You have "+ thePit.getStones().length+ " pits");
			
		}
	
		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
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

    public void changeToYellow()
    {
        styler.changeStyle(new YellowStyle());
    }

    public void changeToBlue()
    {
        styler.changeStyle(new BlueStyle());
    }

    public void changeToGreen()
    {
        styler.changeStyle(new GreenStyle());
    }

}

