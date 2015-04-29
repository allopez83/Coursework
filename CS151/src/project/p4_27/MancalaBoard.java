package project.p4_27;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class MancalaBoard extends JPanel
{

    private BufferedImage image;

    public MancalaBoard() 
    {
    	
       try {                
          image = ImageIO.read(new File("img/jungle.jpg"));
          
       } catch (IOException ex) {
    	   Logger.getLogger(MancalaBoard.class.getName()).log(Level.SEVERE, null, ex);
       }
       
    }
    
    public void setImg(String path)
    {
        try {                
          image = ImageIO.read(new File(path));
       } catch (IOException ex) {
            // handle exception...
       }
    }
    
    public void setJungle() 
    {
        try {
            image = ImageIO.read(new File("img/jungle.jpg"));
        } catch (IOException ex) {
            Logger.getLogger(MancalaBoard.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.repaint();
    }
    
    public void setDesert()
    {
        try {
            image = ImageIO.read(new File("img/desert.jpg"));
        } catch (IOException ex) {
            Logger.getLogger(MancalaBoard.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.repaint();
    }
    
    public void setBeach()
    {
        try {
            image = ImageIO.read(new File("img/beach.jpg"));
        } catch (IOException ex) {
            Logger.getLogger(MancalaBoard.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, null);
    }
    

}