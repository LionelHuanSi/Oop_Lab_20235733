package hust.soict.hedspi.aims.media;

import hust.soict.hedspi.aims.exception.PlayerException;

public class Track implements Playable{
	private int length;
	private String title;
	
	public Track() {
		super();
	}
	public Track(int length, String title) {
		this.length = length;
		this.title = title;
	}
	@Override
	public void play() throws PlayerException {
	    if (this.getLength() > 0) {
	        System.out.println("Playing track: " + this.getTitle());
	        System.out.println("Track length: " + this.getLength());
	    } else {
	        throw new PlayerException("ERROR: Track length is non-positive!");
	    }
	}

	@Override
	public boolean equals(Object o) {
	    if (this == o) return true; 
	    if (!(o instanceof Track)) return false;
	    Track other = (Track) o; 
	    return this.title.equals(other.title) && this.length == other.length;
	}

	public int getLength() {
		return length;
	}
	public String getTitle() {
		return title;
	}
	public void setLength(int length) {
		this.length = length;
	}
	public void setTitle(String title) {
		this.title = title;
	}
}
