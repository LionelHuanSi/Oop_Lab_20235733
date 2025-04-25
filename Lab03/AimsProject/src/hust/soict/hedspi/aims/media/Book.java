package hust.soict.hedspi.aims.media;
import java.util.ArrayList;
public class Book extends Media{
	private ArrayList<String> authors = new ArrayList<String>();
		
	public Book() {
		super();
	}
	public Book(int id, String title, String category, float cost) {
        super(id, title, category, cost);
        this.authors = new ArrayList<String>();
    }
	public void addAuthor(String authorName) {
        if (!authors.contains(authorName)) {
            authors.add(authorName);
            System.out.println("Author \"" + authorName + "\" has been added.");
        } else {
            System.out.println("Author \"" + authorName + "\" already exists.");
        }
    } 
    public void removeAuthor(String authorName) {
        if (authors.contains(authorName)) {
            authors.remove(authorName);
            System.out.println("Author \"" + authorName + "\" has been removed.");
        } else {
            System.out.println("Author \"" + authorName + "\" does not exist.");
        }
    }
	public ArrayList<String> getAuthors() {
        return authors;
    }
	@Override
	public String toString() {
		return "Book [authors=" + authors + "]";
	}
		
}