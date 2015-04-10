package pckg;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;
import java.util.Vector;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import api.AlchemyAPI;

import com.google.gson.Gson;
public class URLFinder implements Serializable {





	private final String USER_AGENT = "Mozilla/5.0";







	public  Vector <String> GetURLs(String queryString) throws Exception {
		try{
			Thread.sleep(11000);
			String address = "http://ajax.googleapis.com/ajax/services/search/web?v=1.0&q=";
			//System.out.println(address);
			String query = queryString;
			String charset = "UTF-8";
			

			URL url = new URL(address + URLEncoder.encode(query, charset));
			Reader reader = new InputStreamReader(url.openStream(), charset);
			GoogleResults results = null;

			System.out.println("VADAM URL-INJA ZA STRING: " + queryString);

			results = new Gson().fromJson(reader, GoogleResults.class);
			System.out.println("RESPONSE DATA: "+ results.getResponseData().toString());

			int total = results.getResponseData().getResults().size();
			System.out.println("TOTAL BRAT: " + total);

			Vector <String> URLVector= new Vector <String>();
			for(int i=0; i<=total-1; i++){
				System.out.println("Title: " + results.getResponseData().getResults().get(i).getTitle());
				URLVector.addElement(results.getResponseData().getResults().get(i).getUrl());
			}
			System.out.println("NA KRAJOT OD METODOT ZA ISTIOT");

			return URLVector;
		}
		catch(Exception e)
		{
			System.out.println("==================PADNA NA" + queryString + " PUSTAM PAK============================");
			return GetURLs(queryString);
		}
	}

	public static String sendGet( String urlQuery) throws Exception {

		String url = urlQuery;
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		// optional default is GET
		con.setRequestMethod("GET");

		//add request header
		con.setRequestProperty("User-Agent", "Mozilla/5.0");

		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + url);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(
				new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		//print result
		return response.toString();

	}


	public static File writeToFile (String str) throws IOException {

		File f = new File("nnda.xml");
		FileOutputStream fout = new FileOutputStream(f);
		byte[] bytes = str.getBytes();
		if (f.exists()==false) {
			f.createNewFile();
		}
		fout.write(bytes);
		fout.flush();
		fout.close();
		return f;
	}



	public   Vector <String> parseXML (String xml) {
		
		System.out.println(xml);

		Vector <String> wordsVector = new Vector <String>();
		try {
			//System.out.println("AJ VAKA");

			DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
			Document doc = docBuilder.parse (writeToFile(xml));
			//System.out.println("AJ SEA");
			// normalize text representation
			doc.getDocumentElement ().normalize ();
			System.out.println ("Root element of the doc is " + 
					doc.getDocumentElement().getNodeName());


			NodeList listOfPersons = doc.getElementsByTagName("keyword");
			int totalPersons = listOfPersons.getLength();
			System.out.println("Total no of keywords : " + totalPersons);

			for(int s=0; s< Math.min(listOfPersons.getLength(),5) ; s++){


				Node firstPersonNode = listOfPersons.item(s);
				if(firstPersonNode.getNodeType() == Node.ELEMENT_NODE){


					Element firstPersonElement = (Element)firstPersonNode;

					//-------
					//       NodeList firstNameList = firstPersonElement.getElementsByTagName("relevance");
					//        Element firstNameElement = (Element)firstNameList.item(0);

					//   NodeList textFNList = firstNameElement.getChildNodes();
					// System.out.println("Relevance : " + 
					//    ((Node)textFNList.item(0)).getNodeValue().trim());

					//-------
					NodeList lastNameList = firstPersonElement.getElementsByTagName("text");
					Element lastNameElement = (Element)lastNameList.item(0);

					NodeList textLNList = lastNameElement.getChildNodes();
					wordsVector.addElement(
							((Node)textLNList.item(0)).getNodeValue().trim());
					//  System.out.println(((Node)textLNList.item(0)).getNodeValue().trim());

					//----


					//------


				}//end of if clause


			}//end of for loop with s var


		}catch (SAXParseException err) {
			System.out.println ("** Parsing error" + ", line " 
					+ err.getLineNumber () + ", uri " + err.getSystemId ());
			System.out.println(" " + err.getMessage ());

		}catch (SAXException e) {
			Exception x = e.getException ();
			((x == null) ? e : x).printStackTrace ();

		}catch (Throwable t) {
			t.printStackTrace ();
		}
		//System.exit (0);
		return wordsVector;
	}//end of main








}
