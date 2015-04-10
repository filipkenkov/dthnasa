package game;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;

import pckg.Topic;
import pckg.URLFinder;


public class Igra extends JPanel implements MouseListener {

	Topic selectedTopic;
	FontMetrics fm;
	int poeni;
	JButton quitButton;
	Font font=new Font("Calibri",Font.BOLD,20);
	public int speedIndex;
	Word[] words = new Word[7];
	Timer timer;
	int points;
	static JFrame frame = new JFrame();
	static Dimension d =Toolkit.getDefaultToolkit().getScreenSize();
	public Igra (Topic topikoina) throws Exception
	{
			
			quitButton = new JButton("STOP");
			quitButton.setSize(100, 50);
			quitButton.setLocation(5,5);
			this.add(quitButton);
			quitButton.addActionListener(new ActionListener () {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					timer.stop();
					frame.setVisible(false);
					frame.dispose();
					
				}
				
			});
		
			this.add(quitButton);
		    selectedTopic = topikoina;
		    System.out.println(selectedTopic.TopicName);
			System.out.println("NDASIZE: " + selectedTopic.Keywords.size());
		    this.speedIndex=5;
		    poeni=0;
		    fm=getFontMetrics(font);
		    frame.setSize(d);
		    frame.add(this);
		    frame.setVisible(true);
		    
		    this.addMouseListener(this);
		    timer=new Timer(30,new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					//System.out.println("LALAJ");
					update();
					repaint();
				}

		    });
		 //   System.out.println("AJVAN");
		    for (int i=0;i<7;i++) {
		    	words[i]=new Word(this,i);
		    }
		  //  System.out.println("PRED TIMER START ");
		    timer.start();
	}
	
	boolean dobar (Word x,int k) {
		
		int d,g;
		if (k==0) return true;
		for (int i=0;i<k;i++) {
	
			if (words[i].y>this.d.height/3) continue;
			if (i==x.index) continue;
			if (words[i].x-fm.stringWidth(x.value)>0) d=words[i].x-fm.stringWidth(x.value);
			else d=0;
			if (words[i].x+fm.stringWidth(words[i].value)<this.d.width) g=words[i].x+fm.stringWidth(words[i].value);
			else g=this.d.width;
		
				if (x.x>=d&&x.x<=g) return false;
		}
		return true;
	}
	
	public void update ()
	{
		for (int i=0;i<7;i++) {
			
			words[i].update(); 
		}
		
	}

	public void paint (Graphics g)
	{
		g.setFont(font);
		g.setColor(new Color(8,48,135));
		g.fillRect(0, 0, d.width, d.height);
		g.setColor(Color.ORANGE);
		
		
		
		for (int i=0;i<7;i++) words[i].paint(g);
	
	}
/*	public static void main (String args[]) throws Exception
	{
		Igra game = new Igra();
		
		game.addMouseListener(game);
		frame.add(game);
		frame.setSize(d.width,d.height);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//tekst.setBounds(520,420 , 300, 300);
	} */
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		int mx=e.getX();
		int my=e.getY();
	/*	if (mx>=d.width-20&&my<=5&&windowVisible==true) {
			infoFrame.setVisible(true);
		} */
		for (int i=0;i<7;i++) {
			
			if (mx>=words[i].x&&mx<=words[i].x+fm.stringWidth(words[i].value)&&my<=words[i].y&&my>=words[i].y+fm.getHeight()) {
				
				words[i].y=d.height+200;
				 
					if (poeni%350==0&&this.speedIndex<=7) this.speedIndex+=1;
					 
				
				
			}
		}
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		int mx=e.getX();
		int my=e.getY();
        
		
	//	System.out.println("KLIKNAV NA: " + mx + " " + my);
		//System.out.println();
		for (int i=0;i<7;i++) {
			
			if (mx>=words[i].x&&mx<=words[i].x+fm.stringWidth(words[i].value)&&my<=words[i].y&&my>=words[i].y-fm.getHeight()) {
			//	System.out.println("POGODIV");
				//System.out.println();
				
				Integer k = selectedTopic.clickCounter.get(words[i].value);
				selectedTopic.clickCounter.put(words[i].value, k+1);
				words[i].y=d.height+200;
				
					this.poeni+=50;
					
					
					if (poeni%350==0&&this.speedIndex<=8) this.speedIndex+=1;
			}
					
					
				
				
			
		
	
	
}
	}
}
	
