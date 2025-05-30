package hust.soict.hedspi.aims.media;

public class Disc extends Media implements Comparable<Media> {
	private int length;
	private String director;
	
	public Disc() {
		super();
	}
	
	public Disc(int id, String title, String category, float cost, int length, String director) {
        super(id, title, category, cost);
        this.length = length;
        this.director = director;
    }

	public int getLength() {
		return length;
	}

	public String getDirector() {
		return director;
	}
	@Override
	public int compareTo(Media other) {
	    if (other instanceof Disc) {
	        Disc disc = (Disc) other;
	        int titleCompare = this.getTitle().compareToIgnoreCase(disc.getTitle());
	        if (titleCompare != 0) return titleCompare;
	        int lengthCompare = Integer.compare(disc.getLength(), this.getLength()); // length giảm dần
	        if (lengthCompare != 0) return lengthCompare;
	        return Float.compare(this.getCost(), disc.getCost());
	    }
	    return this.getTitle().compareToIgnoreCase(other.getTitle());
	}
	@Override
	public String toString() {
		return "Disc [length=" + length + ", director=" + director + "]";
	}

}
