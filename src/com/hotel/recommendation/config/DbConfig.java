package com.hotel.recommendation.config;
import java.util.*;
import java.io.*;
import java.sql.*;
import java.util.*;
public class DbConfig 
{
  private static Connection con;
  private static PreparedStatement stmp;
  private static ResultSet r=null;
  private static DbConfig dbobj;
  private DbConfig()
  {
	  try
	  {
	  Properties p=new Properties();
	  p.load(PathHelper.fin);
	  String dname=p.getProperty("driver");
	  String username=p.getProperty("username");
	  String pass=p.getProperty("password");
	  String url=p.getProperty("url");
	  Class.forName(dname);
	  con=DriverManager.getConnection(url,username,pass);
	  if(con!=null)
	  {
		  System.out.println("database connected");
	  }
	  }
	  catch(Exception e)
	  {
		  System.out.println(e);
	  }
  }
  public static DbConfig getDbConfig()
  {
	  if(dbobj==null)
	  {
		dbobj=new DbConfig();  
	  }
	  return dbobj;
  }
public static Connection getCon() {
	return con;
}
public static PreparedStatement getStmp() {
	return stmp;
}
public static ResultSet getR() {
	return r;
}
  
}
