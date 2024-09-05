package com.hotel.recommendation.service;

import java.util.ArrayList;
import java.util.Map;

import com.hotel.recommendation.model.BookingModel;
import com.hotel.recommendation.repo.BookingRepo;

public class BookingService {
	BookingRepo br=new BookingRepo();
	 public int generateId()
	  {
		 return br.generateId();
	  }
	 public boolean makeBooking(BookingModel b,int room_id,int rooms)
	  {
		 return br.makeBooking(b,room_id,rooms);
	  }
	 public  Map<Integer,ArrayList<String>> GetBooking(int userid)
	  {
		 return br.GetBooking(userid);
	  }
	 public int cancelBooking(int userId,String HotelName)
	  {
		  return br.cancelBooking(userId, HotelName);
	  }
	 public int getBookingId(int userid,String hotelName)
	  {
		 return br.getBookingId(userid, hotelName);
	  }
}
