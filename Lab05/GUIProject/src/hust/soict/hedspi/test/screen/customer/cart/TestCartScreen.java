package hust.soict.hedspi.test.screen.customer.cart;


import javax.naming.LimitExceededException;

import hust.soict.hedspi.aims.cart.Cart.Cart;
import hust.soict.hedspi.aims.media.Book;
import hust.soict.hedspi.aims.media.DigitalVideoDisc;
import hust.soict.hedspi.aims.screen.customer.controller.CartController;
import hust.soict.hedspi.aims.store.Store;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TestCartScreen extends Application {

    private static Cart cart;
    private static Store store;
    @Override
    public void start(Stage primaryStage) throws Exception {
        final String CART_FXML_PATH = "/hust/soict/hedspi/aims/screen/customer/view/Cart.fxml";

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(CART_FXML_PATH));
        CartController cartController = new CartController(store,cart);
        fxmlLoader.setController(cartController);

        Parent root = fxmlLoader.load();
        primaryStage.setTitle("Cart");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
    
    public static void main(String[] args) {
        cart = new Cart();
        store = new Store();

        try {
            // Thêm media vào cart
            cart.addMedia(new Book("Harry Potter and the Philosopher's Stone (2001)", "Fantasy", 3.0f));
            cart.addMedia(new Book("Harry Potter and the Chamber of Secrets (2002)", "Fantasy", 3.5f));
            cart.addMedia(new Book("Harry Potter and the Prisoner of Azkaban (2004)", "Fantasy", 5.0f));
            cart.addMedia(new Book("Harry Potter and the Goblet of Fire (2005)", "Fantasy", 4.5f));
            cart.addMedia(new DigitalVideoDisc("Inception", "Sci-Fi", "Nolan", 148, 12.0f));
        } catch (LimitExceededException e) {
            System.err.println("Failed to add media to cart: " + e.getMessage());
        }

        // Thêm media vào store (không giới hạn nên không cần try-catch)
        store.addMedia(new Book("The Alchemist", "Novel", 9.5f));
        store.addMedia(new DigitalVideoDisc("Interstellar", "Sci-Fi", "Christopher Nolan", 169, 14.0f));

        launch(args);
    }

}
