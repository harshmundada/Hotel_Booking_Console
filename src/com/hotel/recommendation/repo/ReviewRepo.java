package com.hotel.recommendation.repo;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import com.hotel.recommendation.config.DbHelper;
import com.hotel.recommendation.model.HotelMaster;
import com.hotel.recommendation.model.ReviewMaster;

public class ReviewRepo extends DbHelper
{
	 public int generateId()
	  {
		  try
		  {
		   stmp=con.prepareStatement("select max(rev_id) from reviewmaster");
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
	 public boolean AddHotelReview(ReviewMaster rm,HotelMaster h,int uid)
	 {
		 try
		 {
			 float prerating=h.getRating();
			 float currate=(prerating+rm.getRating())/2;
			 CallableStatement c=con.prepareCall("call addHotelreview(?,?,?,?,?,?)");
			 c.setInt(1, rm.getRev_id());
			 c.setString(2,rm.getRev_text());
			 c.setFloat(3,rm.getRating());
			 c.setInt(4,h.getHotel_id());
			 c.setFloat(5,currate);
			 c.setInt(6,uid);
			 return c.execute();
			 
		 }
		 catch(SQLException e)
		 {
			 System.out.println(e);
			 return true;
		 }
	 }
	 public Map<Integer,ArrayList<String>> getHotelreview(HotelMaster h) 
	 {
		 try
		 {
			 stmp=con.prepareStatement("select  u.userid,u.username,r.rev_text,r.rating,r.rev_date from usermaster u inner join hotelreviewjoin rhj on rhj.userid=u.userid inner join hotelmaster h on h.hotel_id=rhj.hotel_id inner join reviewmaster r on r.rev_id=rhj.rev_id where h.hotel_name=?");
			 stmp.setString(1,h.getHotel_name());
			 r=stmp.executeQuery();
			 Map<Integer,ArrayList<String>>map=new LinkedHashMap<>();
			 while(r.next())
			 {
				ArrayList<String>al=new ArrayList<>();
				int userid=r.getInt(1);
				al.add(r.getString(2));
				al.add(r.getString(3));
				al.add(r.getString(4));
				al.add(r.getString(5));
				map.put(userid, al);
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
