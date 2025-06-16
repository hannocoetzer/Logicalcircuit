import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/** the primary class*/
public class CircuitTest
{
	/** the main method
		@param String args[] as usual*/
	public static void main(String args[])
	{
		JFrame appFrame = new CircuitFrame();
		appFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		appFrame.show();
	}
}