import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.math.*;

public class investment_value_calc {
	private JFrame frame = new JFrame("Investment-value Calculator");
	private JPanel panel = new JPanel();
	private JLabel[] label = new JLabel[4];
	private JTextField[] textField = new JTextField[4];
	private JButton calBtn = new JButton("Calculate");
	



	private int investmentAmount, year;
	private double futureValue, monthlyInterestRate, annualInterestRate;


	public investment_value_calc() {
		createFrame();
		addComponents();
		addEventListener();
	}


	public void createFrame() {
		frame.setSize(520, 200);

		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.add(panel);

		panel.setLayout(new GridBagLayout());
		

		frame.setVisible(true);
	}

	public void addComponents() {
		GridBagConstraints gc = new GridBagConstraints();

		gc.gridx = 0;
	    gc.gridy = GridBagConstraints.RELATIVE;
	    gc.gridwidth = 2;
	    gc.gridheight = 1;
	    gc.insets = new Insets(5, 5, 5, 5);
	    gc.anchor = GridBagConstraints.WEST;

		String[] labels = {"Investment Amount: ", "Number of Years: ", "Annual Interest Rate: ", "Future Value: "};
		for(int i = 0 ; i < labels.length; i++) {
			label[i] = new JLabel(labels[i], SwingConstants.LEFT);
			panel.add(label[i], gc);
		}

		gc.gridx = 2;
	    gc.gridy = 0;
	    gc.weightx = 1.0;
	    gc.fill = GridBagConstraints.HORIZONTAL;

	    textField[0] = new JTextField(10);
	    textField[0].setBorder(BorderFactory.createLineBorder(Color.GRAY));
	    textField[0].setHorizontalAlignment(JTextField.RIGHT);
	    panel.add(textField[0], gc);

	    for(int i = 1 ; i < labels.length; i++) {
	    	gc.gridx = 2;
   			gc.gridy = GridBagConstraints.RELATIVE;
			textField[i] = new JTextField(10);
			textField[i].setBackground(Color.WHITE);
			textField[i].setBorder(BorderFactory.createLineBorder(Color.GRAY));
			textField[i].setHorizontalAlignment(JTextField.RIGHT);

			if(i == 3) {
				textField[i].setBorder(BorderFactory.createLineBorder(Color.GRAY));
				textField[i].setEditable(false);
			}

			panel.add(textField[i], gc);
		}

		GridBagConstraints noFill = new GridBagConstraints();

		noFill.gridx = 2;
   		noFill.gridy = GridBagConstraints.RELATIVE;
   		noFill.weightx = 1.0;
   		noFill.gridwidth = 2;
	    noFill.gridheight = 1;
	    noFill.insets = new Insets(5, 5, 5, 5);
	    noFill.anchor = GridBagConstraints.EAST;

		panel.add(calBtn, noFill);

		panel.revalidate();
		panel.repaint();

		frame.getContentPane();
	}


	private class IsNotIntException extends RuntimeException {
		public IsNotIntException() {}
	}

	private void getInput() {
		
	}

	private void addEventListener() {
        
	}

	private class calculate implements ActionListener{
		public void actionPerformed(ActionEvent e) {
        	getInput();

        	JButton btn = (JButton) e.getSource();
			String command = btn.getActionCommand();

            
        }
	}

	private double convertInterestRate(double annualInterestRate) {
		monthlyInterestRate = Math.pow((1 + annualInterestRate),(1/12)) - 1;
		return monthlyInterestRate;
	}

	private double futureValue(int investmentAmount, double monthlyInterestRate, int year) {
		futureValue = investmentAmount * Math.pow((1 + monthlyInterestRate),(year*12));
		return futureValue;
	}

	private static double round(double value, int places) {
    	if (places < 0) throw new IllegalArgumentException();

	    BigDecimal bd = BigDecimal.valueOf(value);
	    bd = bd.setScale(places, RoundingMode.HALF_UP);
	    return bd.doubleValue();
	}

	public static void main(String[] args) {
		new investment_value_calc();
	}
}