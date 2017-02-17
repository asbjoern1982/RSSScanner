package dk.ninjabear.rssscanner.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author asbjoern1982@gmail.com
 * @see http://www.vogella.com/tutorials/RSSFeed/article.html
 */
public class Message implements Comparable<Message> {
	private String title;
	private String description;
	private String link;
	private String author;
	private String guid;
	private String pubDate;
    
    public String getTitle() {return title;}
	public void setTitle(String title) {this.title = title;}
	public String getDescription() {return description;}
	public void setDescription(String description) {this.description = description;}
	public String getLink() {return link;}
	public void setLink(String link) {this.link = link;}
	public String getAuthor() {return author;}
	public void setAuthor(String author) {this.author = author;}
	public String getGuid() {return guid;}
	public void setGuid(String guid) {this.guid = guid;}
	public String getPubDate() {return pubDate;}
	public void setPubDate(String pubDate) {this.pubDate = pubDate;}

	@Override
	public String toString() {
		String date;
		try {
			DateFormat formatter = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss zzz");
			Calendar cal = Calendar.getInstance();
			cal.setTime(formatter.parse(pubDate));
			date = cal.get(Calendar.DAY_OF_MONTH) + "/" + (cal.get(Calendar.MONTH) + 1);
		} catch (Exception e) {date = pubDate;}
		return date + " " + title;
	}
	@Override
	public int compareTo(Message other) {
		try {
			DateFormat formatter = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss zzz");
			Date date1 = formatter.parse(pubDate);
			Date date2 = formatter.parse(other.getPubDate());
			return date1.compareTo(date2);
		} catch (Exception e) {}
		return 0;
	}
}
