package com.ftfl.travellerapp.model;

public class Profile {
	Integer mID = 0;
	String mName = "";
	String mAddress = "";
	String mDescription = "";
	String mLatitude = "";
	String mLongitude = "";
	String mMenu = "";
	String mSpecialMenu = "";
	String mDailyOpenTime = "";
	String mCloseDay = "";
	String mImage = "";

	public Profile() {

	}

	public Profile(String eName, String eAddress, String eDescription,
			String eLatitude, String eLongitude, String eMenu,
			String eSpecialMenu, String eDailyOpentime, String eCloseDay,
			String eImage) {
		super();
		this.mName = eName;
		this.mAddress = eAddress;
		this.mDescription = eDescription;
		this.mLatitude = eLatitude;
		this.mLongitude = eLongitude;
		this.mMenu = eMenu;
		this.mSpecialMenu = eSpecialMenu;
		this.mDailyOpenTime = eDailyOpentime;
		this.mCloseDay = eCloseDay;
		this.mImage = eImage;
	}

	public Profile(Integer eid, String eName, String eAddress,
			String eDescription, String eLatitude, String eLongitude,
			String eMenu, String eSpecialMenu, String eDailyOpentime,
			String eCloseDay, String eImage) {
		super();
		this.mID = eid;
		this.mName = eName;
		this.mAddress = eAddress;
		this.mDescription = eDescription;
		this.mLatitude = eLatitude;
		this.mLongitude = eLongitude;
		this.mMenu = eMenu;
		this.mSpecialMenu = eSpecialMenu;
		this.mDailyOpenTime = eDailyOpentime;
		this.mCloseDay = eCloseDay;
		this.mImage = eImage;
	}

	public Integer getId() {
		return mID;
	}

	public void setId(Integer eId) {
		this.mID = eId;
	}

	public String getmName() {
		return mName;
	}

	public void setmName(String eName) {
		this.mName = eName;
	}

	public String getmAddress() {
		return mAddress;
	}

	public void setmAddress(String eAddress) {
		this.mAddress = eAddress;
	}

	public String getmDescription() {
		return mDescription;
	}

	public void setmDescription(String eDescription) {
		this.mDescription = eDescription;
	}

	public String getmLatitude() {
		return mLatitude;
	}

	public void setmLatitude(String eLatitude) {
		this.mLatitude = eLatitude;
	}

	public String getmLongitude() {
		return mLongitude;
	}

	public void setmLongitude(String eLongitude) {
		this.mLongitude = eLongitude;
	}

	public String getmMenu() {
		return mMenu;
	}

	public void setmStartDate(String eMenu) {
		this.mMenu = eMenu;
	}

	public String getmSpecialMenu() {
		return mSpecialMenu;
	}

	public void setmEndDate(String eSpecialMenu) {
		this.mSpecialMenu = eSpecialMenu;
	}

	public String getmDailyOpenTime() {
		return mDailyOpenTime;
	}

	public void setmNote(String eDailyOpenTime) {
		this.mDailyOpenTime = eDailyOpenTime;
	}

	public String getmCloseDay() {
		return mCloseDay;
	}

	public void setmDistrict(String eCloseDay) {
		this.mCloseDay = eCloseDay;
	}

	public String getmImage() {
		return mImage;
	}

	public void setmImage(String eImage) {
		this.mImage = eImage;
	}

}
