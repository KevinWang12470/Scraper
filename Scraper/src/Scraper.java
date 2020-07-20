import java.net.URL;
import java.util.Scanner;

public class Scraper {
    /**
     * Main method.
     *
     * @param args
     *            the command line arguments; unused here
     */
    public static void main(String[] args) throws Exception {

        System.out.println("Hello World!");

        //Instantiate a URL
        URL webAddress = new URL("https://www.youtube.com");

        System.out.println("URL: " + webAddress);

        System.out.println("Content : " + webAddress.getContent());

        Scanner scanner = new Scanner(webAddress.openStream());

        StringBuffer strBuffer = new StringBuffer();

        while (scanner.hasNext()) {
            strBuffer.append(scanner.next());
        }

        String result = strBuffer.toString();

        result = result.replaceAll("<[^>]*>", "");

        System.out.println("Contents: " + result);
    }
}
