import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class Scraper {

    private String temp;
    private String[] Exts = new String[] { ".com", ".org", ".gov", ".us",
            ".ca" };//add more if ya feel like it
    private File file = new File("OUTPUT.txt");
    FileOutputStream out;

    public Scraper() {
        try {
            this.out = new FileOutputStream(this.file);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        Scraper scraper = new Scraper();
        scraper.getURL();
        scraper.parse(scraper.temp);
    }

    /**
     * Prompts for a user input URL. Takes this url and scans the contents of
     * its web page and returns those contents.
     *
     * @throws Exception
     */
    public void getURL() throws Exception {
        //Instantiate a URL
        URL webAddress = new URL(JOptionPane.showInputDialog("Enter url"));
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
        System.out.println("Contents: " + result);
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
        String[] splits = t.split(" |\\(|\\)|\\:|\\;|\\<|\\>|\\\\");
        for (String s : splits) {
            /*
             * for (int i = 0; i < s.length(); i++) { if (s.substring(i, i +
             * 1).equals("@")) { System.out.println(s); } }
             */
            boolean hasAt = false;
            boolean hasExt = false;
            if (s.contains("@")) {
                hasAt = true;
            }
            for (String e : this.Exts) {
                if (s.contains(e)) {
                    hasExt = true;
                    break;
                }
            }
            if (hasAt && hasExt) {//if email
                System.out.println(s);
                try {
                    this.out.write((s + "\n").getBytes());
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            } else if (hasExt) {//if url
//        		System.out.println(s);
            }
        }
    }

}
