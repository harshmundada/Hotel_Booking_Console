package com.hotel.recommendation.client;

import com.hotel.recommendation.config.DbHelper;
import com.hotel.recommendation.model.AmenitiesModel;
import com.hotel.recommendation.model.AreaMaster;
import com.hotel.recommendation.model.CityMaster;
import com.hotel.recommendation.model.HotelMaster;
import com.hotel.recommendation.model.ReviewMaster;
import com.hotel.recommendation.model.RoomMaster;
import com.hotel.recommendation.model.UserModel;
import com.hotel.recommendation.service.AmenitiesService;
import com.hotel.recommendation.service.AreaService;
import com.hotel.recommendation.service.BookingService;
import com.hotel.recommendation.service.CityService;
import com.hotel.recommendation.service.HotelService;
import com.hotel.recommendation.service.ReviewService;
import com.hotel.recommendation.service.RoomService;
import com.hotel.recommendation.service.UserService;

import MessageRepo.EmailDriver;

import java.util.*;
public class ClientAppliaction extends DbHelper
{
	public static UserService us=new UserService();
	public static CityService cs=new CityService();
	public static AreaService as=new AreaService();
	public static HotelService hs=new HotelService();
	public static RoomService rs=new RoomService();
	public static ReviewService rrvs=new ReviewService();
	public static AmenitiesService ams=new AmenitiesService();
	public static boolean flag;
	public static boolean rflag=false;
	public static boolean loginflag=false;
	public static String loginemail;
	public static int userid; 
	public static EmailDriver e=new EmailDriver();
	public static  HotelMaster hmaster;
	public static BookingService bs=new BookingService();
	public static RecommendationLogic r=new RecommendationLogic();
	public static void main(String[] args) throws Exception  
	{
		int choice;
		Scanner sc=new Scanner(System.in);
		do
		{
			System.out.println("Welcome");
			System.out.println("1.User Section");
			System.out.println("2.Admin Section");
			System.out.println("3.exit");
			choice=sc.nextInt();
		switch(choice)
		{
		case 1:
		{
			System.out.println("1.sign in");
			System.out.println("2.log in");
			System.out.println("3.forgot Password");
			int choice1=sc.nextInt();
			switch(choice1)
			{
			case 1:
				System.out.println("enter your name");
				sc.nextLine();
				String name=sc.nextLine();
				System.out.println("enter contact");
				String contact=sc.nextLine();
				System.out.println("enter your email");
				String email=sc.nextLine();
				flag=us.checkUser(email);
				if(flag)
				{
					System.out.println("user alredy exist with this email");
					break;
				}
				else
				{
				System.out.println("enter password");
				String password=sc.next();
				UserModel obj=new UserModel(name,email,password,contact);
				flag=us.addUser(obj);
				if(flag)
				{
					e.sendto=email;
					boolean eflag=e.Welcome();
					if(eflag)
					{
					System.out.println("sign in sucessful");
					loginemail=email;
					loginflag=true;
					userid=us.checkLogin(email, password);
				    }
				}
				else
					System.out.println("sign in failed");
				}
				break;
			case 2:
				System.out.println("enter your email");
				email=sc.next();
				System.out.println("enter your password");
				String password=sc.next();
				userid=us.checkLogin(email, password);
				if(userid!=-1)
				{
					System.out.println("login sucessful");
					loginemail=email;
					loginflag=true;
				}
				else
				{
					System.out.println("login failed");	
				}
				break;
			case 3:
				System.out.println("enter your mail id");
				sc.nextLine();
				email=sc.nextLine();
				if(us.checkUser(email))
			    {
				  e.sendto=email;
				  int otp=e.Otp();
				  if(otp!=-1)
				  {
					System.out.println("otp send sucessfully");
					System.out.println("enter otp");
					int uotp=sc.nextInt();
					if(otp==uotp)
					{
						System.out.println("otp matched");
						System.out.println("enter your updated Password");
						sc.nextLine();
						String upass=sc.nextLine();
						flag=us.updatePassword(email, upass);
						if(flag)
							System.out.println("password upadted sucessfully login again");
						else
							System.out.println("password not updated");
					}
					else
						System.out.println("wrong otp");
					
				  }
				  else
					  System.out.println("otp not send");
				}
				else
					System.out.println("no user with this mail found");	
				break;
			}
				if(loginflag)
				{
					int choice2;
					do
					{
					System.out.println("1.update account details");
					System.out.println("2.remove account");
					System.out.println("3.Add Hotel Review");
					System.out.println("4.get Hotel Recommendation");
					System.out.println("5.View My Bookings");
					System.out.println("6.Cancel Booking");
					System.out.println("7.Log Out");
					choice2=sc.nextInt();
					switch(choice2)
					{
			     case 1:
				System.out.println("enter updated name");
				sc.nextLine();
				String name=sc.nextLine();
				System.out.println("enter updated email");
				String email=sc.nextLine();
				System.out.println("enter updated password");
				String password=sc.nextLine();
				System.out.println("enter updated contact");
				String contact=sc.nextLine();
				flag=us.updateInfo(loginemail,name,email,password,contact);
				if(flag)
					System.out.println("updated sucess");
				else
					System.out.println("update failed");
				break;
		case 2:
			  System.out.println("do you really want to delete your account yes/no");
			  sc.nextLine();
			  rflag=false;
			  String response=sc.nextLine();
			  if(response.equalsIgnoreCase("yes"))
			  {
				  flag=us.deleteAccount(loginemail);
				  loginflag=false;
				  if(flag)
						System.out.println("account deleted sucess");
					else
						System.out.println("deletion failed");
			  }
			  else
				  System.out.println("account not removed");
				case 3:
					System.out.println("enter hotel city name");
					sc.nextLine();
					   String cname=sc.nextLine();
					   
					   System.out.println("enter hotel area name");
						String aname=sc.nextLine();
						 HotelMaster h=new HotelMaster();
						h.setArea_name(aname);
						h.setCity_name(cname);
						ArrayList<HotelMaster>al=hs.getAllHotels(h);
						
						if(al.size()>0)
						{
							System.out.println("displaying all hotels in this area");
							System.out.println("--------------------------------------------");
							al.forEach(h1->
							{
								System.out.println("hotel_id-> "+h1.getHotel_id());
								System.out.println("hotel_name-> "+h1.getHotel_name());
								System.out.println("hotel_Address-> "+h1.getHotel_Address());
								System.out.println("hotel_email-> "+h1.getHotel_email());
								System.out.println("hotel_contact-> "+h1.getHotel_contact());
								System.out.println("hotel rating-> "+h1.getRating());
								
								System.out.println("--------------------------------------------");
							});
							System.out.println("enter hotel name");
							 name=sc.nextLine();
							al.forEach(h2->
							{
							   if(h2.getHotel_name().equals(name))
							   {
								   rflag=true;
								   int bid=bs.getBookingId(userid, name);
								   if(bid==-1)
								   {
									   System.out.println("you never Stayed in this hotel you cant add review for this hotel\n");
									   return;
								   }
								   else
								   {
								     hmaster=h2;
								   System.out.println("enter your review");
								   String review=sc.nextLine();
								   System.out.println("how much rating you want to give out of 5");
								   float rating=sc.nextInt();
								   int rev_id=rrvs.generateId();
								   ReviewMaster rm=new ReviewMaster(rev_id,review,rating);
								   flag=rrvs.AddHotelReview(rm, hmaster, userid);
								   if(!flag)
									   System.out.println("review added sucessfully");
		
								   else
						             System.out.println("review not added");
								   }
							   }
							});
							if(!rflag) 
								  System.out.println("enter valid hotel name");
						}
						else
						{
							System.out.println("no hotels found");
						}			
					break;
				case 4:
					 r.recommend(); 
					break;
				case 5:
					System.out.println("\n");
					 Map<Integer,ArrayList<String>> bookings=bs.GetBooking(userid);
					 if(bookings.size()>0)
					 {
					 Set<Map.Entry<Integer, ArrayList<String>>>s=bookings.entrySet();
					 for(Map.Entry<Integer, ArrayList<String>>it:s)
					 {
						System.out.println("booking id   : "+it.getKey());
						ArrayList<String>booking=it.getValue();
				        System.out.println("User Name    : "+booking.get(0));
				        System.out.println("Hotel Name   : "+booking.get(1));
				        System.out.println("Booking Date : "+booking.get(2));
				        System.out.println("Checkin Date : "+booking.get(3));
				        System.out.println("Checkin Time : "+booking.get(4));
				        System.out.println("Checkout Date: "+booking.get(5));
				        System.out.println("Checkout Time: "+booking.get(6));
				        System.out.println("-------------------------------------------");
					 }
					 }
					 else
						 System.out.println("No Bookings Found");
					break;
				case 6:
					System.out.println("enter hotel name");
					sc.nextLine();
					String hname=sc.nextLine();
					int result=bs.cancelBooking(userid, hname);
					if(result!=-1)
						System.out.println("booking canceled");
					else
					   System.out.println("no booking found");
					break;
					}
			}
					while(choice2!=7);
		}
		break;
		}
	case 2:
	{
	   System.out.println("1.City Section");
	   System.out.println("2.Area Setion");
	   System.out.println("3.Hotel Section");
	  int choice3=sc.nextInt();
	  switch(choice3)
	  {
	  case 1:
	  {
		  System.out.println("1.Add new City");
		  System.out.println("2.Update City");
		  System.out.println("3.Delete City");
		  System.out.println("4.View City");
		  int choice4=sc.nextInt();
		  switch(choice4)
		  {
		  case 1:
			  System.out.println("enter city name");
			  sc.nextLine();
			  String cname=sc.nextLine();
			  CityMaster c=new CityMaster();
			  c.setCity_name(cname);
			  int cid=cs.getCityId(c);
			  if(cid==-1)
			  {
				     cid=cs.generateId();
					  System.out.println("enter city pincode");
					  int pin=sc.nextInt();
					  sc.nextLine();
					  c.setPincode(pin);
					  c.setCity_id(cid);
					  flag=cs.isAddCity(c);
					  if(flag)
						  System.out.println("city added sucessful");
					  else
						  System.out.println("city not added");
			   }
				  else
				  {
					  System.out.println("city already present");
				  }
			  break;
		  case 2:
			 System.out.println("enter city name you want to update");
			   sc.nextLine();
			   cname=sc.nextLine();
			  c=new CityMaster();
			  c.setCity_name(cname);
			  cid=cs.getCityId(c);
			  if(cid!=-1)
			  {
				  System.out.println("enter updated city name");
				  String newname=sc.nextLine();
				   c=new CityMaster();
				  c.setCity_name(newname);
				  c.setCity_id(cid);
				  flag=cs.updateCity(c);
				  if(flag)
					  System.out.println("city updated sucessful");
				  else
					  System.out.println("city not updated");
				  
			  }
			  else
				  System.out.println("city not present");
			  break;
		  case 3:
			  System.out.println("enter city name you want to delete");
			   sc.nextLine();
			   cname=sc.nextLine();
			  c=new CityMaster();
			  c.setCity_name(cname);
			  cid=cs.getCityId(c);
			  if(cid!=-1)
			  {
				  flag=cs.deleteCity(c);
				  if(flag)
					  System.out.println("city deleted sucessful");
				  else
					  System.out.println("city not deleted"); 
			  }
			  else
				  System.out.println("city not present");
			  break;
		  case 4:
			 Map<Integer,ArrayList> map=cs.getAllCity();
			 if(map.size()>0)
			 {
				 System.out.println("displaying all cities");
				Set<Map.Entry<Integer, ArrayList>>s=map.entrySet();
				System.out.println("----------------------------");
				for(Map.Entry<Integer, ArrayList>it:s)
				{
					System.out.println("city_id-> "+it.getKey());
					ArrayList al=it.getValue();
					System.out.println("city_name-> "+al.get(0));
					System.out.println("city_pin-> "+al.get(1));
					System.out.println("----------------------------------------");
				}
			 }
			 else if(map.size()==0)
			 {
				 System.out.println("no city present");
			 }
			 else
				 System.out.println("Some Error is there");
			  break;
		  }
		  break;
	  }
	  case 2:
	  {
		  System.out.println("1.Add new Area");
		  System.out.println("2.Update Area");
		  System.out.println("3.Delete Area from a city");
		  System.out.println("4.View Areas from a particular city");
		  int choice5=sc.nextInt();
		  switch(choice5)
		  {
		  case 1:
			  System.out.println("enter city name in which you want to add area");
			  sc.nextLine();
			  String cname=sc.nextLine();
			  CityMaster c=new CityMaster();
			  c.setCity_name(cname);
			  int cid=cs.getCityId(c);
			  if(cid!=-1)
			  {
				 System.out.println("enter area name");
				 String aname=sc.nextLine();
				 AreaMaster a=new AreaMaster();
					a.setCity_id(cid);
					a.setArea_name(aname);
					int aid=as.getAreaId(a);
					int caid=as.getCityAreaId(a);
					if(aid==-1)
				    {
					 aid=as.generateId();
					 a.setArea_id(aid);
					 flag=as.isAddArea(a);
						if(flag)
						System.out.println("area not added");
					else
						System.out.println("area added sucessfully");
				   }
					else if(caid==-1)
					{
						a.setArea_id(aid);
					   flag=as.isAddJoin(a);
					   if(flag)
							System.out.println("area added");
						else
							System.out.println("area not added"); 
					}
					else
					{
					 System.out.println("alredy present");	
					}
			  }
			  else
				  System.out.println("this city is not present first add city");
			  break;
		  case 2:
			  System.out.println("enter area name you want to update");
			  sc.nextLine();
			  String aname=sc.nextLine();
			  AreaMaster a=new AreaMaster();
				a.setArea_name(aname);
			  int aid=as.getAreaId(a);
			  if(aid!=-1)
			  {
				  System.out.println("enter new name");
				  aname=sc.nextLine();
				  a.setArea_name(aname);
				  a.setArea_id(aid);
				  flag=as.updateArea(a);
				  if(flag)
					  System.out.println("area name updated sucessfully");
				  else
					  System.out.println("area not updated");
			  }
			  else
				  System.out.println("area not present");
			  break;
		  case 3:
			  System.out.println("enter city name from which you want to delete area");
			  sc.nextLine();
			   cname=sc.nextLine();
			   c=new CityMaster();
			  c.setCity_name(cname);
			  cid=cs.getCityId(c);
			  if(cid!=-1)
			  {
				 System.out.println("enter area name");
				  aname=sc.nextLine();
				   a=new AreaMaster();
					a.setCity_id(cid);
					a.setArea_name(aname);
					 aid=as.getAreaId(a);
					int caid=as.getCityAreaId(a);
					if(aid!=-1)
				    {
						a.setArea_id(aid);
					  caid=as.getCityAreaId(a);
					  if(caid!=-1)
					  {
						 flag=as.deleteArea(a);
						 if(flag)
						  System.out.println("area deleted sucessfully");
					  else
						  System.out.println("area not deleted");
					  }
					  else
						  System.out.println("this area is not in this city");
				    }
					else
					{
					  System.out.println("area not present"); 
					}
			  }
			  else
				  System.out.println("city not present");
			  break;
		  case 4:
			  System.out.println("enter city name");
			  sc.nextLine();
			  cname=sc.nextLine();
			   c=new CityMaster();
			  c.setCity_name(cname);
			  cid=cs.getCityId(c);
			  if(cid!=-1)
			  {
				 List<String>al=as.getParticular(c);
				 if(al.size()>0)
				 {
					 al.forEach(s->System.out.println(s));
				 }
				 else
					 System.out.println("no area found in this city");
			  }
			  else
				  System.out.println("city not present");
			  break;
		  }
		  break;
	  }  
     case 3:
     {
    	 System.out.println("1.Add new Hotel");
		  System.out.println("2.Update Hotel");
		  System.out.println("3.Delete Hotel");
		  System.out.println("4.View Hotels from a particular area");
		  int choice6=sc.nextInt();
		  sc.nextLine();
		  switch(choice6)
		  {
		  case 1:
		  System.out.println("enter city name");
		  String cname=sc.nextLine();
		  CityMaster c=new CityMaster();
		  c.setCity_name(cname);
		  int cid=cs.getCityId(c);
		  if(cid==-1)
		  {
			  System.out.println("this city is not present is database do you want to add it yes/no");
			  String res=sc.nextLine();
			  if(res.equalsIgnoreCase("yes"))
			  {
				  System.out.println("enter city pincode");
				  int pin=sc.nextInt();
				  sc.nextLine();
				  c.setPincode(pin);
				  cs.isAddCity(c);
				  cid=cs.getCityId(c);
			  }
			  else
				  break;
		  }
			System.out.println("enter area name");
			String aname=sc.nextLine();
			AreaMaster a=new AreaMaster();
			a.setCity_id(cid);
			a.setArea_name(aname);
			int aid=as.getAreaId(a);
			if(aid==-1)
			{
				System.out.println("this area is not present do you want to add it yes/no");
				String res=sc.nextLine();
				if(res.equalsIgnoreCase("yes"))
				{
					aid=as.generateId();
					a.setArea_id(aid);
					flag=as.isAddArea(a);
					if(flag)
						System.out.println("area not added");
					else
						System.out.println("area added sucessfully");
				}
				else 
					break;
			}
			a.setArea_id(aid);
			  int caid=as.getCityAreaId(a);
			   if(caid==-1)
			  {
				System.out.println("this area is not avalible in this city do you want to add it yes/no");
				String res=sc.nextLine();
				if(res.equalsIgnoreCase("yes"))
				{
					as.isAddJoin(a);
				}
				else
					break;
			  }   
				 int hid=hs.generateId();
				 float rating=1.0f;
				System.out.println("enter hotel name");
				String hname=sc.nextLine();
				System.out.println("enter hotel address");
				String address=sc.nextLine();
				System.out.println("enter hotel email");
				String hemail=sc.nextLine();
				System.out.println("enter hotel contact number");
				String contact=sc.nextLine();
				HotelMaster h=new HotelMaster(hid,hname,address,hemail,contact);
				h.setArea_id(aid);
				h.setCity_id(cid);
				h.setCity_name(cname);
				h.setArea_name(aname);
				int phid=hs.getHotelId(h);
				if(phid!=-1)
				{
					System.out.println("hotel alredy present");
				}
				else
				{
				System.out.println("enter room types avalible in your hotel their prices and no of rooms avalible");
				Map<Integer,String>map=rs.GetAllRooms();
			    Set<Map.Entry<Integer,String>>s=map.entrySet();
			    System.out.println("RoomId\t\tRoomType");
			    System.out.println("|-------|--------------------------|");
			    for(Map.Entry<Integer, String>it:s)
			    {
			    	System.out.println("|"+it.getKey()+"\t|"+it.getValue());
			    	System.out.println("|-------|--------------------------|");
			    }
			    System.out.println("do you want to add any another room type yes/no");
			    String nres=sc.nextLine();
			    if(nres.equalsIgnoreCase("yes"))
			    {
			     System.out.println("enter room type");
			     String type=sc.nextLine();
			     int rid=rs.generateId();
			     RoomMaster rm=new RoomMaster(rid,type);
			     flag=rs.addRoom(rm);
			     if(flag)
			    	 System.out.println("added sucessfully");
			     else
			    	 System.out.println("room type not added");
			    map=rs.GetAllRooms();
			    s=map.entrySet();
			    System.out.println("RoomId\t\tRoomType");
			    System.out.println("|-------|--------------------------|");
			    for(Map.Entry<Integer, String>it:s)
			    {
			    	System.out.println("|"+it.getKey()+"\t|"+it.getValue());
			    	System.out.println("|-------|--------------------------|");
			    }
			    }
			    String rres="yes";
			    Map<Integer,ArrayList<Integer>> m=new LinkedHashMap<>();
			    do
			    {
			    	System.out.println("enter choice,price and avlible rooms");
			      int rid=sc.nextInt();
			      if(rid>map.size())
			      {
			    	  System.out.println("enter valid room type");
			      }
			      else
			      {
			      int price=sc.nextInt();
			      int avalible=sc.nextInt();
			      ArrayList<Integer>al=new ArrayList<>();
			      al.add(price);
			      al.add(avalible);
			      m.put(rid,al);
			      System.out.println("do you want to add more rooms yes/no");
			      sc.nextLine();
			      rres=sc.nextLine();
			    }
			    }
			    while(rres.equalsIgnoreCase("yes"));
			    System.out.println("enter amenities present in hotel");
				map=ams.GetAllAmenities();
			    s=map.entrySet();
			    System.out.println("AmenityId\tAmenityName");
			    System.out.println("|-------|--------------------------|");
			    for(Map.Entry<Integer, String>it:s)
			    {
			    	System.out.println("|"+it.getKey()+"\t|"+it.getValue());
			    	System.out.println("|-------|--------------------------|");
			    }
			    System.out.println("do you want to add any another Amenity yes/no");
			     nres=sc.nextLine();
			    if(nres.equalsIgnoreCase("yes"))
			    {
			     System.out.println("enter Amenity name");
			     String name=sc.nextLine();
			     int amid=ams.generateId();
			     AmenitiesModel am=new AmenitiesModel(amid,name);
			     flag=ams.addAmenity(am);
			     if(flag)
			    	 System.out.println("amenity added sucessfully");
			     else
			    	 System.out.println("amenity not added");
			    map=ams.GetAllAmenities();
			    s=map.entrySet();
			    System.out.println("AmenityId\tAmenityName");
			    System.out.println("|-------|--------------------------|");
			    for(Map.Entry<Integer, String>it:s)
			    {
			    	System.out.println("|"+it.getKey()+"\t|"+it.getValue());
			    	System.out.println("|-------|--------------------------|");
			    }
			    }
			    rres="yes";
			    ArrayList<Integer>al=new ArrayList<>();
			    do
			    {
			    	System.out.println("enter amenity id");
			      int amid=sc.nextInt();
			      if(amid>map.size())
			      {
			    	  System.out.println("enter valid details");
			      }
			      else
			      {
			      al.add(amid);
			      System.out.println("do you want to add more amenities yes/no");
			      sc.nextLine();
			      rres=sc.nextLine();
			    }
			    }
			    while(rres.equalsIgnoreCase("yes"));
			    System.out.println("do you want to change default rating of hotel yes/no");
			     rres=sc.nextLine();
				if(rres.equals("yes"))
				{
					System.out.println("enter rating");
					 rating=sc.nextFloat();
					 if(rating>5.0f)
					 {
						 do
						 {
						 System.out.println("enter valid raing in between 1 to 5");
                          rating=sc.nextFloat();
						 }
						 while(rating>5.0f);
					 }
				}
				h.setRating(rating);
				flag=hs.isAddHotel(h)&& rs.AddHotelRooms(hid, m)&& ams.AddHotelAmenities(hid, al);
				if(flag)
					System.out.println("hotel added sucessfully");
				else
					System.out.println("hotel not added");
				}
	      break;
		  case 2:
			  System.out.println("enter city name");
			   cname=sc.nextLine();
			   System.out.println("enter area name");
				 aname=sc.nextLine();
				System.out.println("enter hotel name");
				 hname=sc.nextLine();
				System.out.println("enter hotel email");
				 hemail=sc.nextLine();
				 h=new HotelMaster();
				h.setCity_name(cname);
				h.setArea_name(aname);
				h.setHotel_email(hemail);
				h.setHotel_name(hname);
				 hid=hs.getHotelId(h);
				if(hid==-1)
					System.out.println("there is no hotel with this name int this city and in this area");
				else
				{
					System.out.println("enter updated hotel name");
					 hname=sc.nextLine();
					System.out.println("enter updated hotel address");
					address=sc.nextLine();
					System.out.println("enter updated hotel email");
					 hemail=sc.nextLine();
					System.out.println("enter updated hotel contact number");
					 contact=sc.nextLine();
					System.out.println("enter hotel updated rating");
					 rating=sc.nextFloat();
					if(rating>5.0f)
					 {
						 do
						 {
						 System.out.println("enter valid raing in between 1 to 5");
                         rating=sc.nextFloat();
						 }
						 while(rating>5.0f);
					 }
				    h=new HotelMaster(hid,hname,address,hemail,contact);
				    h.setRating(rating);
				    flag=hs.updateHotel(h);
				    if(flag)
				    	System.out.println("hotel updated sucessfully");
				    else
				    	System.out.println("hotal not updated");
				}
			  
			  break;
	  case 3:
		  System.out.println("enter city name");
		   cname=sc.nextLine();
		   System.out.println("enter area name");
			aname=sc.nextLine();
			System.out.println("enter hotel name");
			 hname=sc.nextLine();
			System.out.println("enter hotel email");
			hemail=sc.nextLine();
			 h=new HotelMaster();
			h.setCity_name(cname);
			h.setArea_name(aname);
			h.setHotel_email(hemail);
			h.setHotel_name(hname);
			hid=hs.getHotelId(h);
			if(hid==-1)
				System.out.println("there is no hotel with this name in this city and in this area");
			else
			{
				flag=hs.removeHotel(hid);
				if(flag)
					System.out.println("hotel removed sucessfully");
				else
					System.out.println("hotel not removed");
			}
         break;
	  case 4:
		  System.out.println("enter city name");
		   cname=sc.nextLine();
		   System.out.println("enter area name");
			aname=sc.nextLine();
			 h=new HotelMaster();
			h.setArea_name(aname);
			h.setCity_name(cname);
			ArrayList<HotelMaster>al=hs.getAllHotels(h);
			if(al.size()>0)
			{
				System.out.println("displaying all hotels in this area");
				System.out.println("--------------------------------------------");
				al.forEach(h1->
				{
					System.out.println("hotel_id-> "+h1.getHotel_id());
					System.out.println("hotel_name-> "+h1.getHotel_name());
					System.out.println("hotel_Address-> "+h1.getHotel_Address());
					System.out.println("hotel_email-> "+h1.getHotel_email());
					System.out.println("hotel_contact-> "+h1.getHotel_contact());
					System.out.println("hotel rating is->"+h1.getRating());
					System.out.println("--------------------------------------------");
				});
			}
			else
				System.out.println("no hotels found");
		  break;
	  }
		  break;
   }
   }
	  break;
   }
  }
	}
		while(choice!=3);
}
}
