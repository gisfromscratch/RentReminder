package edu.antidroid.rentreminder;

import android.app.AlertDialog;
import android.content.Context;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Locale;

/**
 * Represents a view for adding rent items.
 */
public class AddRentItemDialog {

    private AlertDialog dialog;
    private Spinner rentItemTypeSpinner;
    private EditText labelEditText;
    private EditText rentLabelEditText;
    private DatePicker rentDatePicker;

    private ArrayAdapter<RentItemType> rentItemTypesAdapter;

    private RentItem newRentItem;

    void initialize(AlertDialog dialog) {
        this.dialog = dialog;

        // Create the view by using a layout xml
        Context context = dialog.getContext();
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View addNewView = layoutInflater.inflate(R.layout.rentitem_addnewview, null, false);

        rentItemTypeSpinner = (Spinner) addNewView.findViewById(R.id.addNew_rentItemTypeSpinner);
        rentItemTypesAdapter = new ArrayAdapter<RentItemType>(context, android.R.layout.simple_list_item_1, RentItemType.values());
        rentItemTypeSpinner.setAdapter(rentItemTypesAdapter);

        labelEditText = (EditText) addNewView.findViewById(R.id.addNew_labelTextInput);
        rentLabelEditText = (EditText) addNewView.findViewById(R.id.addNew_rentLabelTextInput);

        rentDatePicker = (DatePicker) addNewView.findViewById(R.id.addNew_rentDateInput);

        dialog.setView(addNewView);
    }

    public RentItem getNewRentItem() {
        newRentItem.setRentItemType((RentItemType) rentItemTypeSpinner.getSelectedItem());
        newRentItem.setLabel(labelEditText.getText().toString());
        newRentItem.setRentLabel(rentLabelEditText.getText().toString());

        Calendar calendar = Calendar.getInstance(Locale.getDefault());
        calendar.set(rentDatePicker.getYear(), rentDatePicker.getMonth(), rentDatePicker.getDayOfMonth());
        newRentItem.setRentDate(calendar.getTime());
        return newRentItem;
    }

    public void setNewRentItem(RentItem newRentItem) {
        this.newRentItem = newRentItem;

        labelEditText.setText(newRentItem.getLabel());
        rentLabelEditText.setText(newRentItem.getRentLabel());
    }

    public void show() {
        dialog.show();
    }
}
