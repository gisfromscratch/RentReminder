package edu.antidroid.rentreminder;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;

public class OverviewActivity extends ActionBarActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    ViewPager mViewPager;

    /**
     * The {@link InputDialogFactory} creating input dialogs for rent items.
     */
    private static final InputDialogFactory mInputDialogFactory = new InputDialogFactory();

    /**
     * The {@link RentItemFactory} creating the rent items.
     */
    private static final RentItemFactory mRentItemFactory = new RentItemFactory();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overview);

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mSectionsPagerAdapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.overview, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.action_add:
                addNewRentItem();
                return true;
            case R.id.action_remove:
                removeSelectedRentItems();
                return true;
            case R.id.action_settings:
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void addNewRentItem() {
         AddRentItemDialog addRentItemDialog = mInputDialogFactory.createAddRentItemDialog(this, new Visitor<RentItem>() {

             @Override
             public void visit(RentItem newRentItem) {
                 RentItemsModel rentItemsModel = getRentItemsModelFromCurrentFragment();
                 if (null != rentItemsModel) {
                     rentItemsModel.addRentItem(newRentItem);
                 }
             }
         });
         addRentItemDialog.show();
    }

    private void removeSelectedRentItems() {
        ListView rentItemsListView = getRentItemsListViewFromCurrentFragment();
        RentItemsModel rentItemsModel = getRentItemsModelFromCurrentFragment();
        if (null != rentItemsListView && null != rentItemsModel) {
            // Remove the rent items
            ArrayList<RentItem> rentItemsToRemove = rentItemsModel.getCheckedRentItems();
            for (RentItem rentItem : rentItemsToRemove) {
                rentItemsModel.removeRentItem(rentItem);
            }

            // Clear the checked states
            rentItemsListView.clearChoices();
        }
    }

    private ListView getRentItemsListViewFromCurrentFragment() {
        int fragmentIndex = mViewPager.getCurrentItem();
        RentItemsListFragment listFragment = (RentItemsListFragment) mSectionsPagerAdapter.getItem(fragmentIndex);
        if (null != listFragment) {
            return listFragment.getListView();
        }
        return null;
    }

    private RentItemsModel getRentItemsModelFromCurrentFragment() {
        int fragmentIndex = mViewPager.getCurrentItem();
        RentItemsListFragment listFragment = (RentItemsListFragment) mSectionsPagerAdapter.getItem(fragmentIndex);
        if (null != listFragment) {
            return listFragment.getItemsModel();
        }
        return null;
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        private final Context context;

        private final HashMap<Integer, Fragment> fragments = new HashMap<Integer, Fragment>();

        public SectionsPagerAdapter(Context context, FragmentManager fm) {
            super(fm);
            this.context = context;
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            if (fragments.containsKey(position)) {
                return fragments.get(position);
            }

            // Return a RentItemsListFragment (defined as a static inner class below).
            RentItemsListFragment newFragment = RentItemsListFragment.newInstance(position + 1);
            fragments.put(position, newFragment);

            // Initialize the rent items model with this instance
            RentItemsModel rentItemsModel = newFragment.getItemsModel();
            rentItemsModel.initialize(context);
            return newFragment;
        }

        @Override
        public int getCount() {
            // Show 2 total pages.
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            Locale l = Locale.getDefault();
            switch (position) {
                case 0:
                    return getString(R.string.title_section1).toUpperCase(l);
                case 1:
                    return getString(R.string.title_section2).toUpperCase(l);
            }
            return null;
        }
    }
}
