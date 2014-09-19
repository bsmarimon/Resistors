import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class MainFrame extends JFrame implements ActionListener{
	
	/**
	 * 
	 */

	private Resistor myResistor;
	private JButton findKey; 
	private JLabel output; 
	private JTextField field;
	
	public static void main(String[] args) {
		MainFrame frame = new MainFrame();
		frame.setVisible(true);
	}
	
	public MainFrame() {
		setSize(700, 110);
		output = new JLabel();
		findKey = new JButton("Enter color bands below (separated by spaces), then click");
		findKey.addActionListener(this);
		field = new JTextField();
		setLayout(new BorderLayout());	
		setTitle("Resistance Finder");
		add(output, BorderLayout.SOUTH);
		add(findKey, BorderLayout.NORTH);
		add(field, BorderLayout.CENTER);	
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getSource() == findKey) {
			String input = field.getText();
			if (input.equals("")) {
				return;
			}
			String outputStr;
			String[] inputArr = input.split(" ");
			if (inputArr.length == 1) {
				myResistor = new Resistor(inputArr[0]);
				outputStr = myResistor.findBands();
				output.setText(outputStr);
			} else {
				myResistor = new Resistor(inputArr);
				outputStr = myResistor.findResistance();
				output.setText(outputStr);
			}
		}
	}
}
