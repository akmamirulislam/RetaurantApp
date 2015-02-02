package com.ftfl.travellerapp;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ftfl.travellerapp.databasse.DataSource;
import com.ftfl.travellerapp.model.Profile;

public class AddResturantActivity extends ActionBarActivity {

	EditText mEditTextName = null;
	EditText mEditTextAddress = null;
	EditText mEditTextDescription = null;
	EditText mEditTextLatitude = null;
	EditText mEditTextLongitude = null;
	EditText mEditTextMenu = null;
	EditText mEditTextSpecialMenu = null;
	EditText mEditTextDailyOpenTime = null;
	EditText mEditTextCloseDay = null;
	Button mBtnSave = null;
	Button mBtnPhoto = null;

	String mName = "";
	String mAddress = "";
	String mDescription = "";
	String mLatitude = "";
	String mLongitude = "";
	String mMenu = "";
	String mSpecialMenu = "";
	String mDailyOpenTime = "";
	String mCloseDay = "";

	int mID = 0;
	Bundle mBundle = null;
	TextView mTittle = null;
	ImageView imgPreview;
	static File mediaFile;
	static String mCurrentPhotoPath = "";

	// Profile Object
	Profile mProfile = null;
	DataSource mDataSource = null;

	// Activity request codes
	private static final int CAMERA_CAPTURE_IMAGE_REQUEST_CODE = 100;
	public static final int MEDIA_TYPE_IMAGE = 1;

	// directory name to store captured images and videos
	private static final String IMAGE_DIRECTORY_NAME = "Restaurant_List";

	private Uri fileUri; // file url to store image

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add);
		mDataSource = new DataSource(this);

		// Initialization
		mEditTextName = (EditText) findViewById(R.id.etName);
		mEditTextAddress = (EditText) findViewById(R.id.etAddress);
		mEditTextDescription = (EditText) findViewById(R.id.etDescription);
		mEditTextLatitude = (EditText) findViewById(R.id.etLatitude);
		mEditTextLongitude = (EditText) findViewById(R.id.etLongitude);
		mEditTextMenu = (EditText) findViewById(R.id.etMenu);
		mEditTextSpecialMenu = (EditText) findViewById(R.id.etSpecialMenu);
		mEditTextDailyOpenTime = (EditText) findViewById(R.id.etDailyOpenTime);
		mEditTextCloseDay = (EditText) findViewById(R.id.etCloseDay);
		mBtnSave = (Button) findViewById(R.id.bSave);
		mBtnPhoto = (Button) findViewById(R.id.bPhoto);

		// get the Intent that started this Activity
		Intent mIntent = getIntent();

		// get the Bundle that stores the data of this Activity
		mBundle = mIntent.getExtras();

		if (mBundle != null) {
			mTittle.setText(R.string.update);
			mID = mBundle.getInt("id");

			if (mID > 0) {
				mDataSource = new DataSource(this);
				mProfile = mDataSource.getDetail(mID);

				mEditTextName.setText(mProfile.getmName());
				mEditTextName.setEnabled(true);
				mEditTextName.setFocusable(true);
				mEditTextName.setClickable(true);

				mEditTextAddress.setText(mProfile.getmAddress());
				mEditTextAddress.setEnabled(true);
				mEditTextAddress.setFocusable(true);
				mEditTextAddress.setClickable(true);

				mEditTextDescription.setText(mProfile.getmDescription());
				mEditTextDescription.setEnabled(true);
				mEditTextDescription.setFocusable(true);
				mEditTextDescription.setClickable(true);

				mEditTextLatitude.setText(mProfile.getmLatitude());
				mEditTextLatitude.setEnabled(true);
				mEditTextLatitude.setFocusable(true);
				mEditTextLatitude.setClickable(true);

				mEditTextLongitude.setText(mProfile.getmLongitude());
				mEditTextLongitude.setEnabled(true);
				mEditTextLongitude.setFocusable(true);
				mEditTextLongitude.setClickable(true);

				mEditTextMenu.setText(mProfile.getmMenu());
				mEditTextMenu.setEnabled(true);
				mEditTextMenu.setFocusable(true);
				mEditTextMenu.setClickable(true);

				mEditTextSpecialMenu.setText(mProfile.getmSpecialMenu());
				mEditTextSpecialMenu.setEnabled(true);
				mEditTextSpecialMenu.setFocusable(true);
				mEditTextSpecialMenu.setClickable(true);

				mEditTextDailyOpenTime.setText(mProfile.getmDailyOpenTime());
				mEditTextDailyOpenTime.setEnabled(true);
				mEditTextDailyOpenTime.setFocusable(true);
				mEditTextDailyOpenTime.setClickable(true);

				mEditTextCloseDay.setText(mProfile.getmCloseDay());
				mEditTextCloseDay.setEnabled(true);
				mEditTextCloseDay.setFocusable(true);
				mEditTextCloseDay.setClickable(true);
			}
		}
		mBtnPhoto.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// capture picture
				captureImage();
				mCurrentPhotoPath = mediaFile.getAbsolutePath();
			}
		});

		// Checking camera availability
		if (!isDeviceSupportCamera()) {
			Toast.makeText(getApplicationContext(),
					"Sorry! Your device doesn't support camera",
					Toast.LENGTH_LONG).show();
			// will close the app if the device does't have camera
			finish();
		}

		mBtnSave.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				getData();

				if (mBundle != null) {
					mProfile = new Profile(mName, mAddress, mDescription,
							mLatitude, mLongitude, mMenu, mSpecialMenu,
							mDailyOpenTime, mCloseDay, mCurrentPhotoPath);
					long updated = mDataSource.updateData(mID, mProfile);
					if (updated >= 0) {
						Toast.makeText(getApplicationContext(),
								R.string.updatecom, Toast.LENGTH_LONG).show();
						Intent i = new Intent(getApplicationContext(),
								MainActivity.class);
						startActivity(i);
						finish();
					} else
						Toast.makeText(getApplicationContext(),
								R.string.updateprob, Toast.LENGTH_LONG).show();
				} else if (mEditTextName.length() == 0
						|| mEditTextAddress.length() == 0
						|| mEditTextDescription.length() == 0
						|| mEditTextLatitude.length() == 0
						|| mEditTextLongitude.length() == 0
						|| mEditTextMenu.length() == 0
						|| mEditTextSpecialMenu.length() == 0
						|| mEditTextDailyOpenTime.length() == 0
						|| mEditTextCloseDay.length() == 0) {

					// show toast when not correctly completed
					Toast.makeText(getApplicationContext(), R.string.form,
							Toast.LENGTH_SHORT).show();
				} else if (mEditTextDescription.length() < 225) {
					Toast.makeText(getApplicationContext(), R.string.condition,
							Toast.LENGTH_SHORT).show();
				} else {
					mProfile = new Profile(mName, mAddress, mDescription,
							mLatitude, mLongitude, mMenu, mSpecialMenu,
							mDailyOpenTime, mCloseDay, mCurrentPhotoPath);

					long inserted = mDataSource.addNewPlace(mProfile);
					if (inserted >= 0) {
						Toast.makeText(getApplicationContext(),
								getString(R.string.insert), Toast.LENGTH_LONG)
								.show();

						Intent i = new Intent(getApplicationContext(),
								MainActivity.class);
						startActivity(i);
						finish();
					} else {
						Toast.makeText(getApplicationContext(),
								getString(R.string.fail), Toast.LENGTH_LONG)
								.show();
					}
				}

			}
		});
	}

	void getData() {
		mName = mEditTextName.getText().toString();
		mAddress = mEditTextAddress.getText().toString();
		mDescription = mEditTextDescription.getText().toString();
		mLatitude = mEditTextLatitude.getText().toString();
		mLongitude = mEditTextLongitude.getText().toString();
		mMenu = mEditTextMenu.getText().toString();
		mSpecialMenu = mEditTextSpecialMenu.getText().toString();
		mDailyOpenTime = mEditTextDailyOpenTime.getText().toString();
		mCloseDay = mEditTextCloseDay.getText().toString();

	}

	/**
	 * Checking device has camera hardware or not
	 * */
	private boolean isDeviceSupportCamera() {
		if (getApplicationContext().getPackageManager().hasSystemFeature(
				PackageManager.FEATURE_CAMERA)) {
			// this device has a camera
			return true;
		} else {
			// no camera on this device
			return false;
		}
	}

	/**
	 * Capturing Camera Image will lauch camera app requrest image capture
	 */
	private void captureImage() {
		Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

		fileUri = getOutputMediaFileUri(MEDIA_TYPE_IMAGE);

		intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);

		// start the image capture Intent
		startActivityForResult(intent, CAMERA_CAPTURE_IMAGE_REQUEST_CODE);
	}

	/**
	 * Here we store the file url as it will be null after returning from camera
	 * app
	 */
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);

		// save file url in bundle as it will be null on scren orientation
		// changes
		outState.putParcelable("file_uri", fileUri);
	}

	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);

		// get the file url
		fileUri = savedInstanceState.getParcelable("file_uri");
	}

	/**
	 * Receiving activity result method will be called after closing the camera
	 * */
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// if the result is capturing Image
		if (requestCode == CAMERA_CAPTURE_IMAGE_REQUEST_CODE) {
			if (resultCode == RESULT_OK) {
				// successfully captured the image
				Toast.makeText(getApplicationContext(), "image captured",
						Toast.LENGTH_SHORT).show();
			} else if (resultCode == RESULT_CANCELED) {
				// user cancelled Image capture
				Toast.makeText(getApplicationContext(),
						"User cancelled image capture", Toast.LENGTH_SHORT)
						.show();
			} else {
				// failed to capture image
				Toast.makeText(getApplicationContext(),
						"Sorry! Failed to capture image", Toast.LENGTH_SHORT)
						.show();
			}
		}
	}

	/**
	 * ------------ Helper Methods ----------------------
	 * */

	/**
	 * Creating file uri to store image
	 */
	public Uri getOutputMediaFileUri(int type) {
		Uri mUri = Uri.fromFile(getOutputMediaFile(type));
		return mUri;
	}

	/**
	 * returning image
	 */
	private static File getOutputMediaFile(int type) {

		// External sdcard location
		File mediaStorageDir = new File(
				Environment
						.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
				IMAGE_DIRECTORY_NAME);

		// Create the storage directory if it does not exist
		if (!mediaStorageDir.exists()) {
			if (!mediaStorageDir.mkdirs()) {
				Log.d(IMAGE_DIRECTORY_NAME, "Oops! Failed create "
						+ IMAGE_DIRECTORY_NAME + " directory");
				return null;
			}
		}

		// Create a media file name
		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss",
				Locale.getDefault()).format(new Date());

		if (type == MEDIA_TYPE_IMAGE) {
			mediaFile = new File(mediaStorageDir.getPath() + File.separator
					+ "IMG_" + timeStamp + ".jpg");
		} else {
			return null;
		}

		return mediaFile;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.menu_of_create, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		// Take appropriate action for each action item click
		switch (item.getItemId()) {
		case R.id.home:
			// profile found
			Intent i = new Intent(getBaseContext(), MainActivity.class);
			startActivity(i);
			return true;

		default:
			return super.onOptionsItemSelected(item);
		}
	}
}
