package hust.soict.hedspi.aims.store;
import java.util.ArrayList;
import java.util.List;

import hust.soict.hedspi.aims.media.Media;
public class Store {
	private ArrayList<Media> itemsInStore = new ArrayList<Media>();
	public void addMedia(Media media) {
		if(itemsInStore.contains(media)) {
			System.out.println("Media \"" + media.getTitle() + "\" is already exists in the store.");
		}else {
			itemsInStore.add(media);
			System.out.println("Media \"" + media.getTitle() + "\" has been added to the store.");
		}
	}
	public void removeMedia(Media media) {
		if(itemsInStore.contains(media)) {
			itemsInStore.remove(media);
			System.out.println("Media \"" + media.getTitle() + "\" has been removed from the store.");
		}else {
			System.out.println("Media \"" + media.getTitle() + "\" was not found in the store.");
		}
	}
	
	public Media findMediaByTitle(String title) {
        for (Media media : itemsInStore) {
            if (media.getTitle().equalsIgnoreCase(title)) {
                return media;
            }
        }
        System.out.println("No media found with the title: " + title);
        return null;
    }
	public List<Media> getMediaList() {
        return new ArrayList<>(itemsInStore);  // Return a copy of the list
    }
	
}
