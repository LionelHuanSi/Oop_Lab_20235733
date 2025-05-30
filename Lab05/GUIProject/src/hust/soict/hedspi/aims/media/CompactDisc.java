package hust.soict.hedspi.aims.media;
import java.util.ArrayList;

import hust.soict.hedspi.aims.exception.PlayerException;

public class CompactDisc extends Disc implements Playable {
	private String artist;
	private ArrayList<Track> tracks = new ArrayList<Track>();
	private static int nbCompactDiscs = 0; 
	public CompactDisc() {
		super();
		this.tracks = new ArrayList<>();
	}
	public CompactDisc(String title, String category, float cost, String director, int length, String artist) {
        super(++nbCompactDiscs, title, category, cost, length, director); 
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
	public static int getNbCompactDiscs() {
        return nbCompactDiscs;
    }
	@Override
    public int getLength() {
        int totalLength = 0;
        for (Track track : tracks) {
            totalLength += track.getLength();
        }
        return totalLength;
    }

	@Override
	public void play() throws PlayerException {
	    if (this.getLength() > 0) {
	        System.out.println("Playing CD: " + this.getTitle());
	        for (Track track : tracks) {
	            try {
	                track.play();
	            } catch (PlayerException e) {
	                System.err.println(e.getMessage());
	                throw e;
	            }
	        }
	    } else {
	        throw new PlayerException("ERROR: CD length is non-positive!");
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
