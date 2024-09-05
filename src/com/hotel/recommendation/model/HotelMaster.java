package com.hotel.recommendation.model;

public class HotelMaster extends AreaMaster {
  private int hotel_id;
  private String Hotel_name;
  private String Hotel_Address;
  private String Hotel_email;
  private String hotel_contact;
  private float rating;
public float getRating() {
	return rating;
}
public void setRating(float rating) {
	this.rating = rating;
}
public String getHotel_email() {
	return Hotel_email;
}
public void setHotel_email(String hotel_email) {
	Hotel_email = hotel_email;
}
public HotelMaster()
{
	
}
public HotelMaster(int hotel_id,String hotel_name, String hotel_Address, String hotel_email, String hotel_contact) {
	super();
	this.hotel_id=hotel_id;
	Hotel_name = hotel_name;
	Hotel_Address = hotel_Address;
	Hotel_email = hotel_email;
	this.hotel_contact = hotel_contact;
}
public String getHotel_contact() {
	return hotel_contact;
}
public void setHotel_contact(String hotel_contact) {
	this.hotel_contact = hotel_contact;
}
public int getHotel_id() {
	return hotel_id;
}
public void setHotel_id(int hotel_id)
{
	this.hotel_id = hotel_id;
}
public String getHotel_name() {
	return Hotel_name;
}
public void setHotel_name(String hotel_name) {
	Hotel_name = hotel_name;
}
public String getHotel_Address() {
	return Hotel_Address;
}
public void setHotel_Address(String hotel_Address) {
	Hotel_Address = hotel_Address;
}
}
