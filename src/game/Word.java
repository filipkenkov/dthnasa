package game;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Random;
import java.util.Vector;

import javax.swing.JPanel;

import pckg.Topic;


public class Word extends JPanel {

	Igra igra;
	Random rand = new Random();
	int x,y;
	int speed;
	String value;
	int value1;
	int index;
	public Word (Igra igra,int i) throws IOException {
		
		  for (String kx : igra.selectedTopic.Keywords) {
		    	System.out.println(kx);
		    }
		  this.igra=igra;
		this.index=i;	
		this.speed=0;
		while (this.speed<=igra.speedIndex-4) this.speed=setSpeed(igra.speedIndex);
		
		this.y=0;
		this.value1=setValue();
		System.out.println("SIGUREN SI ?");
		this.value=igra.selectedTopic.Keywords.elementAt(this.value1);
		while (this.value.length()>25)
		{
			this.value1=setValue();
			this.value=igra.selectedTopic.Keywords.elementAt(this.value1);
			
		}
		System.out.println(this.value);
		this.x=setX();
		while ((igra.dobar(this,i)==false)) this.x=setX();
		this.igra=igra;
	
	}
	
	public int setX ()
	{
		return rand.nextInt(igra.d.width-200);
	}
	public int setSpeed(int k)
	{
		return rand.nextInt(k);
	}
	public int setValue()
	{
		//System.out.println("AJDE BE BRAT");
		System.out.println(igra.selectedTopic.Keywords.size());
		return rand.nextInt(igra.selectedTopic.Keywords.size()-1);
	}
	public void paint(Graphics g)
	{
		
		String s=this.value;
		g.setColor(Color.ORANGE);
		g.drawString(s, this.x, this.y);
		
	}

		
	public void update()
	{
		if (this.y>=igra.d.height)
		{
			
			
			this.y=50;
			this.value1=setValue();
			this.value=igra.selectedTopic.Keywords.elementAt(this.value1);
			while (this.value.length()>25)
			{
				this.value1=setValue();
				this.value=igra.selectedTopic.Keywords.elementAt(this.value1);
				
			}
			System.out.println(this.value);
			this.x=setX();
			this.speed=0;
			while (this.speed<=igra.speedIndex-3) this.speed=setSpeed(igra.speedIndex);
			while (igra.dobar(this,7)==false) this.x=setX();
		}
		this.y+=this.speed;
	}

	
}
