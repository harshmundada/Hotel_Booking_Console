package com.hotel.recommendation.repo;

import java.sql.SQLException;

import com.hotel.recommendation.config.DbHelper;
import com.hotel.recommendation.model.AreaMaster;
import com.hotel.recommendation.model.HotelMaster;
import com.hotel.recommendation.model.RoomMaster;

import java.util.*;
public class HotelRepo extends DbHelper{
	 public int generateId()
	  {
		  try
		  {
		   stmp=con.prepareStatement("select max(hotel_id) from hotelmaster");
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
   public boolean isAddHotel(HotelMaster h)
   {
	   try
	   {
		   stmp=con.prepareStatement("insert into hotelmaster values(?,?,?,?,?,?,?,?)");
		   stmp.setInt(1, h.getHotel_id());
		   stmp.setString(2,h.getHotel_name());
		   stmp.setString(3,h.getHotel_Address());
		   stmp.setInt(4, h.getCity_id());
		   stmp.setInt(5, h.getArea_id());
		   stmp.setString(6, h.getHotel_email());
		   stmp.setString(7,h.getHotel_contact());
		   stmp.setFloat(8, h.getRating());
		   int val=stmp.executeUpdate();
		   return val>0?true:false;
	   }
	   catch(SQLException e)
	   {
		   System.out.println(e);
		   return false;
	   }
   }
   public int getHotelId(HotelMaster h)
   {
	   try
	   {
		 stmp=con.prepareStatement("select h.hotel_id from hotelmaster h inner join citymaster c on c.city_id=h.city_id inner join areamaster a on a.area_id=h.area_id where c.city_name=? and a.area_name=? and h.hotel_name=? and h.hotel_email=?"); 
	     stmp.setString(1, h.getCity_name());
	     stmp.setString(2,h.getArea_name());
	     stmp.setString(3, h.getHotel_name());
	     stmp.setString(4,h.getHotel_email());
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
   public boolean updateHotel(HotelMaster h)
   {
	   try
	   {
		   stmp=con.prepareStatement("update hotelmaster set hotel_name=?,hotel_address=?,hotel_email=?,hotel_contact=?,rating=? where hotel_id=?");
		   stmp.setString(1,h.getHotel_name());
		   stmp.setString(2,h.getHotel_Address());
		   stmp.setString(3,h.getHotel_email());
		   stmp.setString(4, h.getHotel_contact());
		   stmp.setFloat(5, h.getRating());
		   stmp.setInt(6,h.getHotel_id());
		   int val=stmp.executeUpdate();
		   return val>0?true:false;
	   }
	   catch(SQLException e)
	   {
		   System.out.println(e);
		   return false;
	   }
   }
   public boolean removeHotel(int hid)
   {
	try
	{
		stmp=con.prepareStatement("delete from hotelmaster where hotel_id=?");
		stmp.setInt(1, hid);
		int val=stmp.executeUpdate();
		return val>0?true:false;
	}
	catch(SQLException e)
	{
		System.out.println(e);
		return false;
	}
   }
   public ArrayList<HotelMaster> getAllHotels(HotelMaster h)
   {
	   ArrayList<HotelMaster> al=new ArrayList<>();
	   try
	   {
		   stmp=con.prepareStatement("select hotel_id,hotel_name,hotel_address,hotel_contact,hotel_email,rating from hotelmaster h inner join citymaster c on c.city_id=h.city_id inner join areamaster a on a.area_id=h.area_id where a.area_name=? and c.city_name=?");
		   stmp.setString(1,h.getArea_name());
		   stmp.setString(2,h.getCity_name());
		   r=stmp.executeQuery();
		   while(r.next())
		   {
			    HotelMaster obj=new HotelMaster();
			    obj.setHotel_id(r.getInt(1));
			    obj.setHotel_name(r.getString(2));
			    obj.setHotel_Address(r.getString(3));
			    obj.setHotel_contact(r.getString(4));
			    obj.setHotel_email(r.getString(5));
			    obj.setRating(r.getFloat(6));
			    al.add(obj);
		   }
		   return al;   
	   }
	   catch(SQLException e)
	   {
		   System.out.println(e);
		   return null;
	   }
   }
   public ArrayList<HotelMaster> getHotelByRoom(HotelMaster h,RoomMaster rm,int count)
   {
	 ArrayList<HotelMaster> al=new ArrayList<>();
	 try
	 {
		 stmp=con.prepareStatement("select h.hotel_id,h.hotel_name,h.hotel_address,h.hotel_contact,h.hotel_email,h.rating from hotelmaster h inner join citymaster c on h.city_id=c.city_id inner join areamaster a on a.area_id=h.area_id inner join hotelroomjoin hj on h.hotel_id=hj.hotel_id where c.city_name=? and a.area_name=? and hj.room_id=?  and hj.avalible_room>=?");
		 stmp.setString(1,h.getCity_name());
		 stmp.setString(2,h.getArea_name());
		 stmp.setInt(3, rm.getRid());
		 stmp.setInt(4,count);
		 r=stmp.executeQuery();
		 while(r.next())
		 {
			 HotelMaster obj=new HotelMaster();
			    obj.setHotel_id(r.getInt(1));
			    obj.setHotel_name(r.getString(2));
			    obj.setHotel_Address(r.getString(3));
			    obj.setHotel_contact(r.getString(4));
			    obj.setHotel_email(r.getString(5));
			    obj.setRating(r.getFloat(6));
			    al.add(obj); 
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
