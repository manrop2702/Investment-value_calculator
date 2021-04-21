import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.math.*;

public class investment_value_calc {
	private JFrame frame = new JFrame("Investment-value Calculator");
	private JPanel panel = new JPanel();
	private JLabel[] label = new JLabel[4];
	private JTextField[] textField = new JTextField[4];
	private JButton calBtn = new JButton();
	private GridBagConstraints gc = new GridBagConstraints();

	private int investmentAmount, year;
	private double futureValue, monthlyInterestRate, annualInterestRate;


	public investment_value_calc() {
		createFrame();
		addComponents();
		addEventListener();
	}


	public void createFrame() {
		frame.setSize(400, 300);

		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(panel);

		panel.setLayout(new GridBagLayout());

		frame.setVisible(true);
	}

	public void addComponents() {

	}


	private void addEventListener() {
         
	}

	private double convertInterestRate(double annualInterestRate) {
		monthlyInterestRate = (1 + annualInterestRate)^(1/12) - 1;
		return monthlyInterestRate;
	}

	private double futureValue(int investmentAmount, double monthlyInterestRate, int year) {
		futureValue = investmentAmount * (1 + monthlyInterestRate)^(year*12);
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