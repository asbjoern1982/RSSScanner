package dk.ninjabear.rssscanner.test;

import dk.ninjabear.rssscanner.model.Feed;
import dk.ninjabear.rssscanner.model.Message;
import dk.ninjabear.rssscanner.model.RSSParser;

public class Test {
	public static void main(String[] args) {
		RSSParser parser = new RSSParser("http://jp.dk/indland/?service=rssfeed");
		Feed feed = parser.readFeed();
		System.out.println(feed);
		for (Message message : feed.getMessages()) {
		    System.out.println(message);
		
		}
	}
}
