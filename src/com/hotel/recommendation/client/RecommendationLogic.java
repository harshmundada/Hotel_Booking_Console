package com.hotel.recommendation.client;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;

import com.hotel.recommendation.model.AreaMaster;
import com.hotel.recommendation.model.BookingModel;
import com.hotel.recommendation.model.CityMaster;
import com.hotel.recommendation.model.HotelMaster;
import com.hotel.recommendation.model.RoomMaster;

public class RecommendationLogic extends ClientAppliaction {
	int count=0;
	int rooms=0;
	 int rid;
	   boolean mflag=false;
	   boolean bflag=false;
	Scanner sc=new Scanner(System.in);
   public void recommend()
   {
	   
	   System.out.println("enter city you want to check hotel for");
		String city=sc.nextLine();
		CityMaster c=new CityMaster();
		c.setCity_name(city);
		int cid=cs.getCityId(c);
		if(cid!=-1)
		{
		 ArrayList<String>areas=as.getAreaByCity(c);
		 if(areas.size()>0)
		 {
			areas.forEach(s->System.out.println(s));
			System.out.println("enter area name");
			String areaname=sc.nextLine();
			AreaMaster a=new AreaMaster();
			a.setArea_name(areaname);
			a.setCity_id(cid);
			int caid=as.getCityAreaId(a);
			if(caid!=-1)
			{
				 HotelMaster h=new HotelMaster();
				h.setArea_name(areaname);
				h.setCity_name(city);
				ArrayList<HotelMaster>al=hs.getAllHotels(h);
				if(al.size()>0)
				{
			     System.out.println("enter room type and no of rooms you want");
			     Map<Integer,String>map=rs.GetAllRooms();
				    Set<Map.Entry<Integer,String>>s=map.entrySet();
				    System.out.println("RoomId\t\tRoomType");
				    System.out.println("|-------|--------------------------|");
				    for(Map.Entry<Integer, String>it:s)
				    {
				    	System.out.println("|"+it.getKey()+"\t|"+it.getValue());
				    	System.out.println("|-------|--------------------------|");
				    }
				    System.out.println("enter room id");
				      rid=sc.nextInt();
				    if(rid>s.size())
				    {
				    	System.out.println("enter valid details");
				    }
				    else
				    {
				    	System.out.println("enter number of rooms");
				      rooms=sc.nextInt();
				      sc.nextLine();
				      RoomMaster rm=new RoomMaster();
				      rm.setRid(rid);
				      al=hs.getHotelByRoom(h, rm, rooms);
				      if(al.size()>0)
				      {
				    	 System.out.println("enter amenities you want");
				    	 map=ams.GetAllAmenities();
						    s=map.entrySet();
						    System.out.println("AmenityId\tAmenityName");
						    System.out.println("|-------|--------------------------|");
						    for(Map.Entry<Integer, String>it:s)
						    {
						    	System.out.println("|"+it.getKey()+"\t|"+it.getValue());
						    	System.out.println("|-------|--------------------------|");
						    }
						   ArrayList<String> userAmenities=new ArrayList<>();
						   String res="yes";
						   do
						   {
							   System.out.println("enter amenity name");
							  String amenity=sc.nextLine().toLowerCase();
							  userAmenities.add(amenity);
							  System.out.println("do you want to add more amenities");
							  res=sc.nextLine();
						   }
						   while(res.equalsIgnoreCase("yes"));
				    	 Map<HotelMaster,ArrayList<String>>finalmap=new LinkedHashMap<>();
				    	 al.forEach(hotel->
				    	 {
				    	 ArrayList<String> am=ams.getHotelAmenities(hotel);
				    	 finalmap.put(hotel, am);
				    	 });
				    	 cosineSimilarity(finalmap,userAmenities);
				      }
				      else
				      {
				    	  System.out.println("no hotels found");
				    	  return;
				      }
				    }
				}
				else
				{
					System.out.println("no hotels found");
					return;
				}
		    }	
			else
				
				System.out.println("enter valid area name");
		    }
		 else
		 {
			 System.out.println("no area found in this city");
			 return;
		 }
		}
		else
		System.out.println("city not found");
   }
   public void cosineSimilarity(Map<HotelMaster,ArrayList<String>>finalmap,ArrayList<String> userAmenities)
   {
	   
	   Map<HotelMaster,Integer>similaritymap=new LinkedHashMap<>();
	   Set<Map.Entry<HotelMaster,ArrayList<String>>>finalset=finalmap.entrySet();
	    for(Map.Entry<HotelMaster,ArrayList<String>>it:finalset)
	    {
	    	HotelMaster h=it.getKey();
	    	ArrayList<String>al=it.getValue();
	    	count=0;
	    	userAmenities.forEach(str->
	    	{
	    		if(al.contains(str))
	    			count++;
	    			
	    	});
	    	similaritymap.put(h, count);
	    }
	    displayRecommendation(similaritymap);
   }
   public void displayRecommendation(Map<HotelMaster,Integer>similaritymap)
   {
	   boolean flag=true;
	  Set<Entry<HotelMaster,Integer>>set=similaritymap.entrySet();
	    List<Entry<HotelMaster,Integer>>al=new ArrayList<>(set);
	    for(int i=0;i<al.size();i++)
	    {
	    	for(int j=i+1;j<al.size();j++)
	    	{

		    	Entry<HotelMaster,Integer>e=al.get(i);
		    	HotelMaster h=e.getKey();
		    	int score=e.getValue();
	    		Entry<HotelMaster,Integer>e1=al.get(j);
		    	HotelMaster h1=e1.getKey();
		    	int score1=e1.getValue();
		    	if(score<score1)
		    	{
		    		Entry<HotelMaster,Integer> e2=al.get(i);
		    		al.set(i, e1);
		    		al.set(j,e2);
		    	}
		    	else if(score==score1)
		    	{
		    	  float r1=h.getRating();
		    	  float r2=h1.getRating();
		    	  if(r1<r2)
		    	  {
		    		  Entry<HotelMaster,Integer> e2=al.get(i);
			    		al.set(i, e1);
			    		al.set(j,e2); 
		    	  }	  
		    	}
		    	else
		    		continue;
	    	}
	    }
	     for(int i=0;i<al.size();i++)
	     {
	    	 Entry<HotelMaster,Integer>entry=al.get(i);
	    	 if(i==0)
	    	 {
	    		 System.out.println("*****The Perfect Matches For You***************");
	    	 }
	    	 if(i>0 && flag)
	    	 {
	    		 System.out.println("***********you can also check out following************");
	    		 flag=false;
	    	 }
	    	HotelMaster h1=entry.getKey();
			System.out.println("hotel_name    :"+h1.getHotel_name());
			System.out.println("hotel_Address :"+h1.getHotel_Address());
			System.out.println("hotel_email   :"+h1.getHotel_email());
			System.out.println("hotel_contact :"+h1.getHotel_contact());
			System.out.println("hotel rating  :"+h1.getRating());
			System.out.println("Amenties Match:"+entry.getValue());
			System.out.println("-----------------------------------------------");
	     }
	     MoreInfo(al);
   }
   public void MoreInfo( List<Entry<HotelMaster,Integer>>al)
   {
	   int choice;
	   do
	   {
	   System.out.println("1.Get More Info About These Hotels");
	   System.out.println("2.Book Any Of These Hotels");
	   System.out.println("3.exit");
	   System.out.println("enter your choice");
	   choice=sc.nextInt();
	   switch(choice)
	   {
	   case 1:
		   System.out.println("enter hotel name");
		   sc.nextLine();
		   String hname=sc.nextLine();
		   al.forEach(entry->
		   {
			   HotelMaster h1=entry.getKey();
			   if(h1.getHotel_name().equalsIgnoreCase(hname))
			   {
				   System.out.println();
				   System.out.println();
				 mflag=true;
				     System.out.println("hotel_name   :"+h1.getHotel_name());
					System.out.println("hotel_Address :"+h1.getHotel_Address());
					System.out.println("hotel_email   :"+h1.getHotel_email());
					System.out.println("hotel_contact :"+h1.getHotel_contact());
					System.out.println("hotel rating  :"+h1.getRating());
					System.out.println("Amenties Match:"+entry.getValue());
					System.out.println();
					System.out.println("this hotel has following rooms and thier prices");
					System.out.println("\n");
				   Map<String,ArrayList<Integer>> hotelrooms=rs.getHotelRoom(h1);
				   Set<Map.Entry<String, ArrayList<Integer>>>hotelset=hotelrooms.entrySet();
				   System.out.println(" Room Type\t\tRoom Price\tAvalible Rooms");
				   System.out.println("------------------------------------------------------");
				   for(Map.Entry<String, ArrayList<Integer>>it:hotelset)
				   {
					   System.out.print(it.getKey()+"\t");
					   ArrayList<Integer>al2=it.getValue();
					   al2.forEach(i->System.out.print(i+"\t\t"));
					   System.out.println();
					   System.out.println("------------------------------------------------------");
				   }
				   System.out.println("\n\nthis hotel has following Amentites");
				   System.out.println("\n");
				   ArrayList<String> am=ams.getHotelAmenities(h1);
				   am.forEach(str->
				   {
					   System.out.println(str);
					   System.out.println("--------------------");
				   });
				   
				   System.out.println("do you want to view others review on this hotel");
				   String rans=sc.nextLine();
				   if(rans.equalsIgnoreCase("yes"))
				   {
					 Map<Integer,ArrayList<String>> hotelreview=rrvs.getHotelreview(h1);
					 if(hotelreview.size()==0)
						 System.out.println("no reviews found");
					 else
					 {
					 Set<Map.Entry<Integer,ArrayList<String>>> reviewset=hotelreview.entrySet();
					 System.out.println("\n");
					 for(Map.Entry<Integer,ArrayList<String>> it:reviewset)
					 {
						int userid=it.getKey();
						ArrayList<String> review=it.getValue();
						 System.out.println("User Id: "+userid+"    User Name  :"+review.get(0)+"\n");
						 System.out.println("Review : "+review.get(1)+"\n");
						 System.out.println("Rating : "+review.get(2)+"   Review Date:"+review.get(3));
						 System.out.println("------------------------------------------------");
					 }
					 }
				   }
				   else
				      return;
				   return;
			   }
		   });
		   if(mflag==false)
		   {
			   System.out.println("enter valid hotel name");
			   break;
		   }
		   break;
	   case 2:
		    System.out.println("enter hotel name");
		    sc.nextLine();
		    hname=sc.nextLine();
		    al.forEach(entry ->
			   {
				   HotelMaster h1=entry.getKey();
				   if(h1.getHotel_name().equalsIgnoreCase(hname))
				   {
					  bflag=true;
					  Date booking_Date = new Date(System.currentTimeMillis());
					  System.out.println("enter checkin date yyyy-mm-dd");
					  String checkin=sc.nextLine();
					  Date checking_date=Date.valueOf(checkin);
					  System.out.println("enter checkin time hh:mm:ss");
					  String time=sc.nextLine();
					  Time checkin_time=Time.valueOf(time);
					  System.out.println("enter checkout date yyyy-mm-dd");
					  String checkout=sc.nextLine();
					  Date checkout_date=Date.valueOf(checkout);
					  System.out.println("enter checkout time hh:mm:ss");
					  String ctime=sc.nextLine();
					  Time checkout_time=Time.valueOf(ctime);
					  int bid=bs.generateId();
					  BookingModel b=new BookingModel(bid,userid,h1.getHotel_id(),booking_Date,checking_date,checkin_time,checkout_date,checkout_time);
					  boolean flag=bs.makeBooking(b,rid,rooms);
					  if(flag)
						 System.out.println("booking Sucessful");
					  else
						  System.out.println("Booking failed");
				   }
			   });
		    if(bflag==false)
			   {
				   System.out.println("enter valid hotel name");
				   break;
			   }
	   break;
	   }
	   }
	   while(choice!=3);
   }
}
