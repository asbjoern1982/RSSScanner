package dk.ninjabear.rssscanner.service;

import java.util.ArrayList;
import java.util.List;

import dk.ninjabear.rssscanner.model.Feed;
import dk.ninjabear.rssscanner.model.Message;
import dk.ninjabear.rssscanner.model.RSSParser;
import dk.ninjabear.rssscanner.storage.Storage;

public class Service {
	public static List<Message> searchFeeds() {
		List<String> urls = Storage.getUrls();
		List<String> keyWords = Storage.getKeyWords();
		List<Message> messages = new ArrayList<>();
		System.out.println("starting");
		for (String url : urls) {
			RSSParser parser = new RSSParser(url);
			Feed feed = parser.readFeed();
			
			for (Message message : feed.getMessages()) {
			    String title = message.getTitle();
			    String description = message.getDescription();
			
			    for (String keyWord : keyWords) {
			    	if (title.contains(keyWord) || description.contains(keyWord))
			    		messages.add(message);
			    }
			}
		}
		System.out.println("finised");
		return messages;
	}
	
	public static List<String> getKeyWords() {return Storage.getKeyWords();}
}
