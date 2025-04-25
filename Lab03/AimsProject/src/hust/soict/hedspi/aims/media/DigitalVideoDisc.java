package hust.soict.hedspi.aims.media;

import java.util.Objects;

public class DigitalVideoDisc extends Disc implements Playable {
	public DigitalVideoDisc() {
        super();
    }

    // Parameterized Constructor
    public DigitalVideoDisc(int id, String title, String category, float cost,int length,String director) {
        super(id, title, category, cost, length, director);
    }

    @Override
    public void play() {
        System.out.println("Playing DVD: " + this.getTitle());
        System.out.println("DVD length: " + this.getLength());
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
}