package hust.soict.hedspi.aims.Aims;

import java.util.Scanner;

import hust.soict.hedspi.aims.cart.Cart.Cart;
import hust.soict.hedspi.aims.media.Book;
import hust.soict.hedspi.aims.media.CompactDisc;
import hust.soict.hedspi.aims.media.DigitalVideoDisc;
import hust.soict.hedspi.aims.media.Media;
import hust.soict.hedspi.aims.media.Playable;
import hust.soict.hedspi.aims.media.Track;
import hust.soict.hedspi.aims.store.Store;

public class Aims {
    private static Store store = new Store();
    private static Cart cart = new Cart();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
    	// CD
    	CompactDisc cd1 = new CompactDisc("The Beatles", "Music", 10.99f, "Director", 120, "The Beatles");
    	CompactDisc cd2 = new CompactDisc("Thriller", "Music", 9.99f, "Quincy Jones", 110, "Michael Jackson");
    	CompactDisc cd3 = new CompactDisc("Kind of Blue", "Jazz", 11.49f, "Miles Davis", 55, "Miles Davis");
    	CompactDisc cd4 = new CompactDisc("The Four Seasons", "Classical", 14.49f, "Antonio Vivaldi", 80, "Vivaldi");
    	Track track1 = new Track(180, "Let It Be");
    	Track track2 = new Track(240, "Hey Jude");
    	// DVD
    	DigitalVideoDisc dvd1 = new DigitalVideoDisc("Inception", "Sci-Fi", "Christopher Nolan", 148, 14.99f);
    	DigitalVideoDisc dvd2 = new DigitalVideoDisc("Mad Max: Fury Road", "Action", "George Miller", 120, 19.99f);
    	DigitalVideoDisc dvd3 = new DigitalVideoDisc("The Shawshank Redemption", "Drama", "Frank Darabont", 142, 13.49f);
    	DigitalVideoDisc dvd4 = new DigitalVideoDisc("The Grand Budapest Hotel", "Comedy", "Wes Anderson", 99, 17.99f);

    	// Book
    	Book book1 = new Book("The Alchemist", "Novel", 12.99f);
    	Book book2 = new Book("Harry Potter and the Sorcerer's Stone", "Fantasy", 15.99f);
    	Book book3 = new Book("The Girl with the Dragon Tattoo", "Mystery", 18.49f);

        
        store.addMedia(cd1);
        cd1.addTrack(track1);
    	cd1.addTrack(track2);
        store.addMedia(cd2);
        store.addMedia(cd3);
        store.addMedia(cd4);
        store.addMedia(dvd1);
        store.addMedia(dvd2);
        store.addMedia(dvd3);
        store.addMedia(dvd4);
        store.addMedia(book1);
        store.addMedia(book2);
        store.addMedia(book3);

        while (true) {
            showMenu();
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume the newline character

            switch (choice) {
                case 1:
                    viewStore();
                    break;
                case 2:
                    updateStore();
                    break;
                case 3:
                    seeCurrentCart(scanner);
                    break;
                case 0:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        }
    }

    public static void showMenu() {
        System.out.println("AIMS: ");
        System.out.println("--------------------------------");
        System.out.println("1. View store");
        System.out.println("2. Update store");
        System.out.println("3. See current cart");
        System.out.println("0. Exit");
        System.out.println("--------------------------------");
        System.out.print("Please choose a number: 0-1-2-3: ");
    }

    public static void viewStore() {
        storeMenu();
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        switch (choice) {
            case 1:
                seeMediaDetails();
                break;
            case 2:
                addMediaToCart();
                break;
            case 3:
                playMedia();
                break;
            case 4:
                seeCurrentCart(scanner);
                break;
            case 0:
                break;
            default:
                System.out.println("Invalid choice, please try again.");
        }
    }

    public static void storeMenu() {
        System.out.println("Options: ");
        System.out.println("--------------------------------");
        System.out.println("1. See a media’s details");
        System.out.println("2. Add a media to cart");
        System.out.println("3. Play a media");
        System.out.println("4. See current cart");
        System.out.println("0. Back");
        System.out.println("--------------------------------");
        System.out.print("Please choose a number: 0-1-2-3-4: ");
    }

    public static void seeMediaDetails() {
        System.out.print("Enter the title of the media: ");
        String title = scanner.nextLine();
        Media media = store.findMediaByTitle(title);
        
        if (media != null) {
            System.out.println(media.toString());
            mediaDetailsMenu();
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    addToCart(media);
                    break;
                case 2:
                    if (media instanceof Playable) {
                        ((Playable) media).play();
                    } else {
                        System.out.println("This media cannot be played.");
                    }
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        } else {
            System.out.println("Media not found.");
        }
    }

    public static void mediaDetailsMenu() {
        System.out.println("Options: ");
        System.out.println("--------------------------------");
        System.out.println("1. Add to cart");
        System.out.println("2. Play");
        System.out.println("0. Back");
        System.out.println("--------------------------------");
        System.out.print("Please choose a number: 0-1-2: ");
    }

    public static void addToCart(Media media) {
        cart.addMedia(media);
        System.out.println("Media added to cart.");
    }

    public static void addMediaToCart() {
        System.out.println("Available media in store:");
        for (Media media : store.getMediaList()) {
            System.out.println("Title: " + media.getTitle() + ", Category: " + media.getCategory() + ", Cost: " + media.getCost());
        }
        System.out.print("Enter the title of the media to add to cart: ");
        String title = scanner.nextLine();
        Media media = store.findMediaByTitle(title);
        
        if (media != null) {
            addToCart(media);
        } else {
            System.out.println("Media not found.");
        }
    }

    public static void playMedia() {
        System.out.print("Enter the title of the media to play: ");
        String title = scanner.nextLine();
        Media media = store.findMediaByTitle(title);
        
        if (media != null && media instanceof Playable) {
            ((Playable) media).play();
        } else {
            System.out.println("Media not found or cannot be played.");
        }
    }

    public static void updateStore() {
        System.out.println("Update store: ");
        System.out.println("--------------------------------");
        System.out.println("1. Add media");
        System.out.println("2. Remove media");
        System.out.println("0. Back");
        System.out.println("--------------------------------");
        System.out.print("Please choose a number: 0-1-2: ");
        
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character
        
        switch (choice) {
            case 1:
                addMediaToStore();
                break;
            case 2:
                removeMediaFromStore();
                break;
            case 0:
                break;
            default:
                System.out.println("Invalid choice, please try again.");
        }
    }

    public static void addMediaToStore() {
        System.out.print("Enter the title of the media to add: ");
        String title = scanner.nextLine();
        // You can add more logic here for specific media types.
        // Here, for simplicity, we assume a CD is added.
        Media media = new CompactDisc(title, "Music", 15.99f, "Director", 120, "Artist");
        store.addMedia(media);
        System.out.println("Media added to store.");
    }

    public static void removeMediaFromStore() {
        System.out.print("Enter the title of the media to remove: ");
        String title = scanner.nextLine();
        Media media = store.findMediaByTitle(title);
        
        if (media != null) {
            store.removeMedia(media);
        } else {
            System.out.println("Media not found.");
        }
    }

   
    public static void cartMenu(Scanner scanner) {
        System.out.println("Options: ");
        System.out.println("--------------------------------");
        System.out.println("1. Filter medias in cart");
        System.out.println("2. Sort medias in cart");
        System.out.println("3. Remove media from cart");
        System.out.println("4. Play a media");
        System.out.println("5. Place order");
        System.out.println("0. Back");
        System.out.println("--------------------------------");
        System.out.print("Please choose a number: ");
        int choice = scanner.nextInt();
        scanner.nextLine();  
        switch (choice) {
            case 1:
                cart.filterMediaInCart(scanner);
                break;
            case 2:
                cart.sortMediaInCart(scanner);
                break;
            case 3:
                System.out.println("Enter the title of the media to remove:");
                String titleToRemove = scanner.nextLine();
                cart.removeMediaByTitle(titleToRemove);
                break;

            case 4:
                cart.playMediaInCart(scanner);
                break;
            case 5:
                System.out.println("Order placed. Cart has been emptied.");
                cart.emptyCart();
                break;
            case 0:
                return;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }

   
    public static void removeMediaFromCart(Scanner scanner) {
        System.out.print("Enter the title of the media to remove from cart: ");
        String title = scanner.nextLine();
        Media media = cart.findMediaByTitle(title);
        
        if (media != null) {
            cart.removeMedia(media);
        } else {
            System.out.println("Media not found in cart.");
        }
    }

    public static void playMediaInCart(Scanner scanner) {
        System.out.print("Enter the title of the media to play: ");
        String title = scanner.nextLine();
        Media media = cart.findMediaByTitle(title);
        
        if (media != null && media instanceof Playable) {
            ((Playable) media).play();
        } else {
            System.out.println("Media not found or cannot be played.");
        }
    }

    public static void placeOrder(Scanner scanner) {
        System.out.println("Order placed. Your cart is now empty.");
        cart.clearCart();
    }
    
    private static void seeCurrentCart(Scanner scanner) {
        System.out.println("Current cart contents:");
        if (cart.isEmpty()) {
            System.out.println("Your cart is empty.");
        } else {
            for (Media media : cart.getMediaList()) {
                System.out.println("ID: " + media.getId() + " - Title: " + media.getTitle() + " - Category: " + media.getCategory() + " - Cost: " + media.getCost());
            }
        }
        cartMenu(scanner);
    }
}