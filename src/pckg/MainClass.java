package pckg;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.io.IOException;
import java.net.MalformedURLException;

import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class MainClass extends JApplet  {
	
	InternetConnection connection;
    JLabel enterPhrase; JLabel enterPhrase1;
    JTextField phrase;
    Dimension d;
    JButton search;
    JPanel panel;
    static JFrame frame;
    
    
	MainClass() {
		d=Toolkit.getDefaultToolkit().getScreenSize();
		connection = new InternetConnection();
		enterPhrase = new JLabel("Enter search phrase: ");
		search = new JButton("Search!");
		search.setBounds(700,50,200,50);
		enterPhrase1 = new JLabel("Or, enter search topic: ");
		frame = new JFrame();
		panel=new JPanel(null);
		frame.setBounds(0, 0, d.width, d.height);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		phrase=new JTextField();
		phrase.setSize(400, 100);
		phrase.setLocation(200,5);
		enterPhrase.setSize(200,50);
		enterPhrase1.setSize(200,50);
		enterPhrase1.setLocation(5,100);
		enterPhrase.setLocation(5,5);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.add(panel);
			
	}
	
	public void init() {
		panel.add(enterPhrase);
		panel.add(enterPhrase1);
		panel.add(phrase);
		panel.add(search);
	}
	
	
	public static void main(String[] args) throws IOException {
		/*MainClass mc = new MainClass();
		mc.init();
		mc.panel.add(mc); */
		MainClass mc = new MainClass();
		mc.connection.setURL("On Earth");
		mc.connection.makeConnection();
		mc.connection.createFields();
		mc.connection.read();

	}
	
}
