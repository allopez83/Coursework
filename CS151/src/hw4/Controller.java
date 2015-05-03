package hw4;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

/**
 * Initializes the SimpleCalendar program
 * @author Hansen Wu
 *
 */
public class Controller
{
    private static final boolean DEBUG = true;
    private View view;
    private Model model;

    public Controller()
    {
        if (DEBUG) System.out.println("Controller");

        model = new Model();
        view = new View();

        setUpButtons();

        model.setView(view);
        view.display();
    }

    /**
     * Gives buttons their listeners
     */
    private void setUpButtons()
    {
        view.addQuitListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                if (DEBUG) System.out.println(" > Quit pressed");
                model.saveData();
                view.dispose();
            }
        });

        view.addPrevListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                if (DEBUG) System.out.println(" > Prev pressed");
                model.prev();
            }
        });

        view.addNextListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                if (DEBUG) System.out.println(" > Next pressed");
                model.next();
            }
        });

        view.addCreateListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                if (DEBUG) System.out.println(" > Create pressed");
                model.createMenu();
            }
        });

        view.addSaveListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                if (DEBUG) System.out.println(" > Save pressed");
                model.saveEvent();
            }
        });

        view.addMonthViewListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                if (DEBUG) System.out.println(" > MonthView pressed");

                // Get day number
                JButton source = (JButton) e.getSource();
                int day = Integer.parseInt(source.getText());
                if (DEBUG) System.out.println(day);

                model.jumpToDay(day);
            }
        });
    }
}
