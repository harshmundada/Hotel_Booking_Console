package com.hotel.recommendation.model;

import java.sql.Date;
import java.sql.Time;

public class BookingModel {
 private int Booking_id;
 private int user_id;
 private int hotel_id;
 private Date booking_date;
 private Date checkin_date;
 private Time checkin_time;
 private Time Checkout_time;
 private Date checkout_date;

public BookingModel(int booking_id, int user_id, int hotel_id, Date booking_date, Date checkin_date, Time checkin_time,
		Date checkout_date,Time checkout_time) {
	super();
	Booking_id = booking_id;
	this.user_id = user_id;
	this.hotel_id = hotel_id;
	this.booking_date = booking_date;
	this.checkin_date = checkin_date;
	this.checkin_time = checkin_time;
	Checkout_time = checkout_time;
	this.checkout_date = checkout_date;
}
public Time getCheckin_time() {
	return checkin_time;
}
public void setCheckin_time(Time checkin_time) {
	this.checkin_time = checkin_time;
}
public Time getCheckout_time() {
	return Checkout_time;
}
public void setCheckout_time(Time checkout_time) {
	Checkout_time = checkout_time;
}
public int getBooking_id() {
	return Booking_id;
}
public void setBooking_id(int booking_id) {
	Booking_id = booking_id;
}
public int getUser_id() {
	return user_id;
}
public void setUser_id(int user_id) {
	this.user_id = user_id;
}
public int getHotel_id() {
	return hotel_id;
}
public void setHotel_id(int hotel_id) {
	this.hotel_id = hotel_id;
}
public Date getBooking_date() {
	return booking_date;
}
public void setBooking_date(Date booking_date) {
	this.booking_date = booking_date;
}
public Date getCheckin_date() {
	return checkin_date;
}
public void setCheckin_date(Date checkin_date) {
	this.checkin_date = checkin_date;
}
public Date getCheckout_date() {
	return checkout_date;
}
public void setCheckout_date(Date checkout_date) {
	this.checkout_date = checkout_date;
}
public BookingModel() {
	super();
}
 
}
