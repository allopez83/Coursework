package project.turnin;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * This is the core of the MVC Patterns of the Mancala Game. It Controls the flow
 * of the game. It has Views and Model.It initialize Views before passing them to the model. The
 * controller contains the listeners and tell the model what to execute.
 * 
 * @author Jordan Petersen,(Ryan) Tuan Phan, Dustin Tran, Hansen Wu
 * @version 05/09/15
 */
public class Controller 
{
    private InitialView iV;
    private GameView gV;
    private Model model;
    private Styler styler;

    /**
     * Default Controller constructor contains a gameView and a model.
     */
    public Controller()
    {
        iV = new InitialView();
        iV.setVisible(true);
        gV = new GameView();
        model = new Model(gV, iV);

        // Styler setup
        Board b = gV.getMyBoard();
        JComponent[] components = { iV.getTextPanel(), iV.getButtonPanel(),
                gV.getButtonPanel(), gV.getInfoPanel(), iV.getC2(), iV.getC3(),
                iV.getCustomizePanel(), b.getUserAP(), b.getUserBP(),
                b.getMancalaStoreBoardLeft(), b.getMancalaStoreBoardRight() };
        styler = new Styler();
        styler.setComponents(components);
        styler.setBoard(b);

        // Add InitialView Listeners

        iV.addStartListener(new IVStartListener());
        iV.addQuitListener(new IVQuitListener());
        iV.addc1Listener(new GVStyleListener());
        iV.addc2Listener(new IV4BeansListener());
        iV.addc3Listener(new IV3BeansListener());

        // Add GameView Listeners

        gV.addEndTurnListener(new GVEndTurnListener());
        gV.addMenuListener(new GVMenuListener());
        gV.addStyleListener(new GVStyleListener());
        gV.addUndoListener(new GVUndoListener());
        gV.addQuitListener(new GVQuitListener());
        gV.getMyBoard().addPitListener(new GVPitListener());

        // Style Menu

        gV.getStyleMenu().addDesertListener(new YellowStyleListener());
        gV.getStyleMenu().addBeachListener(new BlueStyleListener());
        gV.getStyleMenu().addJungleListener(new GreenStyleListener());

        model.setGameView(gV);
    }

    // InitialView Listeners
    
    /**
     * Action Listener the listens for the start button to be pushed and then tells
     * the model to executes a new game.
     */
    class IVStartListener implements ActionListener
    { 
        public void actionPerformed(ActionEvent e) 
        {
            
            if(iV.getUserA().getText().compareTo("") == 0  || iV.getUserB().getText().compareTo("") == 0)
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
    
    /**
     * Action Listener that listens for the Quit button and close the program
     */
    class IVQuitListener implements ActionListener
    { 
        public void actionPerformed(ActionEvent e) 
        {
            JOptionPane.showMessageDialog(new JFrame(), "Thank You for Playing!");
            System.exit(0);
        }
    }
    
    /**
     * Action Listener that listens for the 4 bean radio button and tells the model
     * to use 4 beans.
     */
    class IV4BeansListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e) 
        {
         iV.getC2().setSelected(true);
         iV.getC3().setSelected(false);
         model.setInitilizeNum(4);
        }
    }
    
    /**
     * Action Listener that listens for the 3 bean radio button and tells the model
     * to use 3 beans.
     */
    class IV3BeansListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e) 
        {
            iV.getC2().setSelected(false);
            iV.getC3().setSelected(true);
            model.setInitilizeNum(3);
        }
    }
    
    /**
     * Action Listener that listens for the Yellow Style Button
     */
    class YellowStyleListener implements ActionListener
    { 
        public void actionPerformed(ActionEvent e) 
        {
        	changeToYellow();
        }
    }
    
    /**
     * Action Listener that listens for the BLue Style Button
     */
    class BlueStyleListener implements ActionListener
    { 
        public void actionPerformed(ActionEvent e) 
        {
        	changeToBlue();
        }
    }

    /**
     * Action Listener that listens for the Green Style Button
     */
    class GreenStyleListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            changeToGreen();
        }
    }

    /**
     * Action Listener that listens for the Game View End Turn Button
     */
    class GVEndTurnListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            model.endUserTurn();
        }
    }

    /**
     * Action Listener that listens for the Game View Return to Menu Button
     */
    class GVMenuListener implements ActionListener
    { 
        public void actionPerformed(ActionEvent e) 
        {
            JOptionPane.showMessageDialog(new JFrame(), "Returning To Menu!");
            close();
            iV.setVisible(true);
        }
    }
    
    /**
     * Action Listener that listens for the Game View Style Button to display 
     * the style Sub-View.
     */
    class GVStyleListener implements ActionListener
    { 
        public void actionPerformed(ActionEvent e) 
        {
            gV.getStyleMenu().setVisible(true);
        }
    }
    
    /**
     * Action Listener that listens for the Game View Undo Move Button
     */
    class GVUndoListener implements ActionListener
    { 
        public void actionPerformed(ActionEvent e) 
        {
            model.reverState();
        }
    }
    
    /**
     * Action Listener that listens for the Quit Game Button
     */
    class GVQuitListener implements ActionListener
    { 
        public void actionPerformed(ActionEvent e) 
        {
            JOptionPane.showMessageDialog(new JFrame(), "Thank You for Playing!");
            System.exit(0);
        }
    }
    
    /**
     * Mouse Event Class that listens for a Mouse to either enter, exit, or click 
     * on a particular pit, and then tells the model to execute accordingly.
     */
    class GVPitListener extends MouseAdapter
    {

            @Override
            /**
             * In the event the mouse is clicked in the pot, it checks if its a valid
             * click and then tells the model to execute a play.
             */
            public void mouseClicked(MouseEvent e) {
                // TODO Auto-generated method stub
                Pit pit = (Pit)e.getSource();

                if(model.getUserState() && (pit.getPitNum() > 0 && pit.getPitNum() < 7))
                {
                    model.play(pit.getPitNum());
                    model.updateMessagePanel("Made A Play");
                }
                else if(!model.getUserState() && (pit.getPitNum() >= 7 && pit.getPitNum() < 13))
                {
                    model.play(pit.getPitNum());
                    model.updateMessagePanel("Made A Play");
                }
                else if(pit.getPitNum() == 0 && pit.getPitNum() == 13)
                {
                   model.errorMessage("Not a Playable Pot");
                   model.updateMessagePanel("Not a Playable Pot");   
                }    
                else
                {
                   model.errorMessage("Not One of Your Pots");
                   model.updateMessagePanel("Not One of Your Pots");
                }
            }

            @Override
            /**
             * In the event a mouse enters a pit area, the pit is color to reflect
             * a valid play
             */
            public void mouseEntered(MouseEvent e) {
			Pit pit = (Pit)e.getSource();
                        if(pit.getPitNum() >= 1 && pit.getPitNum() < 7)
                        {
                            if(model.getUserState())
                                pit.setColor( new Color(102, 204, 255));
                            else
                                pit.setColor(new Color(255, 153, 153));
                        }
                        else if(pit.getPitNum() >= 7 && pit.getPitNum() < 13)
                        {
                             if(!model.getUserState())
                                pit.setColor(new Color(102, 204, 255));
                            else
                                pit.setColor(new Color(255, 153, 153));
                        }
                        pit.repaint();
            }

            @Override
            /**
             * In the event a mouse exits a pit area, the pit reverts to its default
             * state.
             */
            public void mouseExited(MouseEvent e) {
			Pit pit = (Pit)e.getSource();
                        pit.setColor(Color.WHITE);
                        pit.repaint();

            }
    	
    }
    
    /**
     * Method opens a game view and sets the model to begin a game.
     */
    public void open()
    {
        model.setUsers(iV.getUserA().getText(), iV.getUserB().getText());
        gV.getInfoArea().setText("Mancala\n" + model.getUserA() + " vs " + model.getUserB());
        model.startGame();
        iV.setVisible(false);
        gV.setVisible(true);
    }
    
    /**
     * This method closes a game, and puts Mancala in its default state
     */
    public void close()
    {
        gV.setVisible(false);
        iV.setVisible(true);
    }
    /**
     * This method change the style to yellow
     */
    public void changeToYellow()
    {
        styler.changeStyle(new YellowStyle());
    }
    
    /**
     * This method change the style to blue
     */
    public void changeToBlue()
    {
        styler.changeStyle(new BlueStyle());
    }
    
    /**
     * This method change the style to green
     */
    public void changeToGreen()
    {
        styler.changeStyle(new GreenStyle());
    }

}

