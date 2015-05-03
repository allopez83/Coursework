package project.p5_02;

import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;


public class SolidSquare implements DrawAbleShape{
		int x;
		int y;
		int width;
		int height;
		
		SolidSquare (int x, int y, int width, int height)
		{
			this.x= x;
			this.y= y;
			this.width= width;
			this.height = height;
		}
	
		@Override
		public void draw(Graphics2D g2) {
			// TODO Auto-generated method stub
			//Rectangle2D.Double square = new Rectangle2D.Double(x,y, width,height);
			g2.fillRoundRect(x, y, width, height, 25, 25);
			//g2.fill(square);
			
		}

		
	

}
