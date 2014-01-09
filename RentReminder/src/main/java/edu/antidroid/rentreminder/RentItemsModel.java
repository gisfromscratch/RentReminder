package edu.antidroid.rentreminder;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

/**
 * Represents the rent items model.
 */
public class RentItemsModel {

    /**
     * The {@link android.widget.ArrayAdapter} representing the rent items adapter.
     */
    private ArrayAdapter<RentItem> rentItemsAdapter;

    /**
     * The {@link java.util.ArrayList} the rent items.
     */
    private final ArrayList<RentItem> rentItems = new ArrayList<RentItem>();

    private final PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    public void initialize(Context context) {
        rentItemsAdapter = new RentItemArrayAdapter(context, rentItems);
    }

    public ListAdapter getRentItemsAdapter() {
        return rentItemsAdapter;
    }

    public ArrayList<RentItem> getCheckedRentItems() {
        ArrayList<RentItem> checkedRentItems = new ArrayList<RentItem>();
        for (RentItem rentItem : rentItems) {
            if (rentItem.getChecked()) {
                checkedRentItems.add(rentItem);
            }
        }
        return checkedRentItems;
    }

    public void addRentItem(RentItem rentItem) {
        rentItemsAdapter.add(rentItem);
        propertyChangeSupport.firePropertyChange("addRentItem", null, rentItem);
    }

    public void removeRentItem(RentItem rentItem) {
        rentItemsAdapter.remove(rentItem);
        propertyChangeSupport.firePropertyChange("removeRentItem", null, rentItem);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.removePropertyChangeListener(listener);
    }
}
