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
	private JDialog nfExDialog = new JDialog(frame, "Exception!", true);
	private JDialog notIntExDlg = new JDialog(frame, "Exception!", true);


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

	private void addComponents() {
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



	private void getInput() throws IsNotIntException, NumberFormatException {
			if(!isInt(textField[0].getText()) || !isInt(textField[1].getText())) {
				throw new IsNotIntException();
			}

			investmentAmount = Integer.parseInt(textField[0].getText());
			year = Integer.parseInt(textField[1].getText());

			annualInterestRate = Double.parseDouble(textField[2].getText());
	}



	private static boolean isInt(String str) {

	  	try {
	    	int x = Integer.parseInt(str);
	      	return true;
		} catch (NumberFormatException e) {
	    	return false;
		}
  	
	}

	private void addEventListener() {
        calBtn.addActionListener(new calculate());
	}

	private class calculate implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			try{
				if(!isInt(textField[0].getText()) || !isInt(textField[1].getText())) {
					throw new IsNotIntException();
				}
				getInput();
			}
			catch(IsNotIntException ex) {
				createDialog1();
			}
			catch(NumberFormatException ex) {
				createDialog2();
			}

        	futureValue = futureValue(investmentAmount, annualInterestRate, year);

        	textField[3].setText("$" + round(futureValue, 2));
        }
	}

	private double futureValue(int ivnAmnt, double air, int year) {
		futureValue = ivnAmnt * Math.pow((1 + air), year);
		return futureValue;
	}

	private static double round(double value, int places) {
    	if (places < 0) throw new IllegalArgumentException();

	    BigDecimal bd = BigDecimal.valueOf(value);
	    bd = bd.setScale(places, RoundingMode.HALF_UP);
	    return bd.doubleValue();
	}

	private void createDialog1() {
		nfExDialog.setSize(200, 100);
		nfExDialog.setLocationRelativeTo(null);
		nfExDialog.setLayout(new BorderLayout());
		
		JPanel panel = new JPanel();
		JPanel panelBtn = new JPanel();
		JButton button = new JButton("Ok");
		panel.setLayout(new BorderLayout());
		nfExDialog.add(panel);
		nfExDialog.add(panelBtn, BorderLayout.SOUTH);
		JLabel label = new JLabel("Please enter a valid interger!", JLabel.CENTER);
		label.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		panel.add(label);
		panelBtn.add(button);
		button.addActionListener(new dialog1CloseAction());
		nfExDialog.setResizable(false);

		nfExDialog.setVisible(true);
	}

	private void createDialog2() {
		notIntExDlg.setSize(200, 100);
		notIntExDlg.setLocationRelativeTo(null);
		notIntExDlg.setLayout(new BorderLayout());
		
		JPanel panel = new JPanel();
		JPanel panelBtn = new JPanel();
		JButton button = new JButton("Ok");
		panel.setLayout(new BorderLayout());
		notIntExDlg.add(panel);
		notIntExDlg.add(panelBtn, BorderLayout.SOUTH);
		JLabel label = new JLabel("Please enter a valid double!", JLabel.CENTER);
		label.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		panel.add(label);
		panelBtn.add(button);
		button.addActionListener(new dialog2CloseAction());
		notIntExDlg.setResizable(false);

		notIntExDlg.setVisible(true);
	}

	private class dialog1CloseAction implements ActionListener{
		public void actionPerformed(ActionEvent e) {
            nfExDialog.dispose();
        }
	}

	private class dialog2CloseAction implements ActionListener{
		public void actionPerformed(ActionEvent e) {
            notIntExDlg.dispose();
        }
	}

	public static void main(String[] args) {
		investment_value_calc c = new investment_value_calc();
	}
}