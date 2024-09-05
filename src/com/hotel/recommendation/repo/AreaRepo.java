package com.hotel.recommendation.repo;

import java.sql.SQLException;
import java.util.*;

import com.hotel.recommendation.config.DbHelper;
import com.hotel.recommendation.model.AreaMaster;
import com.hotel.recommendation.model.CityMaster;

import java.sql.*;

public class AreaRepo extends DbHelper
{
  public int getCityAreaId(AreaMaster a)
  {
	  try
	  {
		stmp=con.prepareStatement(" select a.area_id from citymaster c inner join cityareajoin cm on c.city_id=cm.city_id inner join areamaster a on a.area_id=cm.area_id where a.area_name=? and c.city_id=?");
		stmp.setString(1,a.getArea_name());
		stmp.setInt(2, a.getCity_id());
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
  public int getAreaId(AreaMaster a)
  {
	  try
	  {
	   stmp=con.prepareStatement("select area_id from  areamaster where area_name=?");
	   stmp.setString(1,a.getArea_name());
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
  public int generateId()
  {
	  try
	  {
	   stmp=con.prepareStatement("select max(area_id) from areamaster");
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
  public boolean isAddArea(AreaMaster a)
  {
	  try
	  {
		 CallableStatement cs=con.prepareCall("call adddemo(?,?,?)");
		 cs.setInt(1, a.getCity_id());
		 cs.setInt(2,a.getArea_id());
		 cs.setString(3,a.getArea_name());
		boolean flag= cs.execute();
		return flag; 
	  }
	  catch(SQLException e)
	  {
		  System.out.println(e);
		  return true;
	  }
  }
  public boolean isAddJoin(AreaMaster a)
  {
	  try
	  {
		 stmp=con.prepareStatement("insert into cityareajoin values(?,?)");
		 stmp.setInt(1, a.getCity_id());
		 stmp.setInt(2, a.getArea_id());
		 int val=stmp.executeUpdate();
		 return val>0?true:false;
	  }
	  catch(SQLException e)
	  {
		  System.out.println(e);
		  return false;
	  }
  }
  public boolean updateArea(AreaMaster a)
  {
	  try
	  {
		stmp=con.prepareStatement("update areamaster set area_name=? where area_id=?");
		stmp.setString(1,a.getArea_name());
		stmp.setInt(2, a.getArea_id());
		int val=stmp.executeUpdate();
		return val>0?true:false;
	  }
	  catch(SQLException e)
	  {
		 System.out.println(e);
		 return false;
	  }
  }
  public boolean deleteArea(AreaMaster a)
  {
	  try
	  {
		  stmp=con.prepareStatement("delete from cityareajoin where city_id=? and area_id=?");
		  stmp.setInt(1, a.getCity_id());
		  stmp.setInt(2, a.getArea_id());
		  int val=stmp.executeUpdate();
		  return val>0?true:false;
	  }
	  catch(SQLException e)
	  {
		 System.out.println(e);
		 return false;
	  }
  }
  public List<String> getParticular(CityMaster c)
  {
	try
	{
		stmp=con.prepareStatement("select a.area_name from areamaster a inner join cityareajoin ca on ca.area_id=a.area_id inner join citymaster c on c.city_id=ca.city_id where c.city_name=?");
	     stmp.setString(1, c.getCity_name());
	     r=stmp.executeQuery();
	     ArrayList<String>al =new ArrayList<>();
	     while(r.next())
	     {
	    	 al.add(r.getString(1));
	     }
	     return al;
	}
	catch(SQLException e)
	  {
		 System.out.println(e);
		 return null;
	  }
  }
  public ArrayList<String> getAreaByCity(CityMaster c)
  {
	  ArrayList<String> al=new ArrayList<>();
	  try
	  {
		  stmp=con.prepareStatement(" select a.area_name from citymaster c inner join cityareajoin cj on c.city_id=cj.city_id inner join areamaster a on a.area_id=cj.area_id where c.city_name=?");
		  stmp.setString(1, c.getCity_name());
		  r=stmp.executeQuery();
		 while(r.next())
		 {
			 al.add(r.getString(1));
		 }
		 return al;
	  }
	  catch(SQLException e)
	  {
		  System.out.println(e);
		  return null;
	  }
  }
}
