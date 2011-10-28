package work;

import javax.swing.*;

public class GUI {
	
	private JFrame frame;
	private JPanel panel;
	private JMenuBar menuBar;
	private JPanel leftPanel;
	private JPanel rightPanel;
	private JMenu file;
	private JMenu edit;
	private JMenu help;
	private JMenu purchase;
	private JMenuItem exit;
	
	public static void main (String args[]){
		GUI goo = new GUI();
	}
	
	public GUI() {
		frame = new JFrame("Library");
		menuBar = new JMenuBar();
		file = new JMenu("File");
		edit = new JMenu("Edit");
		help = new JMenu("Help");
		purchase = new JMenu("Borrow");
		exit = new JMenuItem("Exit");
		
		
		
		frame.add(menuBar);
		menuBar.add(file);
		menuBar.add(purchase);
		menuBar.add(edit);
		menuBar.add(help);
		file.add(exit);
		
		frame.setDefaultCloseOperation(0);
		frame.setContentPane(panel);
		frame.pack();
	}
	
}
