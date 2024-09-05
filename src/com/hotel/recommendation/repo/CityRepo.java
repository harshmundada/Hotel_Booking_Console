package com.hotel.recommendation.repo;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import com.hotel.recommendation.config.DbHelper;
import com.hotel.recommendation.model.CityMaster;

public class CityRepo extends DbHelper
{
	 public int generateId()
	  {
		  try
		  {
		   stmp=con.prepareStatement("select max(city_id) from citymaster");
		   r=stmp.executeQuery();
		   if(r.next())
		   {
			   return r.getInt(1)+1;
		   }
		   else
			   return 1;
		    
		  }
		  catch(SQLException e)
		  {
			  System.out.println(e);
			  return -1;
		  }  
	  }
  public boolean isAddCity(CityMaster c)
  {
	  try
	  {
		  stmp=con.prepareStatement("insert into citymaster values(?,?,?)");
		  stmp.setInt(1, c.getCity_id());
		  stmp.setString(2,c.getCity_name());
		  stmp.setInt(3,c.getPincode());
		  int val=stmp.executeUpdate();
		  return val>0?true:false;
	  }
	  catch(SQLException e)
	  {
		  System.out.println(e);
		  return false;
	  }
  }
  public int getCityId(CityMaster c)
  {
	  try
	  {
	   stmp=con.prepareStatement("select city_id from  citymaster where city_name=?");
	   stmp.setString(1,c.getCity_name());
	   r=stmp.executeQuery();
	   if(r.next())
	   {
		   return r.getInt(1);
	   }
	   else
		   return -1;
	  }
	  catch(SQLException e)
	  {
		  System.out.println(e);
		  return -1;   
	  }
	
  }
  public boolean updateCity(CityMaster c)
  {
	  try
	  {
		stmp=con.prepareStatement("update citymaster set city_name=? where city_id=?");
		stmp.setString(1,c.getCity_name());
		stmp.setInt(2, c.getCity_id());
		int val=stmp.executeUpdate();
		return val>0?true:false;
	  }
	  catch(SQLException e)
	  {
		 System.out.println(e);
		 return false;
	  }
  }
  public boolean deleteCity(CityMaster c)
  {
	  try
	  {
		stmp=con.prepareStatement("delete from citymaster where city_name=?");
		stmp.setString(1,c.getCity_name());
		int val=stmp.executeUpdate();
		return val>0?true:false;
	  }
	  catch(SQLException e)
	  {
		  System.out.println(e);
		  return false;
	  }
  }
  public Map<Integer,ArrayList> getAllCity()
  {
	  Map<Integer,ArrayList> map=new LinkedHashMap<>();
	  try
	  {
		stmp=con.prepareStatement("select * from citymaster");
		r=stmp.executeQuery();
		while(r.next())
		{
		  int cid=r.getInt(1);
		  ArrayList al=new ArrayList();
		  al.add(r.getString(2));
		  al.add(r.getInt(3));
		  map.put(cid, al);
		}
		return map;
	  }
	  catch(SQLException e)
	  {
		  System.out.println(e);
		  return null;
	  }
  }
}
