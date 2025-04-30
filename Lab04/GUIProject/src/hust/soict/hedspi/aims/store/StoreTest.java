package hust.soict.hedspi.aims.store;
import hust.soict.hedspi.aims.media.DigitalVideoDisc;

public class StoreTest {
	public static void main(String[] args) {
		Store store = new Store();
		
		DigitalVideoDisc dvd1 = new DigitalVideoDisc("The Lion King", "Animation", "Roger Allers", 87, 19.95f);
		DigitalVideoDisc dvd2 = new DigitalVideoDisc("Star Wars", "Science Fiction", "George Lucas", 82, 24.95f);
		DigitalVideoDisc dvd3 = new DigitalVideoDisc("Aladin", "Animation", 18.99f);
		
		System.out.println("Add DVDs to the store:");
		store.addDVD(dvd1);
		store.addDVD(dvd2);
		store.addDVD(dvd3);
		
		System.out.println("\nCurrent DVDs in Store:");
		store.printStore();
		
		System.out.println("Remove DVDs from Store.");
		store.removeDVD(dvd2);
		
		System.out.println("\nCurrent DVDs in Store after remove:");
		store.printStore();
		
		System.out.println("\nWhen remove DVD not in Store:");
		store.removeDVD(dvd2);
	}
}
