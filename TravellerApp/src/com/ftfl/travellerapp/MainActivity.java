package com.ftfl.travellerapp;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.ftfl.travellerapp.adapter.CustomAdapter;
import com.ftfl.travellerapp.databasse.DataSource;
import com.ftfl.travellerapp.model.Profile;

public class MainActivity extends ActionBarActivity {

	/* Variable Declaration */
	int mID = 0;
	ListView mListView = null;
	TextView mItemID = null;

	// Profile Object
	Profile mProfile = null;

	// Database Object
	DataSource mDataSource = null;

	// Profile Array List
	ArrayList<Profile> mProfileArrayList = null;

	// Custom Adapter
	CustomAdapter mAdapter = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);

		// Find Listview
		mListView = (ListView) findViewById(R.id.lvRestaurant);

		// Get Data From DataSource
		mDataSource = new DataSource(this);
		mProfileArrayList = mDataSource.getPlaceList();

		// Set Adapter
		mAdapter = new CustomAdapter(this, mProfileArrayList);
		mListView.setAdapter(mAdapter);

		// List Item Click operation
		mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				// listrow, which is hidden/gone
				mItemID = (TextView) view.findViewById(R.id.tvID);
				String msID = mItemID.getText().toString();
				mID = Integer.parseInt(msID);

				// Creating Bundle object
				Bundle b = new Bundle();

				// put id into bundle
				b.putInt("id", mID);
				Intent i = new Intent(getBaseContext(),
						ViewRestaurantActivity.class);
				// Storing bundle object into intent
				i.putExtras(b);
				startActivity(i);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.menu_of_home, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		// Take appropriate action for each action item click
		switch (item.getItemId()) {
		case R.id.add:
			// add new
			Intent i = new Intent(getBaseContext(), AddResturantActivity.class);
			startActivity(i);
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}
}