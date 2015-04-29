package hw4;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class DayViewComponent extends JComponent
{
    // Keep track of panel dimensions
    private final double MAX = 2400;
    private JPanel panel;
    int panelWidth, panelHeight;
    private Rectangle2D border, event;
    private Events ev;
    private Color fillColor;
    private JLabel eventName;

    public DayViewComponent(JPanel p, Events e)
    {
        this.panel = p;
        this.ev = e;
    }

    public void setEvents(Events e)
    {
        this.ev = e;
        super.repaint();
    }

    public void paintComponent(Graphics g)
    {
        System.out.println("DVC-paintComponent");

        this.removeAll();
        Graphics2D g2 = (Graphics2D) g;

        // Update dimensions
        panelWidth = panel.getWidth();
        panelHeight = panel.getHeight();

        // Draw border
        border = new Rectangle2D.Float(2, 2, panelWidth - 6, panelHeight - 6);
        g2.draw(border);

        // Events
        int counter = 0;
        ArrayList<Event> all = ev.getEvents();
        for (Event e : all)
        {
            int start = e.getStart();
            double actualStart = start / MAX * panelHeight;
            int end = e.getEnd();
            double duration = end / MAX * panelHeight - actualStart;

            event = new Rectangle2D.Double(2, actualStart, panelWidth - 6,
                    duration);

            // Differentiate between events
            if (counter % 2 == 0)
                g2.setColor(Color.LIGHT_GRAY);
            else
                g2.setColor(Color.WHITE);
            counter++;

            eventName = new JLabel(e.toSring());
            eventName.setBounds(2, (int) actualStart, panelWidth - 6,
                    (int) duration);
            this.add(eventName);

            g2.setColor(fillColor);
            g2.fill(event);
        }

    }

}
