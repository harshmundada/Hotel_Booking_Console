package com.hotel.recommendation.repo;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import com.hotel.recommendation.config.DbHelper;
import com.hotel.recommendation.model.HotelMaster;
import com.hotel.recommendation.model.RoomMaster;

public class RoomRepo extends DbHelper
{
	  public int generateId()
	  {
		  try
		  {
			  stmp=con.prepareStatement("select max(room_id)from roomsmaster");
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
	  public Map<Integer,String> GetAllRooms()
	  {
		 Map<Integer,String>map=new LinkedHashMap<>();
		  try
		  {
			  stmp=con.prepareStatement("select * from roomsmaster order by room_id");
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
	  public boolean AddHotelRooms(int hid,Map<Integer,ArrayList<Integer>>map)
	  {
		  try
		  {
			  int val=0;
			  stmp=con.prepareStatement("insert into hotelroomjoin values(?,?,?,?)");
			  stmp.setInt(1, hid);
			  Set<Map.Entry<Integer,ArrayList<Integer>>>s=map.entrySet();
			  for(Map.Entry<Integer,ArrayList<Integer>>it:s)
			  {
				  stmp.setInt(2,it.getKey());
				  ArrayList<Integer>al=it.getValue();
				  stmp.setInt(3,al.get(0));
				  stmp.setInt(4,al.get(1));
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
	  public boolean addRoom(RoomMaster r)
	  {
		  try
		  {
			stmp=con.prepareStatement("insert into roomsmaster values(?,?)");
			stmp.setInt(1,r.getRid());
			stmp.setString(2,r.getRtype());
			int val=stmp.executeUpdate();
			return val>0?true:false;
		  }
		  catch(SQLException e)
		  {
			  System.out.println(e);
			  return false;
		  }
	  }
   public Map<String, ArrayList<Integer>> getHotelRoom(HotelMaster h)
   {
	   Map<String,ArrayList<Integer>> map=new LinkedHashMap<>();
	   try
	   {
		   stmp=con.prepareStatement("select r.room_type,hr.price,hr.avalible_room from roomsmaster r inner join hotelroomjoin hr on hr.room_id=r.room_id inner join hotelmaster h on h.hotel_id=hr.hotel_id where hotel_name=?");
	       stmp.setString(1, h.getHotel_name());
	       r=stmp.executeQuery();
	       while(r.next())
	       {
	    	   String rname=r.getString(1);
	    	   ArrayList<Integer> al=new ArrayList<>();
	    	   al.add(r.getInt(2));
	    	   al.add(r.getInt(3)); 
	    	   map.put(rname, al);
	       }
	       return map;	   
	       }
	   catch(Exception e)
	   {
		   System.out.println(e);
		   return null;
	   }
   }
}
