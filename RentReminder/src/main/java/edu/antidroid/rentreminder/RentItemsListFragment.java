package edu.antidroid.rentreminder;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

/**
 * Represents the list fragment for the rent items.
 */
public class RentItemsListFragment extends Fragment {

    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static RentItemsListFragment newInstance(int sectionNumber) {
        RentItemsListFragment fragment = new RentItemsListFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * The {@link android.widget.ListView} that will contain the rent items.
     */
    private ListView mListView;

    /**
     * The {@link RentItemsModel} managing the rent items.
     */
    private final RentItemsModel mRentItemsModel = new RentItemsModel();

    public RentItemsListFragment() {
    }

    public ListView getListView() {
        return mListView;
    }

    public RentItemsModel getItemsModel() {
        return mRentItemsModel;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_overview, container, false);

        // Find the list view
        // and set the array adapter
        mListView = (ListView) rootView.findViewById(R.id.overview_listview);
        mListView.setAdapter(mRentItemsModel.getRentItemsAdapter());

        return rootView;
    }
}
