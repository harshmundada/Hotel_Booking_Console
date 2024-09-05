package com.hotel.recommendation.service;

import java.util.ArrayList;

import com.hotel.recommendation.model.AreaMaster;
import com.hotel.recommendation.model.HotelMaster;
import com.hotel.recommendation.model.RoomMaster;
import com.hotel.recommendation.repo.HotelRepo;

public class HotelService {
	HotelRepo hr=new HotelRepo();
	public int generateId()
	  {
	  return hr.generateId();
	  }
	public boolean isAddHotel(HotelMaster h)
	   {
		return hr.isAddHotel(h);
	   }
	 public int getHotelId(HotelMaster h)
	   {
		 return hr.getHotelId(h);
	   }
	 public boolean updateHotel(HotelMaster h)
	   {
		 return hr.updateHotel(h);
	   }
	  public boolean removeHotel(int hid)
	   {
		  return hr.removeHotel(hid);
	   }
	  public ArrayList<HotelMaster> getAllHotels(HotelMaster h)
	   {
		  return hr.getAllHotels(h);
	   }
	  public ArrayList<HotelMaster> getHotelByRoom(HotelMaster h,RoomMaster rm,int count)
	   {
		  return hr.getHotelByRoom(h, rm, count);
	   }
}
