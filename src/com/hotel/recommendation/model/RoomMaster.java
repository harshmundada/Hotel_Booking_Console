package com.hotel.recommendation.model;

public class RoomMaster {
  private int rid;
  private String rtype;
public int getRid() {
	return rid;
}
public void setRid(int rid) {
	this.rid = rid;
}
public String getRtype() {
	return rtype;
}
public void setRtype(String rtype) {
	this.rtype = rtype;
}
public RoomMaster(int rid, String rtype) {
	super();
	this.rid = rid;
	this.rtype = rtype;
}
public RoomMaster()
{
	
}
}
