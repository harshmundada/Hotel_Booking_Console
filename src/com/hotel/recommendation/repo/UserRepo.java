package com.hotel.recommendation.repo;

import java.sql.SQLException;

import com.hotel.recommendation.config.DbHelper;
import com.hotel.recommendation.model.UserModel;

public class UserRepo extends DbHelper
{
	
   public boolean addUser(UserModel um)
   {
	 try
	 {
		 stmp=con.prepareStatement("insert into usermaster values('0',?,?,?,?)");
		 stmp.setString(1, um.getName());
		 stmp.setString(2,um.getEmail());
		 stmp.setString(3,um.getPassword());
		 stmp.setString(4,um.getContact());
		 int val=stmp.executeUpdate();
		 return val>0?true:false;
	 }
	 catch(SQLException e)
	 {
		 System.out.println(e);
		 return false;
	 }
   }
   public boolean checkUser(String email)
   {
	try
	{
		stmp=con.prepareStatement("select userid from usermaster where useremail=?");
		stmp.setString(1, email);
		r=stmp.executeQuery();
		if(r.next())
			return true;
		else
			return false;
	}
	catch(SQLException e)
	{
		System.out.println(e);
		return false;
	}
   }

   public int checkLogin(String email,String password)
   {
	   try
	   {
		   stmp=con.prepareStatement("select userid from usermaster where useremail=? and password=?");
		   stmp.setString(1, email);
		   stmp.setString(2, password);
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
   public boolean updateInfo(String loginemail,String name,String email,String password,String contact)
   {
	   try
	   {
		stmp=con.prepareStatement("update usermaster set username=?,useremail=?,password=?,contact=? where useremail=?");
		stmp.setString(1, name);
		stmp.setString(2,email);
		stmp.setString(3, password);
		stmp.setString(4, contact);
		stmp.setString(5, loginemail);
		int val=stmp.executeUpdate();
		return val>0?true:false;
	   }
	   catch(SQLException e)
	   {
		   System.out.println();
		   return false;
	   }
   }
   public boolean updatePassword(String email,String pass)
   {
	   try
	   {
		   stmp=con.prepareStatement("update usermaster set password=? where useremail=?");
		   stmp.setString(1, pass);
		   stmp.setString(2,email);
		   int val=stmp.executeUpdate();
		   return val>0?true:false;
	   }
	   catch(SQLException e)
	   {
		   System.out.println(e);
		   return false;
	   }
   }
   public boolean deleteAccount(String email)
   {
	   try
	   {
		   stmp=con.prepareStatement("delete from usermaster where useremail=?");
		   stmp.setString(1, email);
		   int val=stmp.executeUpdate();
             return val>0?true:false;		   
	   }
	   catch(SQLException e)
	   {
		   System.out.println(e);
		   return false;
	   }
   }
	
}
