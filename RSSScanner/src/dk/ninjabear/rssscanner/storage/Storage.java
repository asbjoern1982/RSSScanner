package dk.ninjabear.rssscanner.storage;

import java.util.ArrayList;
import java.util.List;

public class Storage {
	private static final List<String> urls = new ArrayList<>();
	private static final List<String> keyWords = new ArrayList<>();
	
	public static List<String> getUrls() {return new ArrayList<String>(urls);}
	public static List<String> getKeyWords() {return new ArrayList<String>(keyWords);}
	
	public static void initStorage() {
		// TODO read from file
	}
	
	public static void storeUrl(String url) {
		urls.add(url);
		// TODO write urls to file
	}
	
	public static void removeUrl(String url) {
		urls.remove(url);
		// TODO write urls to file
	}
	
	public static void storeKeyWord(String keyWord) {
		keyWords.add(keyWord);
		// TODO write keyWords to file
	}
	
	public static void removeKeyWord(String keyWord) {
		keyWords.remove(keyWord);
		// TODO write keyWords to file
	}
}
