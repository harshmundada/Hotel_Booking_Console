package com.hotel.recommendation.model;

public class AmenitiesModel
{
   private int amenity_id;
   private String amenity_name;
public int getAmenity_id() {
	return amenity_id;
}
public void setAmenity_id(int amenity_id) {
	this.amenity_id = amenity_id;
}
public String getAmenity_name() {
	return amenity_name;
}
public void setAmenity_name(String amenity_name) {
	this.amenity_name = amenity_name;
}
public AmenitiesModel(int amenity_id, String amenity_name) {
	super();
	this.amenity_id = amenity_id;
	this.amenity_name = amenity_name;
}
   
}
