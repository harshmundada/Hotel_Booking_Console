package com.hotel.recommendation.service;

import java.util.ArrayList;
import java.util.List;

import com.hotel.recommendation.model.AreaMaster;
import com.hotel.recommendation.model.CityMaster;
import com.hotel.recommendation.repo.AreaRepo;

public class AreaService {
	AreaRepo ar=new AreaRepo();
	public int getCityAreaId(AreaMaster a)
	{
		return ar.getCityAreaId(a);
	}
	public int getAreaId(AreaMaster a)
	 {
           return ar.getAreaId(a);
	  }
	public int generateId()
	  {
		return ar.generateId();
	  }
	public boolean isAddArea(AreaMaster a)
	  {
		return ar.isAddArea(a);
	  }
	public boolean isAddJoin(AreaMaster a)
	  {
		return ar.isAddJoin(a);
	  }
	public boolean updateArea(AreaMaster a)
	  {
		return ar.updateArea(a);
	  }
	public boolean deleteArea(AreaMaster a)
	  {
		return ar.deleteArea(a);
	  }
	  public List<String> getParticular(CityMaster c)
	  {
		 return ar.getParticular(c);
	  }
	  public ArrayList<String> getAreaByCity(CityMaster c)
	  {
		  return ar.getAreaByCity(c);
	  }
}
