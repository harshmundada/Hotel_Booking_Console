package com.hotel.recommendation.config;
import java.util.*;
import java.io.*;
public class PathHelper
{
  public static FileInputStream fin;
  public static File F=new File(".");
  public static String name=F.getAbsolutePath().substring(0,F.getAbsolutePath().length()-1)+"src\\";
    static
    {
    	String Name=name+"db.properties";
    	try
    	{
    	  fin=new FileInputStream(Name);
    	}
    	catch(FileNotFoundException e)
    	{
    		System.out.println(e);
    	}
   
    }
}
