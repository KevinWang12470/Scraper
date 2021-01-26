import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

public class Scraper {

    private String temp;
    private File file = new File("OUTPUT.txt");

    private String webName;
    private ArrayList<String> emails = new ArrayList<String>();
//    private ArrayList<String> links = new ArrayList<String>();

    FileOutputStream out;

    public Scraper() {
        try {
            this.out = new FileOutputStream(this.file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        Scraper scraper = new Scraper();
        scraper.getURL();
        scraper.parse(scraper.temp);
        scraper.printToFile();
    }

    /**
     * Prompts for a user input URL. Takes this url and scans the contents of
     * its web page and returns those contents.
     *
     * @throws Exception
     */
    public void getURL() throws Exception {
        //Instantiate a URL
    	webName = JOptionPane.showInputDialog("Enter url");
        URL webAddress = new URL(webName);
//        System.out.println("URL: " + webAddress);
//        System.out.println("Content : " + webAddress.getContent());

        //System.out.println("URL: " + webAddress);

        //System.out.println("Content : " + webAddress.getContent());


        Scanner scanner = new Scanner(webAddress.openStream());
        StringBuffer strBuffer = new StringBuffer();
        while (scanner.hasNext()) {
            strBuffer.append(scanner.next() + " ");
        }
        String result = strBuffer.toString();
//        result = result.replaceAll("<[^>]*>", "");
//        System.out.println("Contents: " + result);
        this.temp = result;
        scanner.close();

    }

    /**
     * Takes the contents of the user input webpage and parses it for valid
     * emails.
     * 
     * @param t
     *            A string containing the contents of the web page being scraped
     */
    public void parse(String t) {
    	
    	String eRegex = "(^[\\w!#$%&’*+/=?`{|}~^-]+(?:\\.[\\w!#$%&’*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$)";
    	Pattern ePattern = Pattern.compile(eRegex);
//    	String lRegex = "((http|https)://)(www.)?"
//                + "[a-zA-Z0-9@:%._\\+~#?&//=]"
//                + "{2,256}\\.[a-z]"
//                + "{2,6}\\b([-a-zA-Z0-9@:%"
//                + "._\\+~#?&//=]*)";
//    	Pattern lPattern = Pattern.compile(lRegex);
    	
    	String[] splits = t.split(" |\\(|\\)|\\:|\\;|\\<|\\>|\\\\");
    	
    	for (String s : splits) {
    		Matcher eMatch = ePattern.matcher(s);
    		if (eMatch.matches()) {
    			emails.add(s);
    		}
//    		Matcher lMatch = lPattern.matcher(s);
//    		if (lMatch.matches()) {
//    			links.add(s);
//    		}
    	}
    	
    }
    
    public void printToFile() {
    	try {
			out.write(("emails found in " + webName + ":\n").getBytes());
			for (String s : emails) out.write((s + "\n").getBytes());
//			out.write(("links found in " + webName + ":\n").getBytes());
//			for (String s : links) out.write((s + "\n").getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}

    }

}
