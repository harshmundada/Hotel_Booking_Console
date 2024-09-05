package com.hotel.recommendation.config;

import java.sql.*;
public class DbHelper 
{
   protected DbConfig dbobj=DbConfig.getDbConfig();
   protected Connection con=DbConfig.getCon();
   protected PreparedStatement stmp=DbConfig.getStmp();
   protected ResultSet r=DbConfig.getR();
}