package retire;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class RetirePanel extends JApplet {

	private RetireModel rm;

	private ButtonListener listener;
	private JButton calc;
	private JButton reset;

	private JPanel butPanel;
	private JPanel qaPanel;

	private JLabel nameQ;
	private JTextField nameF;

	private JLabel sexQ;
	private JComboBox sexBox;
	private String[] sexString;

	private JLabel aTRQ;
	private String[] ageToRetire;
	private JComboBox aTRBox;

	private JLabel lifeExpQ;
	private String[] lifeExp;
	private JComboBox lifeExpCombo;

	private JLabel ageQ;
	private JLabel incomeQ;
	private JTextField ageF;
	private JTextField incomeF;

	private JLabel desiredSalary;
	private JTextField desiredSalaryField;

	private JLabel otherSalary;
	private JTextField otherSalaryField;

	private JLabel totalToSave;
	private JLabel totalTS;
	private JLabel totalToSaveMonthly;

	private JLabel pension1;
	private JLabel pension2;
	private JLabel savings1;
	private JLabel savings2;
	private JLabel otherJob1;
	private JLabel otherJob2;
	private JTextField pensionField;
	private JTextField savingsField;
	private JTextField otherJobField;

	private JPanel topPanel;
	private JPanel botPanel;

	private TitledBorder bt;
	private Border lb;
	private TitledBorder tt;

	private JButton exit;
	private JButton print;

	private JButton nameH;
	private JButton ageH;
	private JButton sexH;
	private JButton ageToRetireH;
	private JButton annualIncomeH;
	private JButton lifeExpH;
	private JButton desiredSalaryH;
	private JButton otherSalaryH;
	private JButton pensionH;
	private JButton savingsH;
	private JButton otherJobH;

	private JPanel pensionPanel;
	private JPanel savingsPanel;
	private JPanel partTimePanel;

	private String[] incomePerc;
	private JComboBox incomePercBox;

	public RetirePanel() {
		rm = new RetireModel();

		setLayout(new BorderLayout());

		qaPanel = new JPanel();
		qaPanel.setLayout(new GridLayout(2, 2, 20, 20));
		// rows, columns, horizontal gap, vertical gap
		add(qaPanel, BorderLayout.CENTER);

		lb = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
		bt = BorderFactory.createTitledBorder(lb, "Retirement Information");
		tt = BorderFactory.createTitledBorder(lb, "Personal Information");

		tt.setTitleColor(Color.BLACK);
		bt.setTitleColor(Color.BLACK);

		pensionPanel = new JPanel();
		savingsPanel = new JPanel();
		partTimePanel = new JPanel();

		pensionPanel.setLayout(new GridLayout(2,0));
		savingsPanel.setLayout(new GridLayout(3,0));
		partTimePanel.setLayout(new GridLayout(3,0));

		topPanel = new JPanel();
		botPanel = new JPanel();
		topPanel.setLayout(new GridLayout(6, 3, 10, 20));
		botPanel.setLayout(new GridLayout(7, 3, 10, 1));
		botPanel.setBorder(bt);
		topPanel.setBorder(tt);

		topPanel.setSize(40, 800);

		qaPanel.add(topPanel);
		qaPanel.add(botPanel);

		setCombos();
		createButtons();
		setJLabels();
		setListen();
		setUpPanels();

		topPanel.setBackground(new Color(60,179,113));
		qaPanel.setBackground(new Color(60,179,113));
		botPanel.setBackground(new Color(60,179,113));
		pensionPanel.setBackground(new Color(60,179,113));
		partTimePanel.setBackground(new Color(60,179,113));
		savingsPanel.setBackground(new Color(60,179,113));
		butPanel.setBackground(new Color(60,179,113));

		print.setEnabled(false);
	}

	public void setJLabels() {
		nameQ = new JLabel("Name:");
		nameF = new JTextField();
		nameF.setHorizontalAlignment(nameF.CENTER);

		ageQ = new JLabel("Age:");
		ageQ.setVisible(true);
		ageF = new JTextField();
		ageF.setHorizontalAlignment(ageF.CENTER);

		incomeQ = new JLabel("Annual Income:");
		incomeQ.setVisible(true);
		incomeF = new JTextField("");
		incomeF.setHorizontalAlignment(incomeF.CENTER);

		desiredSalary = new JLabel("1. How much do you want to make while " +
		"in retirement?");
		desiredSalary.setVisible(true);
		desiredSalaryField = new JTextField();
		desiredSalaryField.setHorizontalAlignment(desiredSalaryField.CENTER);

		otherSalary = new JLabel("5. Do you have any other form of income");
		otherSalaryField = new JTextField("0");
		otherSalaryField.setHorizontalAlignment(otherSalaryField.CENTER);

		pension1 = new JLabel("2. If you have a pension please enter how much");
		pension2 = new JLabel("    is currently in it. If not skip this question.");
		pensionField = new JTextField();
		pensionField.setHorizontalAlignment(pensionField.CENTER);
		pensionField.setText("0");

		savings1 = new JLabel("3. If applicable, please enter the amount ");
		savings2 = new JLabel("    you haved saved in a savings account ");
		savingsField = new JTextField();
		savingsField.setHorizontalAlignment(savingsField.CENTER);
		savingsField.setText("0");

		otherJob1 = new JLabel("4. If applicable, enter the amount you plan");
		otherJob2 = new JLabel("    earn while in retirement");
		otherJobField = new JTextField();
		otherJobField.setText("0");
		otherJobField.setHorizontalAlignment(otherJobField.CENTER);

		
		totalTS = new JLabel("How much you need to save: ");
		totalTS.setFont(new Font("Arial", Font.PLAIN, 18));
		totalToSave = new JLabel("");
		totalToSaveMonthly = new JLabel("");

		sexQ = new JLabel("Are you male or female?");

		aTRQ = new JLabel("When do you want to retire?");
		aTRQ.setVisible(true);

		lifeExpQ = new JLabel("How long do you expect to live?");
	}

	private void setUpPanels() {
		topPanel.add(nameQ);
		topPanel.add(nameF);
		topPanel.add(nameH);

		topPanel.add(ageQ);
		topPanel.add(ageF);
		topPanel.add(ageH);

		topPanel.add(sexQ);
		topPanel.add(sexBox);
		topPanel.add(sexH);

		topPanel.add(aTRQ);
		topPanel.add(aTRBox);
		topPanel.add(ageToRetireH);

		topPanel.add(lifeExpQ);
		topPanel.add(lifeExpCombo);
		topPanel.add(lifeExpH);

		topPanel.add(incomeQ);
		topPanel.add(incomeF);
		topPanel.add(annualIncomeH);

		botPanel.add(desiredSalary);
		botPanel.add(desiredSalaryField);
		botPanel.add(desiredSalaryH);

		pensionPanel.add(pension1);
		pensionPanel.add(pension2);
		botPanel.add(pensionPanel);
		botPanel.add(pensionField);
		botPanel.add(pensionH);

		botPanel.add(savingsPanel);
		savingsPanel.add(savings1);
		savingsPanel.add(savings2);
		botPanel.add(savingsField);
		botPanel.add(savingsH);

		partTimePanel.add(otherJob1);
		partTimePanel.add(otherJob2);
		botPanel.add(partTimePanel);
		botPanel.add(otherJobField);
		botPanel.add(otherJobH);

		botPanel.add(otherSalary);
		botPanel.add(otherSalaryField);
		botPanel.add(otherSalaryH);

		botPanel.add(totalTS);
		botPanel.add(totalToSave);
		botPanel.add(totalToSaveMonthly);

	}

	public void setCombos() {
		sexString = new String[]{"Male", "Female"};
		sexBox = new JComboBox(sexString);

		ageToRetire = new String[]{"55 or less", "56 - 62", "63 - 69", "70" +
		" or over"};
		aTRBox = new JComboBox(ageToRetire);

		lifeExp = new String[]{"86 or less", "87 - 92", "93 or over"};
		lifeExpCombo = new JComboBox(lifeExp);                     

		incomePerc = new String[]{"70%-80%", "80%-90%", "90% or more"};
		incomePercBox = new JComboBox(incomePerc);
	}

	public void createButtons() {
		calc = new JButton("Show me the money!");
		reset = new JButton("Reset");
		print = new JButton("Print");
		exit = new JButton("Exit");

		nameH = new JButton("Help");
		ageH = new JButton("Help");
		sexH = new JButton("Help");
		ageToRetireH = new JButton("Help");
		annualIncomeH = new JButton("Help");
		lifeExpH = new JButton("Help");
		desiredSalaryH = new JButton("Help");
		otherSalaryH = new JButton("Help");
		pensionH = new JButton("Help");
		savingsH = new JButton("Help");
		otherJobH = new JButton("Help");

		nameH.setSize(5, 5);

		butPanel = new JPanel();
		butPanel.setLayout(new FlowLayout());
		butPanel.add(calc);
		butPanel.add(print);
		butPanel.add(reset);
		butPanel.add(exit);

		add(butPanel, BorderLayout.SOUTH);
	}

	private void setListen() {
		listener = new ButtonListener();

		nameH.addActionListener(listener);
		ageH.addActionListener(listener);
		sexH.addActionListener(listener);
		ageToRetireH.addActionListener(listener);
		annualIncomeH.addActionListener(listener);
		lifeExpH.addActionListener(listener);
		desiredSalaryH.addActionListener(listener);
		otherSalaryH.addActionListener(listener);
		pensionH.addActionListener(listener);
		savingsH.addActionListener(listener);
		otherJobH.addActionListener(listener);

		reset.addActionListener(listener);
		calc.addActionListener(listener);
		exit.addActionListener(listener);
		print.addActionListener(listener);
	}

	private void clearForm() {
		ageF.setText("");
		incomeF.setText("");
		otherSalaryField.setText("");
		desiredSalaryField.setText("");
		totalToSave.setText("");
	}

	private void calculate() {
		rm.setName(nameF.getText());
		int i = sexBox.getSelectedIndex();
		if (i == 0)
			rm.setSex("male");
		else
			rm.setSex("female");

		//		System.out.println(""+ i);
		i = aTRBox.getSelectedIndex();
		if (i == 0) {
			rm.setAgeToRetire(55);
		} else if (i == 1)
			rm.setAgeToRetire(60);
		else if (i == 2)
			rm.setAgeToRetire(65);
		else if (i == 3)
			rm.setAgeToRetire(70);


		i = lifeExpCombo.getSelectedIndex();
		if (i == 0) {
			rm.setLifeExp(82);
		} else if (i == 1)
			rm.setLifeExp(90);
		else if (i == 2)
			rm.setLifeExp(96);

		//		System.out.println(""+ i);
		//		System.out.println(ageF.getText());
		//		System.out.println(incomeF.getText());
		//		System.out.println(desiredSalaryField.getText());
		//		System.out.println(otherSalaryField.getText());
		//		
		if(ageF.getText().length() != 0)
			rm.setAge(Integer.parseInt(ageF.getText()));

		if(incomeF.getText().length()!=0)
			rm.setASalary(Integer.parseInt(deleteComma(incomeF.getText())));
		else
			rm.setASalary(0);

		if(desiredSalaryField.getText().length()!=0)
			rm.setDSalary(Integer.parseInt(deleteComma(desiredSalaryField.getText())));
		else
			rm.setDSalary(0.0);

		if(otherSalaryField.getText().length()!=0)
			rm.setOtherIncome(Integer.parseInt(deleteComma(otherSalaryField.getText())));
		else
			rm.setOtherIncome(0);

		String temp = rm.calculate() + "";
		int count = 0;
		for(int j = temp.length()-1; j>= 0; j--){
			count++;
			if(count == 3 && j !=0){
				temp = temp.substring(0,j) + "," + temp.substring(j);
				count = 0;
			}
		}

		totalToSave.setFont(new Font("Arial", Font.PLAIN, 16));
		totalToSave.setText("$" + temp + " every year");

		temp = ((int) rm.calculate()/12) +"";
		count = 0;
		for(int j = temp.length()-1; j>= 0; j--){
			count++;
			if(count == 3 && j !=0){
				temp = temp.substring(0,j) + "," + temp.substring(j);
				count = 0;
			}
		}
		totalToSaveMonthly.setFont(new Font("Arial", Font.PLAIN, 16));
		totalToSaveMonthly.setText("$" + temp + " every month");
	}

	private String deleteComma(String str){
		for(int i = 0; i < str.length(); i++)
			if(str.charAt(i) == ',')
				str = str.substring(0, i) + str.substring(i+1);

		return str;
	}

	private void print() {
		JFrame f = new JFrame("Print");

		JTextArea printout = new JTextArea(10,5);
		printout.setSize(500,600);
		printout.setEditable(false);
		printout.setFont(new Font("Serif", Font.ITALIC, 18));
		printout.setEditable(false);

		String nl = "\n";

		String txt = "\tThank you for using our retirement calculator" + nl + nl;
		printout.setText(txt);

		txt =   "Personal Information" + nl;
		printout.append(txt);

		txt =	"\tName\t:\t" + rm.getName() + nl + nl +
		"\tAge\t:\t" + rm.getAge() + nl + nl +
		"\tGender\t:\t" + rm.getSex() + nl + nl +
		"\tAge to retire\t:\t" + rm.getAgeToRetire() + nl + nl +
		"\tLife Expectancy :\t" + rm.getExp() + nl + nl +
		"\tAnnual Income :\t" + incomeF.getText() + nl + nl + nl;
		printout.append(txt);

		txt =	"Retirement Information" + nl;
		printout.append(txt);

		txt = "\tDesired salary in Retirement\t:\t" + desiredSalaryField.getText() + nl + nl +
		"\tMoney from Pension\t:\t" + pensionField.getText() + nl + nl +
		"\tMoney from Savings\t:\t" + rm.getSavings() + nl + nl +
		"\tMoney earned in retirement\t:\t" + otherJobField.getText() + nl + nl +
		"\tOther Income\t\t:\t" + rm.getOtherIncome() + nl + nl;
		printout.append(txt);

		txt = "How much you need to save: " + totalToSave.getText() + nl;
		printout.append(txt);

		txt = "\t\t " + totalToSaveMonthly.getText() + nl;

		printout.append(txt);




		f.add(printout);

		f.setSize(500,600);
		f.setVisible(true);

	}

	private class ButtonListener implements ActionListener {

		public void actionPerformed(ActionEvent event) {

			JComponent comp = (JComponent) event.getSource();

			if (comp == calc){
				calculate();
				print.setEnabled(true);
			}

			if (comp == print)
				print();

			if (comp == nameH)
				JOptionPane.showMessageDialog(getParent(), 
						"Please enter your first and last name.", 
						"Help", 1);

			if (comp == ageH)
				JOptionPane.showMessageDialog(getParent(), 
						"Please enter your age. (ex. 21)", "Help", 1);

			if (comp == sexH)
				JOptionPane.showMessageDialog(getParent(), 
						"Please enter your gender. (male or female)",
						"Help", 1);

			if (comp == ageToRetireH)
				JOptionPane.showMessageDialog(getParent(), 
						"Enter the age at which you want to be retired.",
						"Help", 1);

			if (comp == annualIncomeH)
				JOptionPane.showMessageDialog(getParent(), 
						"Type how much you make a year.", "Help", 1);

			if (comp == lifeExpH)
				JOptionPane.showMessageDialog(getParent(), 
						"At what age do you expect to live until.", "Help", 1);

			if (comp == desiredSalaryH)
				JOptionPane.showMessageDialog(getParent(), 
						"This is how much you want to make. You can probably\n" +
						"estimate 70%-80% of your current income would be needed\n" +
						"to cover the basics, while 80%-90% will cover everything\n" +
						"with anything more then 90% being luxury.", "Help", 1);

			if (comp == otherSalaryH)
				JOptionPane.showMessageDialog(getParent(), 
						"Do you have any other sort of expected salary such " +
						"that wasn't listed above.", "Help", 1);

			if (comp == pensionH)
				JOptionPane.showMessageDialog(getParent(), 
						"If you have some type of pension such as a 401k\n" +
						"then please put how much you have in it. If you do\n" +
						"not have a pension, skip this question.", "Help", 1);

			if (comp == savingsH)
				JOptionPane.showMessageDialog(getParent(), 
						"If you have a savings account, put how much you\n" +
						"currently have in there into this field. If you\n" +
						"don't have one, skip this question.", "Help", 1);

			if (comp == otherJobH)
				JOptionPane.showMessageDialog(getParent(), 
						"If you have another job please put how much\n" +
						"you plan to make a year at it. If you don't plan on\n" +
						"working while retired just skip this question.", "Help", 1);
		}
	}
}
