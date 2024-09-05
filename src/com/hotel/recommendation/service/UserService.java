package com.hotel.recommendation.service;

import com.hotel.recommendation.model.UserModel;
import com.hotel.recommendation.repo.UserRepo;

public class UserService
{
	UserRepo ur=new UserRepo();
  public boolean addUser(UserModel um)
  {
	  return ur.addUser(um);
  }
  public boolean checkUser(String email)
  {
	  return ur.checkUser(email);
  }
  public int checkLogin(String email,String password)
  {
	 return ur.checkLogin(email, password);
  }
  public boolean updateInfo(String loginemail,String name,String email,String password,String contact)
  {
	   return ur.updateInfo(loginemail,name,email,password,contact);
  }
  public boolean deleteAccount(String email)
  {
	  return ur.deleteAccount(email);
  }
  public boolean updatePassword(String email,String pass)
  {
	  return ur.updatePassword(email, pass);
  }
}