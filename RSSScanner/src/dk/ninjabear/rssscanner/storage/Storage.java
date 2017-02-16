package dk.ninjabear.rssscanner.storage;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Storage {
	private static boolean load = true;
	private static final String FILENAME = "data.json";
	
	private static final List<String> urls = new ArrayList<>();
	private static final List<String> keyWords = new ArrayList<>();
	
	public static List<String> getUrls() {if (load) load(); return new ArrayList<String>(urls);}
	public static List<String> getKeyWords() {if (load) load(); return new ArrayList<String>(keyWords);}
	
	
	public static void storeUrl(String url) {urls.add(url); store();}
	public static void updateUrl(String oldUrl, String newUrl) {urls.set(urls.indexOf(oldUrl), newUrl); store();}
	public static void removeUrl(String url) {urls.remove(url); store();}
	
	public static void storeKeyWord(String keyWord) {keyWords.add(keyWord); store();}
	public static void removeKeyWord(String keyWord) {keyWords.remove(keyWord); store();}
	
	private static void load() {
		try {
			List<String> lines = Files.readAllLines(Paths.get(FILENAME), Charset.forName("UTF-8"));
			StringBuilder sb = new StringBuilder();
			for (String line : lines) sb.append(line);
			JSONObject obj = new JSONObject(sb.toString());
			
			JSONArray keyWordsJSON = obj.getJSONArray("keyWords");
			for (int i = 0; i < keyWordsJSON.length(); i++)
				keyWords.add(keyWordsJSON.getString(i));

			JSONArray urlsJSON = obj.getJSONArray("urls");
			for (int i = 0; i < urlsJSON.length(); i++)
				urls.add(urlsJSON.getString(i));
			
		} catch (FileNotFoundException e) { // do nothing
		} catch (Exception e) {System.out.println(e);}
		load = false;
	}
	
	private static void store() {
		try (BufferedWriter file = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(FILENAME), "UTF-8"))) {

			JSONArray keyWordsJSON = new JSONArray();
			for (String keyWord : keyWords)
				keyWordsJSON.put(keyWord);
			
			JSONArray urlsJSON= new JSONArray();
			for (String url : urls)
				urlsJSON.put(url);
			
			JSONObject obj = new JSONObject();
			obj.put("keyWords", keyWordsJSON);
			obj.put("urls", urlsJSON);
			obj.write(file);
		} catch (JSONException e) {System.err.println(e);} catch (IOException e) {System.err.println(e);}
	}
}
