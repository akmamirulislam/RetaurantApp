package com.ftfl.travellerapp.databasse;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.ftfl.travellerapp.model.Profile;

public class DataSource {
	private SQLiteDatabase db;
	private DBHelper dbHelper;

	public DataSource(Context context) {
		dbHelper = new DBHelper(context);
	}

	public void open() throws SQLException {
		db = dbHelper.getWritableDatabase();
	}

	public void close() {
		dbHelper.close();
	}

	// Adding new
	public long addNewPlace(Profile place) {
		open();
		ContentValues values = new ContentValues();
		values.put(DBHelper.KEY_NAME, place.getmName());
		values.put(DBHelper.KEY_ADDRESS, place.getmAddress());
		values.put(DBHelper.KEY_DESCRIPTION, place.getmDescription());
		values.put(DBHelper.KEY_LATITUDE, place.getmLatitude());
		values.put(DBHelper.KEY_LOGITUDE, place.getmLongitude());
		values.put(DBHelper.KEY_MENU, place.getmMenu());
		values.put(DBHelper.KEY_SPECIAL_MENU, place.getmSpecialMenu());
		values.put(DBHelper.KEY_OPEN_TIME, place.getmDailyOpenTime());
		values.put(DBHelper.KEY_CLOSE_DAY, place.getmCloseDay());
		values.put(DBHelper.KEY_PHOTO, place.getmImage());

		long inserted = db.insert(DBHelper.TABLE_NAME, null, values);
		close();
		return inserted;
	}

	// delete data form database.
	public boolean deleteData(Integer eId) {
		this.open();
		try {
			db.delete(DBHelper.TABLE_NAME, DBHelper.KEY_ID + "=" + eId, null);
		} catch (Exception ex) {
			Log.e("ERROR", "data not deleted");
			return false;
		}
		this.close();
		return true;
	}

	// update database by Id
	public long updateData(Integer id, Profile place) {
		open();
		ContentValues values = new ContentValues();
		values.put(DBHelper.KEY_NAME, place.getmName());
		values.put(DBHelper.KEY_ADDRESS, place.getmAddress());
		values.put(DBHelper.KEY_DESCRIPTION, place.getmDescription());
		values.put(DBHelper.KEY_LATITUDE, place.getmLatitude());
		values.put(DBHelper.KEY_LOGITUDE, place.getmLongitude());
		values.put(DBHelper.KEY_MENU, place.getmMenu());
		values.put(DBHelper.KEY_SPECIAL_MENU, place.getmSpecialMenu());
		values.put(DBHelper.KEY_OPEN_TIME, place.getmDailyOpenTime());
		values.put(DBHelper.KEY_CLOSE_DAY, place.getmCloseDay());
		long updated = 0;
		try {
			updated = db.update(DBHelper.TABLE_NAME, values, DBHelper.KEY_ID
					+ "=" + id, null);
		} catch (Exception ex) {
			Log.e("ERROR", "data upgraion problem");
		}
		close();
		return updated;
	}

	// Getting All place list
	public ArrayList<Profile> getPlaceList() {
		ArrayList<Profile> profile_list = new ArrayList<Profile>();
		open();
		Cursor cursor = db.query(DBHelper.TABLE_NAME, null, null, null, null,
				null, null);

		// looping through all rows and adding to list
		if (cursor != null && cursor.getCount() > 0) {
			cursor.moveToFirst();
			for (int i = 0; i < cursor.getCount(); i++) {
				int id = cursor.getInt(cursor.getColumnIndex(DBHelper.KEY_ID));
				String name = cursor.getString(cursor
						.getColumnIndex(DBHelper.KEY_NAME));
				String address = cursor.getString(cursor
						.getColumnIndex(DBHelper.KEY_ADDRESS));
				String description = cursor.getString(cursor
						.getColumnIndex(DBHelper.KEY_DESCRIPTION));
				String latitude = cursor.getString(cursor
						.getColumnIndex(DBHelper.KEY_LATITUDE));
				String longitude = cursor.getString(cursor
						.getColumnIndex(DBHelper.KEY_LOGITUDE));
				String menu = cursor.getString(cursor
						.getColumnIndex(DBHelper.KEY_MENU));
				String specialMenu = cursor.getString(cursor
						.getColumnIndex(DBHelper.KEY_SPECIAL_MENU));
				String dailyopentime = cursor.getString(cursor
						.getColumnIndex(DBHelper.KEY_OPEN_TIME));
				String closeday = cursor.getString(cursor
						.getColumnIndex(DBHelper.KEY_CLOSE_DAY));
				String photo = cursor.getString(cursor
						.getColumnIndex(DBHelper.KEY_PHOTO));

				Profile mProfile = new Profile(id, name, address, description,
						latitude, longitude, menu, specialMenu, dailyopentime,
						closeday, photo);
				profile_list.add(mProfile);
				cursor.moveToNext();
			}
		}
		cursor.close();
		db.close();

		// return place list
		return profile_list;
	}

	// Getting All place list
	public Profile getDetail(int id) {
		Profile place_detail;
		open();

		String selectQuery = "SELECT  * FROM " + DBHelper.TABLE_NAME
				+ " WHERE " + DBHelper.KEY_ID + "=" + id;

		Cursor cursor = db.rawQuery(selectQuery, null);
		cursor.moveToFirst();
		String name = cursor
				.getString(cursor.getColumnIndex(DBHelper.KEY_NAME));
		String address = cursor.getString(cursor
				.getColumnIndex(DBHelper.KEY_ADDRESS));
		String description = cursor.getString(cursor
				.getColumnIndex(DBHelper.KEY_DESCRIPTION));
		String latitude = cursor.getString(cursor
				.getColumnIndex(DBHelper.KEY_LATITUDE));
		String longitude = cursor.getString(cursor
				.getColumnIndex(DBHelper.KEY_LOGITUDE));
		String menu = cursor
				.getString(cursor.getColumnIndex(DBHelper.KEY_MENU));
		String specialMenu = cursor.getString(cursor
				.getColumnIndex(DBHelper.KEY_SPECIAL_MENU));
		String opentime = cursor.getString(cursor
				.getColumnIndex(DBHelper.KEY_OPEN_TIME));
		String closeday = cursor.getString(cursor
				.getColumnIndex(DBHelper.KEY_CLOSE_DAY));
		String photo = cursor.getString(cursor
				.getColumnIndex(DBHelper.KEY_PHOTO));

		place_detail = new Profile(id, name, address, description, latitude,
				longitude, menu, specialMenu, opentime, closeday, photo);

		cursor.moveToNext();

		cursor.close();
		db.close();

		// return place detail
		return place_detail;
	}

	public boolean isEmpty() {
		this.open();
		Cursor cursor = db.query(DBHelper.TABLE_NAME, new String[] {
				DBHelper.KEY_ID, DBHelper.KEY_NAME, DBHelper.KEY_ADDRESS,
				DBHelper.KEY_DESCRIPTION, DBHelper.KEY_LATITUDE,
				DBHelper.KEY_LOGITUDE, DBHelper.KEY_MENU,
				DBHelper.KEY_SPECIAL_MENU, DBHelper.KEY_OPEN_TIME,
				DBHelper.KEY_CLOSE_DAY }, null, null, null, null, null);

		if (cursor.getCount() == 0) {
			this.close();
			return true;
		} else {
			this.close();
			return false;
		}
	}
}
