package dk.ninjabear.rssscanner.model;

/**
 * @author asbjoern1982@gmail.com
 * @see http://www.vogella.com/tutorials/RSSFeed/article.html
 */
public class Message {
	private String title;
	private String description;
	private String link;
	private String author;
	private String guid;
    
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

	@Override
	public String toString() {return title;}
}