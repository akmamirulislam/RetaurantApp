package com.ftfl.travellerapp.databasse;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper {

	// All Static variables
	// Database Version
	private static final int DATABASE_VERSION = 1;

	// Database Name
	private static final String DATABASE_NAME = "restaurantManager";

	// table name
	public static final String TABLE_NAME = "restaurant";

	// Table Columns names
	public static final String KEY_ID = "id";
	public static final String KEY_NAME = "name";
	public static final String KEY_ADDRESS = "address";
	public static final String KEY_DESCRIPTION = "description";
	public static final String KEY_LATITUDE = "latitude";
	public static final String KEY_LOGITUDE = "longitude";
	public static final String KEY_MENU = "menu";
	public static final String KEY_SPECIAL_MENU = "specialMenu";
	public static final String KEY_OPEN_TIME = "dailyopentime";
	public static final String KEY_CLOSE_DAY = "closeday";
	public static final String KEY_PHOTO = "photo";

	// table information
	public String CREATE_TABLE = "create table " + TABLE_NAME + "("
			+ KEY_ID + " integer primary key autoincrement, " + KEY_NAME
			+ " text not null, " + KEY_ADDRESS + " text not null, "
			+ KEY_DESCRIPTION + " text not null, " + KEY_LATITUDE
			+ " text not null, " + KEY_LOGITUDE + " text not null, " + KEY_MENU
			+ " text not null, " + KEY_SPECIAL_MENU + " text not null, "
			+ KEY_OPEN_TIME + " text not null, " + KEY_CLOSE_DAY
			+ " text not null, " + KEY_PHOTO + " text not null);";

	// Create Database
	public DBHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	// Creating Tables
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(CREATE_TABLE);
	}

	// Upgrading database
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.w(DBHelper.class.getName(), "Upgrading database from version "
				+ oldVersion + " to " + newVersion
				+ ", which will destroy all old data");

		db.execSQL("DROP TABLE IF EXISTS " + CREATE_TABLE);
		onCreate(db);
	}

}