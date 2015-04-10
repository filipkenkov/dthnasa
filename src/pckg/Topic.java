package pckg;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.Serializable;
import java.util.Map;
import java.util.TreeMap;
import java.util.Vector;

public class Topic implements Serializable{

	 public String TopicName;
	 Vector <String> TopicAPIURLs;
	 public Vector < String > Keywords;
	 Vector <String> KeyWordsXML;
	 Vector <String> FinalKeywords;
	 public TreeMap <String, Integer> clickCounter;
	 URLFinder finder;
	
	public Topic (String x,int ik) throws Exception {
		System.out.println("GO GRADAM  " + ik );
		
		finder = new URLFinder();
		TopicName = new String(x);
		clickCounter = new TreeMap<String, Integer>();
		TopicAPIURLs = new Vector <String> ();
		
		//TopicAPIURLs=finder.GetURLs(x);
		Vector <String> Vtemp = finder.GetURLs(x);
		for (String k : Vtemp ) {
			StringBuilder sb=new StringBuilder();
			sb.append("http://access.alchemyapi.com/calls/url/URLGetRankedKeywords?apikey=6e11ce1bf1140d187441821a1a54d7f3ab0e1e4d&url=");
			sb.append(k);
			TopicAPIURLs.addElement(new String(sb));
		}
		for (int k=0;k<Math.min(TopicAPIURLs.size(),5);k++) {
			System.out.println(k);
		}  
		KeyWordsXML= new Vector <String> ();
		for (int k=0;k<Math.min(TopicAPIURLs.size(),5);k++) {
			KeyWordsXML.addElement(finder.sendGet(TopicAPIURLs.elementAt(k)));
		}
		System.out.println("ZAVRSIV SO APITO");
		Keywords = new Vector <String> ();
		for (int i=0;i<KeyWordsXML.size();i++) {
			Vector <String> vtemp = finder.parseXML(KeyWordsXML.elementAt(i));
			for (int k=0;k<Math.min(vtemp.size(), 20);k++ ) {
				//Keywords.addElement(vtemp.elementAt(k));
				Keywords.addElement(vtemp.elementAt(k));
			
			}
			
			
			System.out.println("AJDE KEYWORDS");
			for (String k : Keywords) {
				System.out.println(k);
				clickCounter.put(k, 0);
				
				
			}
			
	}
	}
			
		
	

		
		
		
		
	
	
	/*public static void main(String[] args) throws Exception {
		Topic t = new Topic("MODIS derived Sea Surface Temperature (SST) datasets");
		
	} */
	
}
