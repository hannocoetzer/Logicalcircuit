
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.lang.*;

/** the class of which all gates are objects*/
public class GateGuy
{
	/** constructor for GateGuy/*
		@param double Gatex the x co-ordinates where the gate should be drawn
		@param double Gatex the y co-ordinates where the gate should be drawn
		@param double Gatex the grid position where the gate should be drawn
		@ param String labelgetter which kind of gate is it?*/
	public GateGuy(double Gatex, double Gatey,int gridpositionget,String labelgetter)
	{
		x = (int)Gatex;
		y = (int)Gatey;
		gridposition = gridpositionget;
		label = labelgetter;
	}

	/** "the drawing of the three" (actually 4 (gates))*/
	public void draw (Graphics g)
	{
		Graphics2D g2 = (Graphics2D)g;
		if(label.equals("SWITCH"))
		{
		    // the top line
			Line2D.Double r = new Line2D.Double(x, y, x + 30, y);
			g2.draw(r);
			// the right vertical line
			r = new Line2D.Double(x + 28, y, x + 28, y + 28);
			g2.draw(r);
			// the left vertical line
			r = new Line2D.Double(x, y , x , y + 29);
			g2.draw(r);
			// the bottom line
			r = new Line2D.Double(x, y + 28, x + 28, y + 28);
	    	g2.draw(r);
	        g2.drawString("S",x + 1,y + 20);
	        valuedisplay = "" + value;
	        g2.drawString(valuedisplay,x + 11, y + 20);
		}
		if(label.equals("AND"))
		{
			// the top line
			Line2D.Double r = new Line2D.Double(x, y, x + 30, y);
			g2.draw(r);
			// the right vertical line
			r = new Line2D.Double(x + 28, y, x + 28, y + 28);
			g2.draw(r);
			// the left vertical line
			r = new Line2D.Double(x, y , x , y + 29);
			g2.draw(r);
			// the bottom line
			r = new Line2D.Double(x, y + 28, x + 28, y + 28);
	    	g2.draw(r);
	    	valuedisplay = "" + value;
	    	g2.drawString(valuedisplay,x + 11, y + 20);
		}
		if(label.equals("OR"))
		{

			// the top line
			Line2D.Double r = new Line2D.Double(x, y, x + 28, y);
			g2.draw(r);
			// the vertical line
			r = new Line2D.Double(x + 28, y, x + 28, y + 30);
			g2.draw(r);
			// the bottom line
			r = new Line2D.Double(x, y + 30, x + 28, y + 30);
			g2.draw(r);
			// the connection line
			r = new Line2D.Double(x, y + 15, x + 15, y + 15);
			g2.draw(r);
			// the bottom sideways line
			r = new Line2D.Double(x, y + 30, x + 15, y + 15);
			g2.draw(r);
			// the top sideways line
			r = new Line2D.Double(x, y, x + 15, y + 15);
	    	g2.draw(r);
	    	valuedisplay = "" + value;
	    	g2.drawString(valuedisplay,x + 11, y + 20);
		}

		if(label.equals("NOT"))
		{
			// the verical line
			Line2D.Double r = new Line2D.Double(x, y, x, y + 30);
	    	g2.draw(r);
			 // the top sideways line
	    	r = new Line2D.Double(x, y, x + 30, y + 15);
	    	g2.draw(r);
			// the bottom sideways line
			r = new Line2D.Double(x, y + 30, x + 30, y + 15);
			g2.draw(r);
			valuedisplay = "" + value;
			g2.drawString(valuedisplay,x + 11, y + 20);
		}
	}

	/** sending input1 on command
		@return input1 the gate to wich input 1 is linked*/
	public int getInput1()
	{
		return input1;
	}

	/** sending input2 on command
		@return input2 the gate to wich input 2 is linked*/
	public int getInput2()
	{
		return input2;
	}

	/** setting input1 on command*/
	public void setInput1(int in1)
	{
		input1 = in1;
	}

	/** setting input2 on command*/
	public void setInput2(int in2)
	{
		input2 = in2;
	}

	/** sending output on command
		@return output the gate to wich output is linked*/
	public int getOutput()
	{
		return output;
	}

	/** setting output on command*/
	public void setOutput(int out)
	{
		output = out;
	}

	public int input1 = 51;
	public int input2 = 51;
	public int inputvalue1 = -1;
	public int inputvalue2 = -1;
	public int output = 51;
	public int value = -1;
	public String valuedisplay;
	public String label;
	public int gridposition;
	private int linecounter;
	private int position;
	private int positioncounter;
	private int blockcounter;
	public int x;
	private int y;
}
