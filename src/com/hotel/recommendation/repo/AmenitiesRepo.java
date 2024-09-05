package com.hotel.recommendation.repo;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.hotel.recommendation.config.DbHelper;
import com.hotel.recommendation.model.AmenitiesModel;
import com.hotel.recommendation.model.HotelMaster;
import com.hotel.recommendation.model.RoomMaster;

public class AmenitiesRepo extends DbHelper
{
	  public int generateId()
	  {
		  try
		  {
			  stmp=con.prepareStatement("select max(amenity_id)from amenities");
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
	  public Map<Integer,String> GetAllAmenities()
	  {
		 Map<Integer,String>map=new LinkedHashMap<>();
		  try
		  {
			  stmp=con.prepareStatement("select * from amenities order by amenity_id");
			  r=stmp.executeQuery();
			  while(r.next())
			  {
				  map.put(r.getInt(1),r.getString(2));
			  }
			  return map;
		  }
		  catch(SQLException e)
		  {
			  System.out.println(e);
			  return null;
		  }
		  
	  }
	  public boolean AddHotelAmenities(int hid,List<Integer>al)
	  {
		  try
		  {
			  int val=0;
			  stmp=con.prepareStatement("insert into  hotelamenitiesjoin values(?,?)");
			  stmp.setInt(1, hid);
			  for(int i=0;i<al.size();i++)
			  {
				  stmp.setInt(2,al.get(i));
				  val=stmp.executeUpdate();
			  }
			  return val>0?true:false;
		  }
		  catch(SQLException e)
		  {
			  System.out.println(e);
			  return false;
		  }
	  }
	  public boolean addAmenity(AmenitiesModel am)
	  {
		  try
		  {
			stmp=con.prepareStatement("insert into amenities values(?,?)");
			stmp.setInt(1,am.getAmenity_id());
			stmp.setString(2,am.getAmenity_name());
			int val=stmp.executeUpdate();
			return val>0?true:false;
		  }
		  catch(SQLException e)
		  {
			  System.out.println(e);
			  return false;
		  }
	  }
	  public ArrayList<String> getHotelAmenities(HotelMaster h) 
	  {
		  ArrayList<String>al=new ArrayList<>();
		  try
		  {
			  stmp=con.prepareStatement("select a.amenity_name from amenities a inner join hotelamenitiesjoin ha on ha.amenity_id=a.amenity_id inner join hotelmaster h on h.hotel_id=ha.hotel_id where h.hotel_name=?");
			  stmp.setString(1,h.getHotel_name());
			  r=stmp.executeQuery();
			  while(r.next())
			  {
				  al.add(r.getString(1).toLowerCase());
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
