package com.hotel.recommendation.model;

public class UserModel {
  private String name;
  private String email;
  private String Password;
  private int userid;
  private String contact; 
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getPassword() {
	return Password;
}
public String getContact() {
	return contact;
}
public void setContact(String contact) {
	this.contact = contact;
}
public void setPassword(String password) {
	Password = password;
}
public int getUserid() {
	return userid;
}
public void setUserid(int userid) {
	this.userid = userid;
}
public UserModel(String name, String email, String password,String contact) {
	this.name = name;
	this.email = email;
	Password = password;
	this.contact=contact;
}
  
}
