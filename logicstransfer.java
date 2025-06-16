import javax.swing.JOptionPane;

public class logicstransfer
{
	logicstransfer()
	{

	}

	/**
		This method receives all the gates added from the GUI and convert it into an array
		that is usable for the logicgate class that will calculate the necessary logic
		connection.
		@param recieves all the gates as an array
	*/
	public void set_array(GateGuy[] pass_gate,int pass_size)
	{

		returned = pass_gate;
		gateguySize = pass_size;

		for(int x = 0;x<gateguySize;x++)
		{
			gates[x] = new logicgate(pass_gate[x].label);

			if("SWITCH".compareTo(pass_gate[x].label)==0)
			{

				gates[x].set_logic_input1(pass_gate[x].inputvalue1);
			}
			else
			{
				gates[x].set_logic_input1(pass_gate[x].inputvalue1);
				gates[x].set_logic_input2(pass_gate[x].inputvalue2);


			}
		}

		for(int x = 0;x<gateguySize;x++)
		{
			if(pass_gate[x].input1!=51&&pass_gate[x].input2!=51)
			{

				linked[0][l_count] = pass_gate[x].input1;
				linked[1][l_count] = x;
				l_count++;
				linked[0][l_count] = pass_gate[x].input2;
				linked[1][l_count] = x;
				l_count++;

			}

		}

		execute();
	}

	/**
		This method returns the array with all the values that have been calculated and pass it
		back to the GUI
		@return return the calculated array
	*/
	public GateGuy[] get_array()
	{


		for(int x = 0;x<gateguySize;x++)
		{

			returned[x].value = gates[x].output;


		}

		return returned;
	}

	/**
		This method run through all the connections made and connect the gates from left to
		right and rearange the connections made

	*/
	private void execute()
	{

		c_count = 0;

		for(int x = 0;x<l_count;x++)
		{
			int temp;

			temp = linked[1][x];

			/*---------------------- only for NOT connections -------------------------------------*/
			if("NOT".compareTo(gates[temp].logictype)==0)
			{
				connectlist[0][c_count] = linked[0][x];
				connectlist[1][c_count] = linked[0][x];
				connectlist[2][c_count] = temp;
				c_count++;


			}
			else
			{
				for(int y = x+1;y<l_count;y++)
				{
					/*----------- this is for the connection between SWITCHES , AND , OR gates ---- */
					if(temp==linked[1][y])
					{

						connectlist[0][c_count] = linked[0][x];
						connectlist[1][c_count] = linked[0][y];
						connectlist[2][c_count] = temp;
						c_count++;

					}
				}
			}
		}

		/*------------------ this pass the values to all the connected gates and
								calculate the values       --------------------------------------*/
		for(int x = 0;x<c_count;x++)
		{
			gates[connectlist[2][x]].set_logic_input1(gates[connectlist[0][x]].get_logic_output());
			gates[connectlist[2][x]].set_logic_input2(gates[connectlist[1][x]].get_logic_output());

		}
	}

	private GateGuy returned[] = new GateGuy[50];
	private int [][] connectlist = new int[3][150];
	private int [][] linked = new int[3][150];
	private int counter = 0;
	private int l_count = 0;
	private int c_count = 0;
	private logicgate[] gates = new logicgate[150];
	private int gateguySize = 0;

}