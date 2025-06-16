import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JOptionPane;

/** the panel that displays the circuits and the panel of buttons to manipulate it, you guessed it,
	all in one frame*/
public class CircuitFrame extends JFrame
{
	//construct the frame (constructor)
	public CircuitFrame()
	{
		//the panel that draws the circuits
		circPanel = new CircuitPanel();

		//add panel to contentpane (in the center, meaning all unoccupied space)
		getContentPane().add(circPanel, BorderLayout.CENTER);

		createButtonPanel();

		pack();
	}

	/** the method to make buttons*/
	public JButton makeButton(final String label)
	{
	    JButton button = new JButton(label);
	    //the buttonlistener
		class MoveButtonListener implements ActionListener
		{
			public void actionPerformed(ActionEvent event)
			{
                buttonlabel = label;
                circPanel.setlabel(label);
                if (label.equals("EXECUTE"))
					{
						runProgram();
					}
				repaint();
		    }
	   	}
	    ActionListener listener = new MoveButtonListener();
		button.addActionListener(listener);
		return button;
	}

	/** executing the logicfunctions of the gates*/
	public void runProgram()
	{
		gates = circPanel.gates;
		gatearraySIZE = circPanel.gatearraySIZE;
		transfer.set_array(gates,gatearraySIZE);
		GateGuy[] gatekeeper2 = transfer.get_array();
		circPanel.gates = gatekeeper2;
		highestx = circPanel.gates[0].x;
		int output = 0;
		for(counter = 1; counter < gatearraySIZE; counter++)
		{

			if(highestx < circPanel.gates[counter].x)
			{
				highestx = circPanel.gates[counter].x;
				output = circPanel.gates[counter].value;
			}

		}
		JOptionPane.showMessageDialog(null, "The final output of the circuit is "+ output,
									  "The final output of the circuit is "+ output,
									  JOptionPane.INFORMATION_MESSAGE);
	}

	/** creates the button panel with the buttons at the bottom of the frame*/
	private void createButtonPanel()
	{
		final JTextField xField = new JTextField(5);
		final JTextField yField = new JTextField(5);

		// the panel for holding the user interface components
		JPanel controlPanel = new JPanel();

		// the buttons to create and remove the gates/switches

		controlPanel.add(makeButton("AND"));
		controlPanel.add(makeButton("OR"));
		controlPanel.add(makeButton("NOT"));
		controlPanel.add(makeButton("SWITCH"));
		controlPanel.add(makeButton("DELETE GATE"));
        controlPanel.add(makeButton("MAKE LINE"));
        controlPanel.add(makeButton("DELETE LINE"));
        controlPanel.add(makeButton("EXECUTE"));
        controlPanel.add(makeButton("CHANGE VALUE"));

		getContentPane().add(controlPanel, BorderLayout.SOUTH);
	}

	private int highestx;
	private int counter;
	private logicstransfer transfer = new logicstransfer();
	private int gatearraySIZE;
	private GateGuy[] gates = new GateGuy[50];
	private CircuitPanel circPanel;
	private String buttonlabel;
}
