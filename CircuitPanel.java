import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Color;
import javax.swing.JPanel;
import java.awt.geom.Line2D;
import java.awt.event.MouseAdapter;
import javax.swing.JOptionPane;

/** the circuit panel in which to draw*/
public class CircuitPanel extends JPanel
{
	public CircuitPanel()
	{
		setPreferredSize(new Dimension(PANELWIDTH, PANELHEIGHT));
		// inner class mouselistener
		class MousePressListener extends MouseAdapter
	    {
	    	public void mousePressed(MouseEvent event)
		    {
		        gatex = event.getX();
		        gatey = event.getY();
				clickHappener();
		    }

	    }
		MouseListener listener = new MousePressListener();
    	addMouseListener(listener);
    }

	/** everything that should happen inside the mouselistener*/
	public void clickHappener()
	{
		// setting the x and y parameters for drawing, and obtaining the grid position of
		// the clicked area
		gridposition = pManager.getPosition(gatex,gatey);
		gatex = pManager.getX();
		gatey = pManager.getY();
		// check if the grid area is occupied
		occupied = pManager.detectPosition(gates,gridposition,gatearraySIZE);

		// if the button pressed was delete:
		if (label.equals("DELETE GATE"))
		{
			if (occupied == true)
			{
				deleteGate(gridposition);
			}

		}
		gateMake();
		if(label.equals("MAKE LINE") && occupied == true)
			lineMake();
		if(label.equals("CHANGE VALUE"))
			valueChange();
		if(label.equals("DELETE LINE"))
			lineDelete();

	}

	/** delete a line, duuuh*/
	public void lineDelete()
	{
		// check if both gates are selected
		connectcounter++;
		if(connectcounter == 1)
		{
			connectxstart = gatex;
			connectystart = gatey;
			connectposition1 = gridposition;
		}
		if(connectcounter == 2)
		{
			connectxend = gatex;
			connectyend = gatey;
			connectposition2 = gridposition;
			deleteLine();
			connectcounter = 0;
		}

	}

	/** making the lineDelete function less convoluted*/
	public void deleteLine()
	{
		arrayposition1 = pManager.getPosition(gates,connectposition1,gatearraySIZE);
		arrayposition2 = pManager.getPosition(gates,connectposition2,gatearraySIZE);

		int pos1output = gates[arrayposition1].output;
		int pos1input1 = gates[arrayposition1].input1;
		int pos1input2 = gates[arrayposition1].input2;
		int pos2output = gates[arrayposition2].output;
		int pos2input1 = gates[arrayposition2].input1;
		int pos2input2 = gates[arrayposition2].input2;

			if(arrayposition1 == pos2output)
			{
				if(pos1input1 == arrayposition2)
					gates[arrayposition1].input1 = 51;
				if(pos1input2 == arrayposition2)
					gates[arrayposition1].input2 = 51;
				gates[arrayposition2].output = 51;
			}
			else if(arrayposition1 ==pos2input1)
			{
				gates[arrayposition2].input1 = 51;
				gates[arrayposition1].output = 51;
			}
			else if(arrayposition1 == pos2input2)
			{
				gates[arrayposition2].input2 = 51;
				gates[arrayposition1].output = 51;
			}
			else
			{
				JOptionPane.showMessageDialog(null,"to delete the line, is impossible, do not think "+
				"\n of it as deleting a line, think of it rather as deleting \n reality around "+
				"the line.... First you must realize, there is no line.\n(The weird kid, 'The Matrix')",
				"to delete the line, is impossible, do not think "+
				"\n of it as deleting a line, think of it rather as deleting \n reality around "+
				"the line.... First you must realize, there is no line.\n(The weird kid, 'The Matrix')"
				,JOptionPane.INFORMATION_MESSAGE);
			}

		for(counter = 0; linearraySIZE > counter; counter++)
		{
			if((connectposition1 == line[counter].position1 && connectposition2
			   == line[counter].position2) || (connectposition1 == line[counter].position2
			   && connectposition2 == line[counter].position1))
			{
				line[counter] = null;
				linearraySIZE = linearraySIZE - 1;
				deletedline = counter;
			}
		}

		if(deletedline < linearraySIZE)
		{
			for(counter = deletedline; counter <= linearraySIZE; counter++)
			{
				templine = line[counter + 1];
				line[counter] = templine;
			}
			line[counter] = null;
		}
		repaint();
	}

	/** making a line*/
	public void lineMake()
	{
		// check if the gate selected is the input or output gate
		connectcounter++;
		if(connectcounter == 1)
		{
			connectxstart = gatex;
			connectystart = gatey;
			connectposition1 = gridposition;
		}
		if(connectcounter == 2)
		{
			connectxend = gatex;
			connectyend = gatey;
			connectposition2 = gridposition;
			if(connectxstart < connectxend)
				makeLine();
			else if(connectxstart >= connectxend)
				JOptionPane.showMessageDialog(null,"please connect from left to right",
											  "please connect from left to right",
											  JOptionPane.ERROR_MESSAGE);
			connectcounter = 0;
		}

	}

	/** compartmentalizing  lineMake*/
	public void makeLine()
	{
		arrayposition1 = pManager.getPosition(gates,connectposition1,gatearraySIZE);
		arrayposition2 = pManager.getPosition(gates,connectposition2,gatearraySIZE);

		// checking if the gates already have input our outputs
		if(gates[arrayposition1].output > 50 && (gates[arrayposition2].input1
		   > 50 || gates[arrayposition2].input2 > 50))
		{

			// check which of the inputs(1 or 2) to create
			if(gates[arrayposition2].input1 > 50)
			{
				gates[arrayposition2].input1 = arrayposition1;
			}

			else if(gates[arrayposition2].input2 > 50)
			{
				gates[arrayposition2].input2 = arrayposition1;
			}

			gates[arrayposition1].output = arrayposition2;
			if((gates[arrayposition2].label).equals("NOT"))
			{
				gates[arrayposition2].input2 = arrayposition1;
			}
			line[linearraySIZE] =
			new LineConnect(connectxstart,connectystart,connectxend,connectyend, connectposition1,
							connectposition2);
			linearraySIZE++;
			repaint();

		}
		else
			JOptionPane.showMessageDialog(null," one of the gates already has an input/outputs",
										  " one of the gates already has an input/outputs",
										  JOptionPane.ERROR_MESSAGE);
	}

	/** change the value of a gate*/
	public void valueChange()
	{
		if(occupied == true)
		{
			String value = JOptionPane.showInputDialog(" The value for this gate?");
			realvalue = Integer.parseInt(value);

			while(realvalue > 1 || realvalue < 0)
			{
				value = JOptionPane.showInputDialog(" value illegal, new value?");
			    realvalue = Integer.parseInt(value);
			}
			// should the user press cancel, we dont want to still use that value, therefore:
			if(realvalue < 2 && realvalue > -1)
			{
				gates[pManager.getPosition(gates, gridposition, gatearraySIZE)].inputvalue1 =
					realvalue;
				if( gates[pManager.getPosition(gates, gridposition, gatearraySIZE)].label == "SWITCH")
					gates[pManager.getPosition(gates, gridposition, gatearraySIZE)].value =
					realvalue;
				repaint();
			}

			if(!(gates[pManager.getPosition(gates, gridposition, gatearraySIZE)].label == "SWITCH"))
			{
				value = JOptionPane.showInputDialog(" The 2nd value for this gate?");
				realvalue = Integer.parseInt(value);

				while(realvalue > 1 || realvalue < 0)
				{
					value = JOptionPane.showInputDialog(" value illegal, new value?");
				    realvalue = Integer.parseInt(value);
				}
				// should the user press cancel, we dont want to still use that value do we?, therefore:
				if(realvalue < 2 && realvalue > -1)
				{
					gates[pManager.getPosition(gates, gridposition, gatearraySIZE)].inputvalue2 =
					realvalue;
				}
			}
		}
	}

	//making a gate
	public void gateMake()
	{
		// if the button pressed was "and" "or" "switch" "not", and if the grid position is unoccupied
		// create a new gate/switch there, also, switches should be placed only on the left side
		if(label.equals("SWITCH") || label.equals("OR") || label.equals("AND") || label.equals("NOT"))
			if (occupied == false && !(label.equals("SWITCH") && gatex > 40))
			{
				 gates[gatearraySIZE] = new GateGuy(gatex,gatey,gridposition,label);
	    	 	 repaint();
				 gatearraySIZE++;
			}
	}

	/** the paintComponent method that paint everything in the circuitpanel
	 @param Graphics g the graphics operator*/
	public void paintComponent(Graphics g)
	{
		counter = 0;
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		g2.setColor(Color.white);
		//paint the grid
		while(y < 440)
		{
			y = y + 40;
		    Line2D.Double r = new Line2D.Double(0.0, y, 800.0, y);
		    g2.draw(r);
		}
		while(x < 840)
		{
			x = x + 40;
		    Line2D.Double r = new Line2D.Double(x, 0.0, x, 400.0);
		    g2.draw(r);
		}
		g2.setColor(Color.black);
		//paint all the gates
		while (gatearraySIZE > counter )
		{
			tempgate = gates[counter];
            tempgate.draw(g);
            counter = counter + 1;
		}
		counter = 0;
		while(linearraySIZE > counter)
		{
			templine = line[counter];
			templine.draw(g);
			counter++;
		}
	    x =0;
		y =0;

	}

	// getting the label of the button to know which button it was
	// @param String gogetter The label of the button
	public void setlabel(String gogetter)
	{
		label = gogetter;
    }

	// deleting a gate, duuh
	// @param int gatenumber the arrayposition of the gate to delete
    public void deleteGate(int gatenumber)
	{

		for(counter = 0; gatearraySIZE > counter; counter++)
		{
			if(gatenumber == gates[counter].gridposition)
			{
				System.out.println("counter" + counter);
				gates[counter] = null;
				deletedgate = counter;
				gatearraySIZE = gatearraySIZE - 1;
			}

		}

		if(deletedgate < gatearraySIZE)
		{
			for(counter = deletedgate; counter <= gatearraySIZE; counter++)
			{
				tempgate = gates[counter + 1];
				gates[counter] = tempgate;
			}
			gates[counter] = null;
		}
		repaint();
	}


	private PositionManager pManager = new PositionManager();
	public GateGuy gates[] = new GateGuy[50];
	private GateGuy gatekeeper[] = new GateGuy[50];
	private LineConnect line[] = new LineConnect[150];

	private int realvalue;
	private int arrayposition1;
	private int arrayposition2;
	private int connectposition1;
	private int connectposition2;
	private double connectxend;
	private double connectyend;
	private double connectxstart;
	private double connectystart;
	private int connectcounter = 0;
	private int deletedgate;
	private int deletedline;
	public int gatearraySIZE = 0;
	private int linearraySIZE = 0;
	private boolean  occupied;
    private int gridposition;
    private String label = null;
    //private int gatecounter = 0;
    private int counter;
    private GateGuy tempgate;
    private LineConnect templine;
    private double gatex = 0;
    private double gatey = 0;
    // the variables for drawing the grid lines
	private double x = 0;
	private double y = 0;
	private static final int PANELWIDTH = 800;
	private static final int PANELHEIGHT = 400;
}
