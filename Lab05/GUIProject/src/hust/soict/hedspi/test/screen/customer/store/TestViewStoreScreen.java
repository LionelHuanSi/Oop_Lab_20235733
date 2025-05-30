package hust.soict.hedspi.test.screen.customer.store;

import hust.soict.hedspi.aims.cart.Cart.Cart;
import hust.soict.hedspi.aims.media.Book;
import hust.soict.hedspi.aims.media.CompactDisc;
import hust.soict.hedspi.aims.media.DigitalVideoDisc;
import hust.soict.hedspi.aims.media.Track;
import hust.soict.hedspi.aims.screen.customer.controller.ViewStoreController;
import hust.soict.hedspi.aims.store.Store;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TestViewStoreScreen extends Application {

    private static Store store;
    private static Cart cart;

    @Override
    public void start(Stage primaryStage) throws Exception {
        final String STORE_FXML_FILE_PATH = "/hust/soict/hedspi/aims/screen/customer/view/Store.fxml";

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(STORE_FXML_FILE_PATH));
        ViewStoreController viewStoreController = new ViewStoreController(store, cart);
        fxmlLoader.setController(viewStoreController);

        Parent root = fxmlLoader.load();
        primaryStage.setTitle("Store");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public static void main(String[] args) {
        store = new Store();
        cart = new Cart();

        // Add sample Books
        store.addMedia(new Book("Harry Potter and the Philosopher's Stone (2001)", "Fantasy", 3.0f));
        store.addMedia(new Book("Harry Potter and the Chamber of Secrets (2002)", "Fantasy", 3.5f));
        store.addMedia(new Book("Harry Potter and the Prisoner of Azkaban (2004)", "Fantasy", 5.0f));
        store.addMedia(new Book("Harry Potter and the Goblet of Fire (2005)", "Fantasy", 4.5f));

        // Add a sample DVD
        store.addMedia(new DigitalVideoDisc("Inception", "Sci-Fi", "Christopher Nolan", 148, 12.0f));

        // Add a sample CD
        CompactDisc cd = new CompactDisc("The Greatest Hits", "Pop", 10.0f, "Producer", 60, "Singer");
        cd.addTrack(new Track(200, "Track One"));
        cd.addTrack(new Track(180, "Track Two"));
        store.addMedia(cd);

        launch(args);
    }
}
