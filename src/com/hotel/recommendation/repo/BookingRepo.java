package com.hotel.recommendation.repo;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import com.hotel.recommendation.config.DbHelper;
import com.hotel.recommendation.model.BookingModel;

public class BookingRepo extends DbHelper
{
	 public int generateId()
	  {
		  try
		  {
		   stmp=con.prepareStatement("select max(booking_id) from bookingmaster");
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
  public boolean makeBooking(BookingModel b,int room_id,int rooms)
  {
	  try
	  {
		  stmp=con.prepareStatement("insert into bookingmaster values(?,?,?,?,?,?,?,?)");
		  stmp.setInt(1, b.getBooking_id());
		  stmp.setInt(2, b.getUser_id());
		  stmp.setInt(3,b.getHotel_id());
		  stmp.setDate(4,b.getBooking_date());
		  stmp.setDate(5,b.getCheckin_date());
		  stmp.setTime(6,b.getCheckin_time());
		  stmp.setDate(7,b.getCheckout_date());
		  stmp.setTime(8,b.getCheckout_time());
		  int val=stmp.executeUpdate();
	      boolean flag=minusRoom(b,room_id,rooms);
		 return val>0?true:false;
	  }
	  catch(SQLException e)
	  {
		  System.out.println(e);
		  return false;
	  }
  }
  public boolean minusRoom(BookingModel b,int room_id,int rooms) 
  {
	  try
	  {
		  stmp=con.prepareStatement("update hotelroomjoin set avalible_room=avalible_room-? where hotel_id=? and room_id=?");
		  stmp.setInt(1,rooms);
		  stmp.setInt(2,b.getHotel_id());
		  stmp.setInt(3,room_id);
		  int val=stmp.executeUpdate();
		  return val>0?true:false;
	  }
	  catch(SQLException e)
	  {
		  System.out.println(e);
		  return false;
	  }
  }
  public  Map<Integer,ArrayList<String>> GetBooking(int userid)
  {
	  try
	  {
		  Map<Integer,ArrayList<String>> map=new LinkedHashMap<>();
		  stmp=con.prepareStatement(" select u.username,h.hotel_name,b.booking_id,b.booking_date,b.checkin_date,b.checkin_time,b.checkout_date,b.checkout_time from bookingmaster b inner join hotelmaster h on h.hotel_id=b.hotel_id inner join usermaster u on u.userid=b.userid where b.userid=?");
		  stmp.setInt(1, userid);
		  r=stmp.executeQuery();
		  while(r.next())
		  {
			 ArrayList<String> al=new ArrayList<>();
			 al.add(r.getString(1));
			 al.add(r.getString(2));
			 al.add(r.getString(4));
			 al.add(r.getString(5));
			 al.add(r.getString(6));
			 al.add(r.getString(7));
			 al.add(r.getString(8));
			 map.put(r.getInt(3),al);
		  }
		  return map;
	  }
	  catch(SQLException e)
	  {
		  System.out.println(e);
		  return null;
	  }
  }
  public int getBookingId(int userid,String hotelName)
  {
	  try
	  {
		  stmp=con.prepareStatement(" select b.booking_id from bookingmaster b inner join hotelmaster h on h.hotel_id=b.hotel_id inner join usermaster u on u.userid=b.userid where b.userid=? and h.hotel_name=?");
		  stmp.setInt(1, userid);
		  stmp.setString(2,hotelName);
		  r=stmp.executeQuery();
		  if(r.next())
			  return r.getInt(1);
		  else
			  return -1;
	  }
	  catch(SQLException e)
	  {
		  System.out.println(e);
		  return -1;
	  }
  }
  public int cancelBooking(int userId,String HotelName)
  {
	  try
	  {
		int bid=getBookingId(userId,HotelName);
		if(bid!=-1)
		{
			stmp=con.prepareStatement("delete from bookingmaster where booking_id=?");
			stmp.setInt(1, bid);
	        return stmp.executeUpdate();
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
}
