import java.io.File;
import java.net.URL;
import java.util.Scanner;

public class Scraper {

    public String temp;

    public Scraper() {
    }

    public static void main(String[] args) throws Exception {
        Scraper scraper = new Scraper();
        scraper.getURL();
        scraper.parse(scraper.temp);
    }

    public void getURL() throws Exception {

        System.out.println("Hello World!");

        //Instantiate a URL

        URL webAddress = new URL(
                "https://www.pickerington.k12.oh.us/pickerington-central-high/directory/049048051054/");

        //System.out.println("URL: " + webAddress);

        //System.out.println("Content : " + webAddress.getContent());

        File exampleHTML = new File(
                "E:\\Users\\knwg2\\Documents\\_SchoolDocuments\\OsuCseWsTemplate\\nonOSU\\Scraper\\Scraper\\src\\exampleStuff.html");

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

    public void parse(String t) {
        String[] splits = t.split(" |\\(|\\)|\\:|\\;|\\<|\\>");
        for (String s : splits) {
            for (int i = 0; i < s.length(); i++) {
                if (s.substring(i, i + 1).equals("@")) {
                    System.out.println(s);
                }
            }
        }
    }

}
