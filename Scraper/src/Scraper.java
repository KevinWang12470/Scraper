
public class Scraper {
	public String temp;
	public Scraper () {
	}
    public static void main(String[] args) {
    	Scraper scraper = new Scraper();
    	scraper.getURL();
        scraper.parse(scraper.temp);
    }
    
    public void getURL() {
		temp = "wow wh@t @ gre@t progr@m";
    }
    
    public void parse(String t) {
    	String[] splits = t.split(" ");
    	for (String s : splits) {
    		for (int i = 0; i < s.length(); i++) {
    			if (s.substring(i, i+1).equals("@")) {
    				System.out.println(s);
    			}
    		}
    	}
    }

}
