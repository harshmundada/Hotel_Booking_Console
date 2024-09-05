package com.hotel.recommendation.service;

import java.util.ArrayList;
import java.util.Map;

import com.hotel.recommendation.model.CityMaster;
import com.hotel.recommendation.repo.CityRepo;

public class CityService {
   CityRepo cr=new CityRepo();
   public boolean isAddCity(CityMaster c)
   {
	  return cr.isAddCity(c); 
   }

	 public int generateId()
	  {
		 return cr.generateId();
	  }
   public int getCityId(CityMaster c)
   {
	 return cr.getCityId(c);
   }
   public boolean updateCity(CityMaster c)
   {
	   return cr.updateCity( c);
   }
   public boolean deleteCity(CityMaster c)
   {
	   return cr.deleteCity(c);
   }
   public Map<Integer,ArrayList> getAllCity()
   {
	   return cr.getAllCity();
   }
}
