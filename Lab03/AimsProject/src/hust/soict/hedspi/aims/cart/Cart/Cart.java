package hust.soict.hedspi.aims.cart.Cart;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

import hust.soict.hedspi.aims.media.DigitalVideoDisc;
import hust.soict.hedspi.aims.media.Media;
import hust.soict.hedspi.aims.media.MediaComparatorByCostTitle;
import hust.soict.hedspi.aims.media.MediaComparatorByTitleCost;
import hust.soict.hedspi.aims.media.Playable;

public class Cart {
	private ArrayList<Media> itemsOrdered = new ArrayList<Media>();
	 
	public void addMedia(Media media) {
		if(itemsOrdered.contains(media)) {
			System.out.println("Media is already in the cart.");
		} else {
			itemsOrdered.add(media);
			System.out.println("Media \"" + media.getTitle() + "\" added to the cart.");
		}
	}
	public void removeMedia(Media media) {
		if(itemsOrdered.contains(media)) {
			itemsOrdered.remove(media);
			System.out.println("Media \"" + media.getTitle() + "\" has been removed from the cart.");
		} else {
			System.out.println("Media \"" + media.getTitle() + "\" was not found in the cart.");
		}
	}
	
	public float totalCost() {
		float total = 0;
		for(Media media : itemsOrdered) {
			total += media.getCost();
		}
		return total;
	}
	public void filterMediaInCart(Scanner scanner) {
        System.out.println("Filter by (1) ID or (2) Title:");
        int filterChoice = scanner.nextInt();
        scanner.nextLine();  
        switch (filterChoice) {
            case 1:
                System.out.println("Enter the ID of the media:");
                int id = scanner.nextInt();
                scanner.nextLine();  
                boolean found = false;
                for (Media media : itemsOrdered) {
                    if (media.getId() == id) {
                        System.out.println(media);
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    System.out.println("No media found with ID: " + id);
                }
                break;
            case 2:
                System.out.println("Enter the title of the media:");
                String title = scanner.nextLine();
                found = false;
                for (Media media : itemsOrdered) {
                    if (media.getTitle().equalsIgnoreCase(title)) {
                        System.out.println(media);
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    System.out.println("No media found with title: " + title);
                }
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
                break;
        }
    }
    
    public void sortMediaInCart(Scanner scanner) {
        System.out.println("Sort by (1) Title or (2) Cost:");
        int sortChoice = scanner.nextInt();
        scanner.nextLine(); 
        switch (sortChoice) {
            case 1:
            	itemsOrdered.sort(Comparator.comparing(Media::getTitle));
                break;
            case 2:
            	itemsOrdered.sort(Comparator.comparingDouble(Media::getCost));
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
                return;
        }
        System.out.println("Sorted media in cart:");
        for (Media media : itemsOrdered) {
            System.out.println(media);
        }
    }
    
    public void playMediaInCart(Scanner scanner) {
        if (itemsOrdered.isEmpty()) {
            System.out.println("The cart is currently empty.");
            return;
        }

        System.out.println("Enter the title of the media to play:");
        String title = scanner.nextLine();
        Media foundMedia = itemsOrdered.stream()
                                       .filter(media -> media.getTitle().equalsIgnoreCase(title))
                                       .findFirst()
                                       .orElse(null);

        if (foundMedia != null && foundMedia instanceof Playable) {
            ((Playable) foundMedia).play();
        } else if (foundMedia != null) {
            System.out.println("This media cannot be played.");
        } else {
            System.out.println("Media not found in the cart.");
        }
    }

    
    public void emptyCart() {
    	itemsOrdered.clear();
        System.out.println("The cart has been emptied.");
    }
    
    public void removeMediaByTitle(String title) {
        Media mediaToRemove = null;
        for (Media media : itemsOrdered) {
            if (media.getTitle().equalsIgnoreCase(title)) {
                mediaToRemove = media;
                break;
            }
        }
        if (mediaToRemove != null) {
        	itemsOrdered.remove(mediaToRemove);
            System.out.println(title + " has been removed from the cart.");
        } else {
            System.out.println(title + " not found in the cart.");
        }
    }
    // Method to display all medias in the cart
    public void displayCart() {
        if (itemsOrdered.isEmpty()) {
            System.out.println("The cart is empty.");
        } else {
            System.out.println("Items in the cart:");
            for (Media media : itemsOrdered) {
                System.out.println(media.toString());
            }
        }
    }

    // Method to count the number of DVDs in the cart
    public int countDVDs() {
        int dvdCount = 0;
        for (Media media : itemsOrdered) {
            if (media instanceof DigitalVideoDisc) {
                dvdCount++;
            }
        }
        return dvdCount;
    }

    // Method to sort medias by title (alphabetical order, then by cost descending)
    public void sortByTitle() {
        itemsOrdered.sort(new MediaComparatorByTitleCost());
        System.out.println("Medias sorted by title and cost.");
    }

    // Method to sort medias by cost (descending order, then by title alphabetical)
    public void sortByCost() {
        itemsOrdered.sort(new MediaComparatorByCostTitle());
        System.out.println("Medias sorted by cost and title.");
    }

    // Method to find a media by title
    public Media findMediaByTitle(String title) {
        for (Media media : itemsOrdered) {
            if (media.getTitle().equalsIgnoreCase(title)) {
                return media;
            }
        }
        System.out.println("No media found with title: " + title);
        return null;
    }
    
    public void clearCart() {
    	itemsOrdered.clear();
        System.out.println("The cart has been cleared.");
    }
    
    public boolean isEmpty() {
        return itemsOrdered.isEmpty();
    }

    public List<Media> getMediaList() {
        return new ArrayList<>(itemsOrdered);
    }
    
}
