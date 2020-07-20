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
        URL webAddress = new URL("https://www.youtube.com");

//        System.out.println("URL: " + webAddress);

//        System.out.println("Content : " + webAddress.getContent());

        Scanner scanner = new Scanner(webAddress.openStream());

        StringBuffer strBuffer = new StringBuffer();

        while (scanner.hasNext()) {
            strBuffer.append(scanner.next());
        }

        String result = strBuffer.toString();

        result = result.replaceAll("<[^>]*>", "");

//        System.out.println("Contents: " + result);

        this.temp = result;

    }

    public void parse(String t) {
        String[] splits = t.split(" ");
        for (String s : splits) {
            for (int i = 0; i < s.length(); i++) {
                if (s.substring(i, i + 1).equals("@")) {
                    System.out.println(s);
                }
            }
        }
    }

}
