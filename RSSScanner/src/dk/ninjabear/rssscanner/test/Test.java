package dk.ninjabear.rssscanner.test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import dk.ninjabear.rssscanner.model.Feed;
import dk.ninjabear.rssscanner.model.Message;
import dk.ninjabear.rssscanner.model.RSSParser;

public class Test {
	public static void main(String[] args) throws Exception {
		
		String pubDate = "Sun, 29 Jan 2017 08:00:00 +0100";
		//Mon, 13 Feb 2017 07:00:00 +0100";

		
		DateFormat formatter = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss zzz");
		Calendar cal = Calendar.getInstance();
		cal.setTime(formatter.parse(pubDate));
		String date = cal.get(Calendar.DAY_OF_MONTH) + "/" + (cal.get(Calendar.MONTH) + 1);
		
		System.out.println(date);
		
		
//		RSSParser parser = new RSSParser("http://jp.dk/indland/?service=rssfeed");
//		Feed feed = parser.readFeed();
//		System.out.println(feed);
//		for (Message message : feed.getMessages()) {
//		    System.out.println(message);
//		
//		}
	}
}
