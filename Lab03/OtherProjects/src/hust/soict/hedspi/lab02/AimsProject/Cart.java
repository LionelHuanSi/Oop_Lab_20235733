
public class Cart {
	public static final int MAX_NUMBERS_ORDERED = 20;
	private DigitalVideoDisc itemsOrdered[] = new DigitalVideoDisc[MAX_NUMBERS_ORDERED];
	private int qtyOrdered = 0;
	 
	public void addDigitalVideoDisc(DigitalVideoDisc disc) {
		if(qtyOrdered < 20) {
			itemsOrdered[qtyOrdered] = disc;
			qtyOrdered++;
			System.out.println("The Disc \"" + disc.getTitle() + "\" has been added.");
		}
		else {
			System.out.println("The cart is almost full. Cannot add more DVDs.");
		}
	}
//	// different types of parameters. 
//	public void addDigitalVideoDisc(DigitalVideoDisc[] dvdList) {
//		for (DigitalVideoDisc dvd : dvdList) {
//			addDigitalVideoDisc(dvd);
//		}
//	}
	
	// arbitrary number of arguments
	public void addDigitalVideoDisc(DigitalVideoDisc... dvds) {
		for (DigitalVideoDisc dvd : dvds)
		{
			addDigitalVideoDisc(dvd);
		}
	}
	
	// differing the number of parameters
	public void addDigitalVideoDisc(DigitalVideoDisc dvd1, DigitalVideoDisc dvd2) {
		addDigitalVideoDisc(dvd1);
		addDigitalVideoDisc(dvd2);
	}
	
	public void removeDigitalVideoDisc(DigitalVideoDisc disc) {
		for(int i = 0; i < qtyOrdered; i++) {
			if(itemsOrdered[i] == disc) {
				for(int j = i; j  < qtyOrdered - 1; j++) {
					itemsOrdered[j] = itemsOrdered[j+1];
				}
				itemsOrdered[qtyOrdered - 1] = null;
				qtyOrdered--;
				System.out.print("The Disc \"" + disc.getTitle() + "\" has been removed");
				return;
			}
		}
		System.out.println("The Disc \"" + disc.getTitle() + "\" was not found in the cart.");
		
	}
	
	public float totalCost() {
		float total = 0;
		for(int i = 0; i < qtyOrdered; i++) {
			total += itemsOrdered[i].getCost();
		}
		return total;
	}
}
