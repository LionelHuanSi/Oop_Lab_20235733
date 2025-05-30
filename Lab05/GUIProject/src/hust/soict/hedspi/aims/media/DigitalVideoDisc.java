package hust.soict.hedspi.aims.media;

import java.util.Objects;

import hust.soict.hedspi.aims.exception.PlayerException;

public class DigitalVideoDisc extends Disc implements Playable {
	public DigitalVideoDisc() {
        super();
    }
	private static int nbDigitalVideoDiscs = 0;
	public DigitalVideoDisc(String title) {
		super(++nbDigitalVideoDiscs, title, "", 0.0f, 0, "");
	}
	public DigitalVideoDisc(String title, String category, float cost) {
        super(++nbDigitalVideoDiscs, title, category, cost, 0, "");
	}
	public DigitalVideoDisc(String title, String category, String director, float cost) {
        super(++nbDigitalVideoDiscs, title, category, cost, 0, director);
	}
	public DigitalVideoDisc(String title, String category, String director, int length, float cost) {
        super(++nbDigitalVideoDiscs, title, category, cost, length, director);
	}

	public static int getNbDigitalVideoDisc() {
		return nbDigitalVideoDiscs;
	}


	@Override
	public void play() throws PlayerException {
	    if (this.getLength() > 0) {
	        System.out.println("Playing DVD: " + this.getTitle());
	        System.out.println("DVD length: " + this.getLength());
	    } else {
	        throw new PlayerException("ERROR: DVD length is non-positive!");
	    }
	}

    @Override
    public String toString() {
        if (getDirector() == null)
            return "DVD - " + getTitle() + " - " + getCategory() + " - " + getCost() + " $ ";
        return "DVD - " + getTitle() + " - " + getCategory() + " - " + getDirector() + " - "
                + getLength() + " : " + getCost() + " $ ";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;

        DigitalVideoDisc other = (DigitalVideoDisc) obj;
        return getId() == other.getId()
                && getLength() == other.getLength()
                && Float.compare(getCost(), other.getCost()) == 0
                && Objects.equals(getTitle(), other.getTitle())
                && Objects.equals(getCategory(), other.getCategory())
                && Objects.equals(getDirector(), other.getDirector());
    }
	public static int getNbDigitalVideoDiscs() {
		return nbDigitalVideoDiscs;
	}
}