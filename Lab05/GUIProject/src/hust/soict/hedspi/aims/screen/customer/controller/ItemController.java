package hust.soict.hedspi.aims.screen.customer.controller;

import javax.naming.LimitExceededException;

import hust.soict.hedspi.aims.cart.Cart.Cart;
import hust.soict.hedspi.aims.exception.PlayerException;
import hust.soict.hedspi.aims.media.Media;
import hust.soict.hedspi.aims.media.Playable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class ItemController {
    private Media media;
    private Cart cart;
    @FXML
    private Button btnAddToCart;

    @FXML
    private Button btnPlay;

    @FXML
    private Label lblTitle;

    @FXML
    private Label lblCost;

    @FXML
    void btnAddToCartClicked(ActionEvent event) {
        // Check if both media and cart are not null
        if (this.media != null && this.cart != null) {
            try {
                // Attempt to add the selected media item to the cart
                cart.addMedia(this.media);
                System.out.println("Added " + this.media.getTitle() + " to cart from ItemController.");

                // Show confirmation dialog
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText(null);
                alert.setContentText("Added '" + this.media.getTitle() + "' to the cart!");
                alert.showAndWait();
            } catch (LimitExceededException e) {
                // Handle the case when the cart is full (exceeds max number of items)
                System.err.println("Limit exceeded: " + e.getMessage());

                // Show error dialog for limit exceeded
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Cart Full");
                alert.setHeaderText(null);
                alert.setContentText("Cannot add this item. The cart is full!");
                alert.showAndWait();
            }
        } else {
            // Handle the case when media or cart is null
            System.err.println("Cart or Media is null in ItemController.");

            // Show generic error dialog
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Unable to add the item to the cart.");
            alert.showAndWait();
        }
    }



    @FXML
    void btnPlayClicked(ActionEvent event) {
        // Check if the current media is playable
        if (this.media instanceof Playable) {
            try {
                // Attempt to play the media (may throw PlayerException)
                ((Playable) this.media).play();

                // If play is successful, show a success message
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Playing Media");
                alert.setHeaderText(null);
                alert.setContentText("Now playing: " + this.media.getTitle());
                alert.showAndWait();

            } catch (PlayerException e) {
                // Handle PlayerException (e.g., invalid media length)

                // Print the exception to the console
                System.err.println("PlayerException caught in ItemController: " + e.getMessage());
                System.err.println("Exception toString(): " + e.toString());
                e.printStackTrace();

                // Show an error dialog with details
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Playback Error");
                alert.setHeaderText("Cannot play this media!");
                alert.setContentText(e.getMessage() + "\n\nError details: " + e.toString());
                alert.showAndWait();
            }
        } else {
            // If the media is not playable (e.g., Book), show a warning
            System.out.println("Selected item is not playable.");
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Cannot Play");
            alert.setHeaderText(null);
            alert.setContentText("The selected item cannot be played.");
            alert.showAndWait();
        }
    }


    public ItemController(Cart cart) {
        this.cart = cart;
    }


    public void setData(Media media) {
        this.media = media;
        lblTitle.setText(media.getTitle());
        lblCost.setText(media.getCost() + " $");
        
        if (media instanceof Playable) {
            btnPlay.setVisible(true);
        } else {
            btnPlay.setVisible(false);
            HBox.setMargin(btnAddToCart, new Insets(0, 0, 0, 60));
        }
    }

}