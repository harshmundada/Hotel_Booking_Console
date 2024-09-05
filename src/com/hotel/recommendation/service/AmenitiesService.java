package com.hotel.recommendation.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.hotel.recommendation.model.AmenitiesModel;
import com.hotel.recommendation.model.HotelMaster;
import com.hotel.recommendation.repo.AmenitiesRepo;

public class AmenitiesService {
	AmenitiesRepo ar=new AmenitiesRepo();
	  public int generateId()
	  {
		  return ar.generateId();
	  }
	  
	  public Map<Integer,String> GetAllAmenities()
	  {
		  return ar.GetAllAmenities();
	  }
	  public boolean AddHotelAmenities(int hid,List<Integer>al)
	  {
		  return ar.AddHotelAmenities(hid, al);
	  }
	  public boolean addAmenity(AmenitiesModel am)
	  {
		  return ar.addAmenity(am);
	  }
	  public ArrayList<String> getHotelAmenities(HotelMaster h) 
	  {
		  return ar.getHotelAmenities(h);
	  }
}
