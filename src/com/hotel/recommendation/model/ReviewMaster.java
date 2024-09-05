package com.hotel.recommendation.model;

public class ReviewMaster {
 private int rev_id;
 private String rev_text;
 private float rating;
public int getRev_id() {
	return rev_id;
}
public void setRev_id(int rev_id) {
	this.rev_id = rev_id;
}
public String getRev_text() {
	return rev_text;
}
public void setRev_text(String rev_text) {
	this.rev_text = rev_text;
}
public float getRating() {
	return rating;
}
public void setRating(float rating) {
	this.rating = rating;
}
public ReviewMaster(int rev_id, String rev_text, float rating) {
	super();
	this.rev_id = rev_id;
	this.rev_text = rev_text;
	this.rating = rating;
}
 
}
