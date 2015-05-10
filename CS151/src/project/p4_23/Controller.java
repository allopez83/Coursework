package project.p4_23;

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

        model = new Model();

        // TODO testing for round board
        AbstractBoard board = new SquareBoard(model);

        // Add InitialView Listeners

        iV.addStartListener(new IVStartListener());
        iV.addQuitListener(new IVQuitListener());
        iV.addc1Listener(new IVc1Listener());
        iV.addc2Listener(new IVc2Listener());
        iV.addc3Listener(new IVc3Listener());

        // Add GameView Listeners
        gV = new GameView(board);
        gV.addMenuListener(new GVMenuListener());
        gV.addStyleListener(new GVStyleListener());
        gV.addUndoListener(new GVUndoListener());
        gV.addQuitListener(new GVQuitListener());
        gV.myBoard.addPitListener(new GVPitListener());

        // Style Menu

        gV.styleMenu.addDesertListener(new IVc1Listener());
        gV.styleMenu.addBeachListener(new IVc2Listener());
        gV.styleMenu.addJungleListener(new IVc3Listener());

        model.setGameView(gV);

        // Setup styler
        styler = new Styler();
        JComponent[] colorThese = { iV.textPanel, iV.buttonPanel,
                gV.buttonPanel, gV.infoPanel };
        styler.setComponents(colorThese);
        styler.setGameBoard(board, model);
    }

    // InitialView Listeners

    class IVStartListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {

            if (iV.userA.getText().compareTo("") == 0
                    || iV.userB.getText().compareTo("") == 0)
            {
                JOptionPane.showMessageDialog(new JFrame(),
                        "Please Enter Players Names!", "No Players",
                        JOptionPane.ERROR_MESSAGE);
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
            JOptionPane.showMessageDialog(new JFrame(),
                    "Thank You for Playing!");
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

    // GameView Listeners

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
            JOptionPane.showMessageDialog(new JFrame(),
                    "Thank You for Playing!");
            System.exit(0);
        }
    }

    class GVPitListener extends MouseAdapter
    {

        public void mouseClicked(MouseEvent e)
        {
            // TODO Auto-generated method stub

        }

        public void mousePressed(MouseEvent e)
        {
            SquarePit thePit = (SquarePit) e.getSource();
            JOptionPane.showMessageDialog(new JFrame(), "You have "
                    + thePit.shapes.length + " pits");
        }

        public void mouseReleased(MouseEvent e)
        {
            // TODO Auto-generated method stub

        }

        public void mouseEntered(MouseEvent e)
        {
            // TODO Auto-generated method stub

        }

        public void mouseExited(MouseEvent e)
        {
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

    public void changeY()
    {
        styler.changeStyle(new YellowSquareStyle(), gV);
    }

    public void changeB()
    {
        styler.changeStyle(new BlueSquareStyle(), gV);
    }

    public void changeG()
    {
        styler.changeStyle(new GreenSquareStyle(), gV);
    }

}
