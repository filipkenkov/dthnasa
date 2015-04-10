package game;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import pckg.Topic;




public   class TopicList implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	TreeSet <String> EntryVector;
	
	//Set <String> EntrySet;
	
	public void FillEntryVector( ) throws FileNotFoundException , IOException {
		
		FileInputStream f = new FileInputStream(new File("C:\\pece.xlsx"));
        
        //Get the workbook instance for XLS file 
        XSSFWorkbook workbook = new XSSFWorkbook(f);

        //Get first sheet from the workbook
        XSSFSheet sheet = workbook.getSheet("metadata");
         
        //Iterate through each rows from first sheet
       Row rc = sheet.getRow(0);
       int key = 0;
       int k=rc.getLastCellNum();
       for (int i=0;i<=k;i++) {
    	   Cell c =rc.getCell(i);
    	   System.out.println(c.getStringCellValue());
    	   if (c.getStringCellValue().equals("english_title")) {
    		//   System.out.println("VLEGOV VO IFOT. SHO CHE JAISH OD TAMU MORE");
    		   key=i;
    		   break;
    	   }
       }
        
        boolean flag = false;
         for (Row r : sheet) {
        	 if (flag==false) flag=true;
        	 else { 
            		 Cell c = r.getCell(key);
            		 EntryVector.add((c.toString()));
            	//	 System.out.println(c.toString());
            		 
        	 }
        	 
        		 
        	 }
        
         } 
                 
                 

	public Map<String, Topic> Topics;
	public TopicList () throws Exception {
		
		Topics = new HashMap<String,Topic > ();
		EntryVector = new TreeSet <String> ();
		FillEntryVector();
		int i=1;
		
		for (String k : EntryVector ) {
			Topic tp = new Topic(k,i);
			Topics.put(tp.TopicName,tp);
			i++;
		}
		
		
		
		
	}
	/*public static void main(String[] args) throws Exception {
		TopicList tl= new TopicList ();
		for (Topic k : tl.Topics ) {
			if (k.TopicName=="On Earth") {
				for ( String kx : k.Keywords) {
					System.out.println(kx);
				}
			}
		}
			//Topic tpc = new Topic (k);
		//	tl.Topics.addElement(tpc);
			
			
		} */
		
	} 

