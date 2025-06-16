import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.lang.*;

/** the class of which all lines are objects*/
public class LineConnect
{
	/** constructor for LineConnect
		@param double xstarter the x starting position for the line
		@param double ystarter the y starting position for the line
		@param double xender the x ending position for the line
		@param double yender the y ending position for the line
		@param double p1 the grid starting position for the line
		@param double p2 the grid ending position for the line*/
	public LineConnect(double xstarter, double ystarter, double xender, double yender,int p1,int p2)
	{
		//assigning all the variables so they can be used in draw
		xstart = (int)xstarter + 28;
		ystart = (int)ystarter + 15;
		xend = (int)xender - 6;
		yend = (int)yender + 15;
		position1 = p1;
		position2 = p2;
	}
	/** the draw method to draw the line
	 @param Graphics g the graphics operator*/
	public void draw (Graphics g)
	{
		Graphics2D g2 = (Graphics2D)g;
		// the first horizontal line
		Line2D.Double r = new Line2D.Double(xstart, ystart, xstart + 4, ystart);
		g2.draw(r);
		// the  vertical line
		r = new Line2D.Double(xstart + 4, ystart, xstart + 4, yend);
		g2.draw(r);
		// the second horizontal line
		r = new Line2D.Double(xstart + 4, yend, xend + 6 , yend);
		g2.draw(r);
	}

public  int position1;
public  int position2;
private int xstart;
private int ystart;
private int xend;
private int yend;
}
