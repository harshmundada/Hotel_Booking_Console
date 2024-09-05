package com.hotel.recommendation.service;

import java.util.ArrayList;
import java.util.Map;

import com.hotel.recommendation.model.HotelMaster;
import com.hotel.recommendation.model.ReviewMaster;
import com.hotel.recommendation.repo.ReviewRepo;

public class ReviewService {
   ReviewRepo rr=new ReviewRepo();
   public int generateId()
   {
	   return rr.generateId();
   }
   public boolean AddHotelReview(ReviewMaster r,HotelMaster h,int uid)
	 {
	   return rr.AddHotelReview(r, h, uid);
	 }
   public Map<Integer,ArrayList<String>> getHotelreview(HotelMaster h) 
	 {
	   return rr.getHotelreview(h);
	 }
}
