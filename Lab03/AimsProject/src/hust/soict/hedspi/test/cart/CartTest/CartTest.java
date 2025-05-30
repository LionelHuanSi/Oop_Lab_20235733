package hust.soict.hedspi.test.cart.CartTest;
import hust.soict.hedspi.aims.cart.Cart.Cart;
import hust.soict.hedspi.aims.media.DigitalVideoDisc;
public class CartTest {
	public static void main(String[] args) {
		Cart cart = new Cart();

        // Create sample DVDs
		DigitalVideoDisc dvd1 = new DigitalVideoDisc("The Lion King", "Animation", "Roger Allers", 87, 19.95f);
		DigitalVideoDisc dvd2 = new DigitalVideoDisc("Star Wars", "Science Fiction", "George Lucas", 82, 24.95f);
		DigitalVideoDisc dvd3 = new DigitalVideoDisc("Aladin", "Animation", 18.99f);

        // Add DVDs to the cart
        cart.addDigitalVideoDisc(dvd1);
        cart.addDigitalVideoDisc(dvd2);
        cart.addDigitalVideoDisc(dvd3);

        // Test print method
        cart.print();

		
	}
}
