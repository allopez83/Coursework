package project.p5_09;

import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
/**
 * Graphical representation of a stone of the pit
 * 
 * @author Jordan Petersen,(Ryan) Tuan Phan, Dustin Tran, Hansen Wu
 * @version 05/09/15
 */

public class SolidStone implements DrawAbleShape{
		private int x;
		private int y;
		private int width;
		private int height;
		
		/**
		 * Constructor that initialize the stone
		 * @param x x-coordinate of this stone
		 * @param y y-coordinate of this stone
		 * @param width the width of this stone
		 * @param height the height of this stone
		 */
		SolidStone (int x, int y, int width, int height)
		{
			this.x= x;
			this.y= y;
			this.width= width;
			this.height = height;
		}
	
		@Override
		/**
		 * Overriding draw method to draw a more specific shape for the stone
		 */
		public void draw(Graphics2D g2) {
			g2.fillRoundRect(x, y, width, height, 25, 25);
		}

		
	

}
