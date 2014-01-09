package edu.antidroid.rentreminder;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.InputType;
import android.widget.EditText;

import java.util.concurrent.Callable;

/**
 * Creates input dialogs for adding and/or editing rent items.
 */
public class InputDialogFactory {

    /**
     * The {@link RentItemFactory} creating the rent items.
     */
    private static final RentItemFactory rentItemFactory = new RentItemFactory();

    public AddRentItemDialog createAddRentItemDialog(Context context, final Visitor<RentItem> approveAction) {
        final AddRentItemDialog addRentItemDialog = new AddRentItemDialog();
        RentItem defaultRentItem = rentItemFactory.createRentItem("New Rent Item", "rent to");

        AlertDialog alertDialog = new AlertDialog.Builder(context)
                .setTitle("Add Rent Item")
                //.setMessage("New Rent Item")
                .setPositiveButton("Add", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        RentItem newRentItem = addRentItemDialog.getNewRentItem();
                        approveAction.visit(newRentItem);
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                }).create();


        addRentItemDialog.initialize(alertDialog);
        addRentItemDialog.setNewRentItem(defaultRentItem);
        return addRentItemDialog;
    }
}
