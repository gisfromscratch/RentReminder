package edu.antidroid.rentreminder;

import java.util.Date;

/**
 * Represents a rent item.
 */
public class RentItem {

    private String label = "Unknown";

    private String rentLabel = "...";

    private RentItemType rentItemType = RentItemType.Book;

    private Date rentDate = new Date();

    private boolean checked = false;

    public void setLabel(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public void setRentLabel(String rentLabel) {
        this.rentLabel = rentLabel;
    }

    public String getRentLabel() {
        return rentLabel;
    }

    public RentItemType getRentItemType() {
        return rentItemType;
    }

    public void setRentItemType(RentItemType rentItemType) {
        this.rentItemType = rentItemType;
    }

    public Date getRentDate() {
        return rentDate;
    }

    public void setRentDate(Date rentDate) {
        this.rentDate = rentDate;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public boolean getChecked() {
        return checked;
    }

    @Override
    public String toString() {
        return getLabel();
    }
}
