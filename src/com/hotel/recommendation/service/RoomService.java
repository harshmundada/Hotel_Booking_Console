package com.hotel.recommendation.service;
import java.util.*;

import com.hotel.recommendation.config.DbHelper;
import com.hotel.recommendation.model.HotelMaster;
import com.hotel.recommendation.model.RoomMaster;
import com.hotel.recommendation.repo.RoomRepo;
public class RoomService 
{
	RoomRepo rr=new RoomRepo();
  public int generateId()
  {
	  return rr.generateId();
  }
  
  public Map<Integer,String> GetAllRooms()
  {
	  return rr.GetAllRooms();
  }
  public boolean AddHotelRooms(int hid,Map<Integer,ArrayList<Integer>>map)
  {
	  return rr.AddHotelRooms(hid, map);
  }
  public boolean addRoom(RoomMaster r)
  {
	  return rr.addRoom(r);
  }
  public Map<String, ArrayList<Integer>> getHotelRoom(HotelMaster h)
  {
	  return rr.getHotelRoom(h);
  }
}
