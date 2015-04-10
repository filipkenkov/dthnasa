package game;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.TreeSet;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import pckg.Topic;

public class BeginningList {

	JList list;
	JFrame frame;
	JPanel panel;
	JLabel label;
	JButton button;
	JButton dispbutton;
	Dimension d;
	JScrollPane scpane;
	String kx;
	Vector <String> vkx;
	TopicList tIn;
	BeginningList(Dimension dk, final TopicList t) {
		d=dk;
		frame = new JFrame();
		frame.setSize(d);
		Vector <String> stri=new Vector <String>();
		for (String k : t.EntryVector) {
			stri.add(k);
		} 
		list = new JList<String>(stri);
	
	    MouseListener ml = new MouseAdapter (){
	    	
	    	public void mouseClicked (MouseEvent e) {
	    		kx = list.getSelectedValue().toString();
	    		System.out.println(kx);
	    	}
	    };
	    list.addMouseListener(ml);
		button=new JButton("START!");
		button.setLocation(5,100);
		button.setSize(100,50);
		tIn = t;
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				
				// TODO Auto-generated method stub
				Igra game;
				try {
					Object topicA = tIn.Topics.get(kx);
//					Topic topic;// = new Topic("",2);
//					if (topicA instanceof Vector) {
//						Vector v =  (Vector) topicA;
//						int p=1;
//						p+=1;
//					} else if (topicA instanceof Topic) {
//						topic = (Topic) topicA;
//					}
					
					 game=new Igra((Topic)topicA);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			 
		});
		//scpane.setViewportView(list);
		dispbutton = new JButton ("SHOW");
		dispbutton.setLocation(700,500);
		dispbutton.setSize(100,50);
		dispbutton.addActionListener(new ActionListener () {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				JFrame jf = new JFrame();
				JPanel jp = new JPanel(null);
				jf.setSize(d);
				jf.setVisible(true);
				
				int xk=5;
				for (String xs : t.Topics.get(kx).Keywords) {
					JLabel jl1 = new JLabel(xs);
					JLabel jl2 = new JLabel(t.Topics.get(kx).clickCounter.get(xs).toString());
					jp.add(jl1); jp.add(jl2);
					jl1.setLocation(5, xk);
					jl2.setLocation(5,xk+90);
					xk++;
				}
				// TODO Auto-generated method stub
				
			}
			
		});
		panel.add(dispbutton);
		panel = new JPanel(null);
		label = new JLabel("Please select one of these topics: ");
		label.setLocation(5, 5);
		label.setSize(200,50);
		panel.add(label);	
		panel.add(button);
		scpane=new JScrollPane(list);
		scpane.createVerticalScrollBar();
		scpane.setLocation(5, 200);
		scpane.setSize(600, 400);
		
		
		//scpane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		panel.add(scpane);
		//panel.setLayout(null);
		frame.add( panel );
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		
	}
	
	public static void main(String[] args) throws Exception {
		File f = new File("fajl.txt");
		TopicList t;
		Boolean b;
		if (f.exists()) b=true;
		else b=false;
		if (b==true) {
			FileInputStream fis = new FileInputStream(f);
			ObjectInputStream ois= new ObjectInputStream(fis);
			Object obj = ois.readObject();
			t=(TopicList)obj;
		}
		else {
			t= new TopicList();
			FileOutputStream ff = new FileOutputStream(f);	
			ObjectOutputStream oo=new ObjectOutputStream(ff);
			oo.writeObject(t);
		}
		BeginningList bl;
		if(t!=null)
		{
			
			bl= new BeginningList(new Dimension(Toolkit.getDefaultToolkit().getScreenSize()), t);
		}
			
		
	}
	
}
