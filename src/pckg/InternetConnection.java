package pckg;


import java.io.IOException;
import java.net.*;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class InternetConnection {

	URL path;
	Map < String, List<String> > headerFields;
	Set <String> headerFieldsSet;
	HttpURLConnection conn;
	
	InternetConnection () {
		
	}
	
	public void setURL (String s) throws MalformedURLException {
		StringBuilder str=new StringBuilder();
		str.append("https://www.google.com/?gws_rd=ssl#q=");
		
		
		StringBuilder str1= new StringBuilder(s);
		for (int i=0;i<str1.length();i++) {
			if (str1.charAt(i)==' ') {
				str1.setCharAt(i,'+');
			}
		}
		str.append(str1.toString());
		path = new URL(str.toString());
		System.out.println(str);
	}
	public void makeConnection () throws IOException {
		conn = (HttpURLConnection) path.openConnection();
		
	}
	
	public void createFields () {
		headerFields = conn.getHeaderFields();
		headerFieldsSet=headerFields.keySet();
	}
	
	public void read () {
		for (String k : headerFieldsSet) {
			System.out.println("Key: " + k);
			System.out.println("Value: " + headerFields.get(k));
		}
	}
}
