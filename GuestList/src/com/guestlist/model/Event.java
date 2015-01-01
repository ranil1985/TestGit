package com.guestlist.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Event implements Parcelable {
	
	private int id;
	private String name;
	private String venue;
	private String date;
	
	public Event(String nam,String pass){
		name=nam;
		venue=pass;
		
		}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getVenue() {
		return venue;
	}
	public void setVenue(String venue) {
		this.venue = venue;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
	
	public Event(Parcel in){
		String[] data= new String[3];
		 
		in.readStringArray(data);
		this.name= data[0];
		this.venue= data[1];
		this.date= (data[2]);
		}
	
	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public void writeToParcel(Parcel dest, int flags) {

		dest.writeStringArray(new String[]{this.name,this.venue,this.date});
		
	}
	
	public static final Parcelable.Creator<Event> CREATOR= new Parcelable.Creator<Event>() {
		 
		@Override
		public Event createFromParcel(Parcel source) {
		// TODO Auto-generated method stub
		return new Event(source);  //using parcelable constructor
		}
		
		@Override
		public Event[] newArray(int size) {
		// TODO Auto-generated method stub
		return new Event[size];
		}
	};
	

}
