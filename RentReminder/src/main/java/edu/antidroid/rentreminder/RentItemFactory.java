package edu.antidroid.rentreminder;

/**
 * Creates simple rent item instances.
 */
public final class RentItemFactory {

    public RentItem createRentItem(String label, String rentLabel) {
        RentItem rentItem = new RentItem();
        rentItem.setLabel(label);
        rentItem.setRentLabel(rentLabel);
        return rentItem;
    }
}
