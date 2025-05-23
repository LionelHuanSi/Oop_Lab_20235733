package hust.soict.hedspi.aims.screen.customer.controller;

import java.io.IOException;

import hust.soict.hedspi.aims.cart.Cart.Cart;
import hust.soict.hedspi.aims.exception.PlayerException;
import hust.soict.hedspi.aims.media.Media;
import hust.soict.hedspi.aims.media.Playable;
import hust.soict.hedspi.aims.store.Store;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class CartController {
	private Store store;
	private Cart cart;
	public CartController(Store store, Cart cart) {
	    this.store = store;
	    this.cart = cart;
	}
	
	
    @FXML
    private TableColumn<hust.soict.hedspi.aims.media.Media, Integer> colMediaId;

    @FXML
    private Label costLabel;

    @FXML
    private TableView<hust.soict.hedspi.aims.media.Media> tblMedia;

    @FXML
    private TableColumn<hust.soict.hedspi.aims.media.Media, Float> colMediaCost;

    @FXML
    private ToggleGroup filterCategory;

    @FXML
    private Button btnPlay;

    @FXML
    private Button btnRemove;

    @FXML
    private TableColumn<hust.soict.hedspi.aims.media.Media, String> colMediaTitle;

    @FXML
    private TableColumn<hust.soict.hedspi.aims.media.Media, String> colMediaCategory;

    @FXML
    void btnPlayPressed(ActionEvent event) {
        Media selectedMedia = tblMedia.getSelectionModel().getSelectedItem();

        if (selectedMedia instanceof Playable) {
            try {
                // Try to play the selected media (may throw PlayerException)
                ((Playable) selectedMedia).play();

                // If play is successful, show information alert
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Playing Media");
                alert.setHeaderText(null);
                alert.setContentText("Now playing: " + selectedMedia.getTitle());
                alert.showAndWait();

            } catch (PlayerException e) {
                // Catch PlayerException and display error details
                System.err.println("PlayerException caught in CartController: " + e.getMessage());
                System.err.println("Exception toString(): " + e.toString());
                e.printStackTrace(); // Print stack trace to console

                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Playback Error");
                alert.setHeaderText("Cannot play the media!");
                alert.setContentText(e.getMessage() + "\n\nError details: " + e.toString());
                alert.showAndWait();
            }

        } else {
            // Selected media is not playable (e.g., Book)
            System.out.println("Selected item is not playable.");
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Cannot Play");
            alert.setHeaderText(null);
            alert.setContentText("The selected item cannot be played.");
            alert.showAndWait();
        }
    }
    @FXML
    private Button btnPlaceOrder;
    @FXML
    void btnPlaceOrderPressed(ActionEvent event) {
        // Kiểm tra nếu giỏ hàng trống
        if (cart.getItemsOrdered().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Cart is Empty");
            alert.setHeaderText("No items in the cart");
            alert.setContentText("Please add items to the cart before placing an order.");
            alert.showAndWait();
            return;
        }

        // Tiến hành đặt hàng: xóa tất cả sản phẩm trong giỏ
        cart.clearCart();

        // Cập nhật lại tổng chi phí (lúc này sẽ là 0)
        updateTotalCost();

        // Hiển thị thông báo xác nhận đặt hàng thành công
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Order Successful");
        alert.setHeaderText("Thank you for your purchase!");
        alert.setContentText("Your order has been placed successfully.");
        alert.showAndWait();
    }


    
    private void updateTotalCost() {
        float total = cart.totalCost();
        costLabel.setText(String.format("Total: %.2f $", total));
    }


    @FXML
    private Button btnViewStore;

    @FXML
    void btnViewStorePressed(ActionEvent event) {
        try {
            final String STORE_FXML_FILE_PATH = "/hust/soict/hedspi/aims/screen/customer/view/Store.fxml";

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(STORE_FXML_FILE_PATH));
            fxmlLoader.setController(new ViewStoreController(store, cart)); // Gán controller thủ công

            Parent root = fxmlLoader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Store");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Lỗi chuyển màn hình");
            alert.setHeaderText("Không thể tải màn hình cửa hàng.");
            alert.setContentText("Chi tiết lỗi: " + e.getMessage());
            alert.showAndWait();
        }
    }



    
    @FXML
	public void initialize() {
	    colMediaId.setCellValueFactory(
	        new PropertyValueFactory<Media, Integer>("id"));
	    colMediaTitle.setCellValueFactory(
	        new PropertyValueFactory<Media, String>("title"));
	    colMediaCategory.setCellValueFactory(
	        new PropertyValueFactory<Media, String>("category"));
	    colMediaCost.setCellValueFactory(
	        new PropertyValueFactory<Media, Float>("cost"));

	    if (cart.getItemsOrdered() != null) {
	        tblMedia.setItems(cart.getItemsOrdered());
	    }
	    btnPlay.setVisible(false);
	    btnRemove.setVisible(false);
	    tblMedia.getSelectionModel().selectedItemProperty().addListener(
	    	    new ChangeListener<Media>() {
	    	        @Override
	    	        public void changed(ObservableValue<? extends Media> observable, Media oldValue, Media newValue) {
	    	            updateButtonBar(newValue);
	    	        }
	    	    }
	    	);
	    tfFilter.textProperty().addListener(new ChangeListener<String>() {
	        @Override
	        public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
	            showFilteredMedia(newValue);  // gọi phương thức lọc
	        }
	    });
	    updateTotalCost();

	}
    private void updateButtonBar(Media media) {
        if (media == null) {
            btnPlay.setVisible(false);
            btnRemove.setVisible(false);
        } else {
            btnRemove.setVisible(true);
            if (media instanceof Playable) {
                btnPlay.setVisible(true);
            } else {
                btnPlay.setVisible(false);
            }
        }
    }
    @FXML
    void btnRemovePressed(ActionEvent event) {
        Media selectedMedia = tblMedia.getSelectionModel().getSelectedItem();
        if (selectedMedia != null) {
            cart.removeMedia(selectedMedia);
            System.out.println("Removed: " + selectedMedia.getTitle() + " from cart.");
            updateTotalCost();
        } else {
            System.out.println("No item selected to remove.");
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Cảnh báo");
            alert.setHeaderText(null);
            alert.setContentText("Vui lòng chọn một mục để xóa.");
            alert.showAndWait();
        }
    }

    @FXML 
    private TextField tfFilter;
    @FXML 
    private RadioButton radioBtnFilterId;
    @FXML 
    private RadioButton radioBtnFilterTitle;
    private FilteredList<Media> filteredData;

    private void showFilteredMedia(String keyword) {
        if (filteredData == null) {
            filteredData = new FilteredList<>(cart.getItemsOrdered(), p -> true);
            tblMedia.setItems(filteredData);
        }

        filteredData.setPredicate(media -> {
            if (keyword == null || keyword.isEmpty()) {
                return true;
            }

            String lowerCaseKeyword = keyword.toLowerCase();

            if (radioBtnFilterId.isSelected()) {
                return String.valueOf(media.getId()).contains(lowerCaseKeyword);
            } else if (radioBtnFilterTitle.isSelected()) {
                return media.getTitle().toLowerCase().contains(lowerCaseKeyword);
            }

            return true;
        });
    }

}
