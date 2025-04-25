package hust.soict.hedspi.aims.media;
import java.util.ArrayList;

public class CompactDisc extends Disc implements Playable {
	private String artist;
	private ArrayList<Track> tracks = new ArrayList<Track>();
	
	public CompactDisc() {
		super();
		this.tracks = new ArrayList<>();
	}
	public CompactDisc(int id, String title, String category, float cost, int length, String director, String artist) {
		super(id, title, category, cost, length, director);
		this.artist = artist;
	}
	public void addTrack(Track track) {
		if(tracks.contains(track)) {
			System.out.println("Track \"" + track.getTitle()+ "\"" + " already exists.");
		}else {
			tracks.add(track);
			System.out.println("Track \"" + track.getTitle() + "\"" + "has been added");
		}
	}
	
	public void removeTrack(Track track) {
        if (tracks.contains(track)) {
            tracks.remove(track);
            System.out.println("Track \"" + track.getTitle() + "\" has been removed.");
        } else {
            System.out.println("Track \"" + track.getTitle() + "\" does not exist.");
        }
    } 
	public int getLength() {
		int totallength = 0;
		for(Track track : tracks) {
			totallength += track.getLength();
		}
		return totallength;
	}
	
	@Override
	public void play() {
		System.out.println("Playing CD: " + getTitle());
		System.out.println("CD length: " + getLength());
		for(Track track: tracks) {
			track.play();
		}
	}
	public String getArtist() {
		return artist;
	}
	public ArrayList<Track> getTracks() {
		return tracks;
	}
	@Override
	public String toString() {
	    return "CompactDisc [id=" + getId()
	            + ", title=" + getTitle()
	            + ", category=" + getCategory()
	            + ", cost=" + getCost()
	            + ", length=" + getLength()
	            + ", director=" + getDirector()
	            + ", artist=" + artist
	            + ", number of tracks=" + tracks.size()
	            + "]";
	}	
}
