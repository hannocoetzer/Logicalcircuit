// the class that sets the positions , both grid and x-y values for all the components
public class PositionManager
{
	/**set the gridposition and return it
	 @param double Gatex the x co-ordinates
	 @param double Gatey the y co-ordinates
	 @return gridpositionsend the gridposition of the x and y co-ordinates*/
	public int getPosition(double Gatex,double Gatey)
	{
		x = (int)Gatex;
		y = (int)Gatey;
		gridpositionsend = 0;
		gridpositioncounter = 0;
		//setting the position to fit in the grid
		for(linecounter = 0;linecounter <= 9; linecounter++)
		{
			position = 40;
			for(blockcounter  = 0; blockcounter <= 19; blockcounter++)
			{
				if((x <= position) && (y <= ((linecounter + 1) * 40))
				  && x > (position - 40) && y > ((linecounter + 1) * 40) - 40)
				{
					x = position - 34;
					y = ((linecounter + 1) * 40) - 34;
					gridpositionsend = gridpositioncounter;
				}
				position = position + 40;
				gridpositioncounter++;
			}
		}
		return gridpositionsend;
	}

	/** the method to return the arrayposition of the given gates gridposition
		@param GateGuy[] gates the array of gates to be checked for the needed one
		@param int gateposition the gridposition of the gate
		@param int gatearraySIZE the size of the gate array "gates"
		@return arrayposition the array position of the gate*/
	public int getPosition(GateGuy[] gates, int gateposition, int gatearraySIZE)
	{
		for(counter = 0; counter < gatearraySIZE; counter++)
			{
				if(gateposition == gates[counter].gridposition)
				arrayposition = counter;
			}
		return arrayposition;
	}

	/** getting the x value of a gate
		@return x the x value to be returned*/
	public int getX()
	{
		return x;
	}

	/** getting the y value of a gate
		@return y the y value to be returned*/
	public int getY()
	{
		return y;
	}

	/** checking if a grid position is already occupied
		@param Gateguy[] gates the array to be checked
		@param int Possibleposition the gridposition to be checked aainst the array
		@param int gatearraySIZE the size of the "gates" array
		@return boolean occupied is the gridposition occupied or not?*/
	public boolean detectPosition(GateGuy[] gates, int Possibleposition, int gatearraySIZE)
	{
		occupied = false;
		for(counter = 0; counter < gatearraySIZE; counter++)
		{
			if(Possibleposition == gates[counter].gridposition)
				occupied = true;
		}
		return occupied;
	}

	private int arrayposition;
	private int counter;
	private boolean occupied;
	private int x;
	private int y;
	private int gridpositionsend;
	private int gridpositioncounter;
	private int linecounter;
	private int position;
	private int positioncounter;
    private int blockcounter;
}

