public class logicgate
{
	logicgate(String pass_type)
	{

		logictype = "" + pass_type;

	}

	/*-------------------   pass values for gates : "SWITCH ","AND" , "OR" , "NOT"  -------------*/

	public void set_logic_input1(int pass_input1)
	{
		inputvalue1 = pass_input1;
		output = inputvalue1;

	}

	public void set_logic_input2(int pass_input2)
	{
		inputvalue2 = pass_input2;
		if(inputvalue1!=-1)
		{

			evaluate();
		}


	}

	public String get_logic_type()
	{


		return logictype;
	}

	public int get_logic_output()
	{

		return output;
	}

	/*------------------   Calculate the output for specified gate     -------------------------*/
	public void evaluate()
	{
		if(logictype.compareTo("SWITCH")==0)
		{

			output = inputvalue1;

		}

		if(logictype.compareTo("AND")==0)
		{

			output = inputvalue1 * inputvalue2;

		}

		if(logictype.compareTo("OR")==0)
		{


			output = inputvalue1 + inputvalue2;
			if(output==2)
			{
				output = output - 1;
			}
		}

		if(logictype.compareTo("NOT")==0)
		{

			if(inputvalue1 == 1)
			{
				output = 0;
			}
			if(inputvalue1 == 0)
			{
				output = 1;
			}

		}


	}

	public int inputvalue1 = -1;
	public int inputvalue2 = -1;
	public int output = -1;
	public String logictype;


}
