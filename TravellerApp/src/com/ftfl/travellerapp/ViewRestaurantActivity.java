package com.ftfl.travellerapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.ftfl.travellerapp.databasse.DataSource;
import com.ftfl.travellerapp.model.Profile;

public class ViewRestaurantActivity extends ActionBarActivity {

	int mId = 0;
	TextView mShowName = null;
	TextView mShowAddress = null;
	TextView mShowDescription = null;
	TextView mShowLatitude = null;
	TextView mShowLongitude = null;
	TextView mShowMenu = null;
	TextView mShowSpecialMenu = null;
	TextView mShowOpenTime = null;
	TextView mShowCloseDay = null;
	ImageView mShowImage = null;
	Button mShowMap = null;

	// Profile Object
	Profile mProfile = null;

	// Data Source
	DataSource mDataSource = null;

	// Photo Object
	String mPhotoPath = "";
	Bitmap bitmap = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view);
		// get the Intent that started this Activity
		Intent i = getIntent();

		// get the Bundle that stores the data of this Activity
		Bundle b = i.getExtras();

		mId = b.getInt("id");

		// TextView Find By ID
		mShowName = (TextView) findViewById(R.id.showName);
		mShowAddress = (TextView) findViewById(R.id.showAddress);
		mShowDescription = (TextView) findViewById(R.id.showDescription);
		mShowLatitude = (TextView) findViewById(R.id.showLatitude);
		mShowLongitude = (TextView) findViewById(R.id.showLongitude);
		mShowMenu = (TextView) findViewById(R.id.showMenu);
		mShowSpecialMenu = (TextView) findViewById(R.id.showSpecialMenu);
		mShowOpenTime = (TextView) findViewById(R.id.showOpenTime);
		mShowCloseDay = (TextView) findViewById(R.id.showCloseDay);
		mShowImage = (ImageView) findViewById(R.id.ivPhoto);
		mShowMap = (Button) findViewById(R.id.showMap);

		// Get Data From DataSource
		mDataSource = new DataSource(this);
		mProfile = mDataSource.getDetail(mId);

		// SetText To the TextView
		mShowName.setText(mProfile.getmName());
		mShowAddress.setText(mProfile.getmAddress());
		mShowDescription.setText(mProfile.getmDescription());
		mShowLatitude.setText(mProfile.getmLatitude());
		mShowLongitude.setText(mProfile.getmLongitude());
		mShowMenu.setText(mProfile.getmMenu());
		mShowSpecialMenu.setText(mProfile.getmSpecialMenu());
		mShowOpenTime.setText(mProfile.getmDailyOpenTime());
		mShowCloseDay.setText(mProfile.getmCloseDay());
		mPhotoPath = mProfile.getmImage();

		// Image Preview
		previewCapturedImage();
		mShowImage.setImageBitmap(bitmap);

		// View Map Button Operation
		mShowMap.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Bundle b = new Bundle();

				// put id into bundle
				b.putInt("id", mId);
				Intent mIntent = new Intent(getApplicationContext(),
						MapActivity.class);
				mIntent.putExtras(b);
				startActivity(mIntent);
			}
		});
	}

	/**
	 * Display image from a path to ImageView
	 */
	private void previewCapturedImage() {
		try {

			// bimatp factory
			BitmapFactory.Options options = new BitmapFactory.Options();

			// downsizing image as it throws OutOfMemory Exception for larger
			// images
			options.inSampleSize = 10;

			bitmap = BitmapFactory.decodeFile(mPhotoPath, options);

		} catch (NullPointerException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.menu_of_view, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		// Take appropriate action for each action item click
		switch (item.getItemId()) {
		case R.id.home:
			// profile found
			home();
			return true;

		case R.id.edit:
			// profile found
			edit();
			return true;

		case R.id.delete:
			// profile found
			delete();
			return true;

		case R.id.add:
			// add new
			add();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	private void home() {
		Intent mIntent = new Intent(getBaseContext(), MainActivity.class);
		startActivity(mIntent);

	}

	private void edit() {
		Bundle b = new Bundle();

		// put id into bundle
		b.putInt("id", mId);
		Intent mIntent = new Intent(getBaseContext(),
				AddResturantActivity.class);
		mIntent.putExtras(b);
		startActivity(mIntent);

	}

	private void delete() {
		mDataSource = new DataSource(this);
		mDataSource.deleteData(mId);
		Intent mIntent = new Intent(getBaseContext(), MainActivity.class);
		startActivity(mIntent);

	}

	private void add() {
		Intent mIntent = new Intent(getBaseContext(),
				AddResturantActivity.class);
		startActivity(mIntent);

	}
}