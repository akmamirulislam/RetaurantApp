package com.ftfl.travellerapp.adapter;

import java.util.ArrayList;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.ftfl.travellerapp.R;
import com.ftfl.travellerapp.model.Profile;

public class CustomAdapter extends ArrayAdapter<Profile> {
	Activity mContext;
	Profile mProfile;
	
	// Profile ArrayList
	ArrayList<Profile> mArrayObject;

	public CustomAdapter(Activity context, ArrayList<Profile> objectArray) {
		super(context, R.layout.list_row, objectArray);
		this.mContext = context;
		this.mArrayObject = objectArray;
	}

	// holder Class to contain inflated xml file elements
	static class ViewHolder {
		public TextView name;
		public TextView address;
		public TextView id;
	}

	// Create ListView row
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		// Get Model object from Array list
		mProfile = mArrayObject.get(position);
		ViewHolder mVHolder = null;

		View rowView = convertView;
		if (convertView == null) {

			// Layout inflater to call external xml layout ()
			LayoutInflater inflater = mContext.getLayoutInflater();

			rowView = inflater.inflate(R.layout.list_row, parent, false);

			mVHolder = new ViewHolder();
			mVHolder.id = (TextView) rowView.findViewById(R.id.tvID);
			mVHolder.name = (TextView) rowView.findViewById(R.id.tvShowName);
			mVHolder.address = (TextView) rowView.findViewById(R.id.tvShowAddress);
			rowView.setTag(mVHolder);
		} else
			mVHolder = (ViewHolder) rowView.getTag();

		mVHolder.id.setText(mProfile.getId().toString());
		mVHolder.name.setText(mProfile.getmName().toString());
		mVHolder.address.setText(mProfile.getmAddress().toString());		

		return rowView;
	}
}
