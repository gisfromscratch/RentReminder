package edu.antidroid.rentreminder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Represents an array adapter for the rent items.
 * Defines the list view appearance.
 */
public class RentItemArrayAdapter extends ArrayAdapter<RentItem> {

    public RentItemArrayAdapter(Context context, ArrayList<RentItem> items) {
        super(context, android.R.layout.simple_list_item_multiple_choice, android.R.id.text1, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = layoutInflater.inflate(R.layout.rentitems_rowlayout, parent, false);

        final RentItem rentItem = getItem(position);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
        int drawableResourceId = getDrawableResourceId(rentItem.getRentItemType());
        imageView.setImageResource(drawableResourceId);

        TextView labelView = (TextView) rowView.findViewById(R.id.label);
        labelView.setText(rentItem.getLabel());

        TextView rentLabelView = (TextView) rowView.findViewById(R.id.rent_label);
        rentLabelView.setText(rentItem.getRentLabel());

        TextView dateLabelView = (TextView) rowView.findViewById(R.id.date_label);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        dateLabelView.setText(dateFormat.format(rentItem.getRentDate()));

        CheckBox checkBox = (CheckBox) rowView.findViewById(R.id.checkbox);
        checkBox.setChecked(rentItem.getChecked());
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean checked) {
                rentItem.setChecked(checked);
            }
        });

        return rowView;
    }

    private int getDrawableResourceId(RentItemType rentItemType) {
        switch (rentItemType) {
            case Book:
                return R.drawable.book;
            case Disc:
                return R.drawable.disc;
            default:
                return R.drawable.book;
        }
    }
}
