package dk.ninjabear.rssscanner.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author asbjoern1982@gmail.com
 * @see http://www.vogella.com/tutorials/RSSFeed/article.html
 */
public class Feed {
	private String title;
    private String link;
    private String description;
    private String language;
    private String copyright;
    private String pubDate;
    private final List<Message> entries = new ArrayList<Message>();
    
    Feed(String title, String link, String description, String language, String copyright, String pubDate) {
    	this.title = title; this.link = link; this.description = description; this.language = language; this.copyright = copyright;
    }

    public List<Message> getMessages() {return entries;}
    public String getTitle() {return title;}
    public String getLink() {return link;}
    public String getDescription() {return description;}
    public String getLanguage() {return language;}
    public String getCopyright() {return copyright;}
    public String getPubDate() {return pubDate;}
    
    @Override
    public String toString() {
    	return "Feed [opyright=" + copyright + ", description=" + description + ", language=" + language + ", link=" + link + ", pubDate=" + pubDate + ", title=" + title + "]";
    }
}
