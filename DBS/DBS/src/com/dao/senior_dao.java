package com.dao;

import java.sql.*;
import java.util.*;

import com.pojo.Senior_pojo;

public class senior_dao {
	//connection with the database
	public static Connection getConnection() 
	{
		 Connection conn =null;
		 String url ="jdbc:mysql://localhost:3306/user";
		 String user = "root";
		 String pass ="hiqlroot#567@";
			
			
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					conn =DriverManager.getConnection(url,user,pass);
				} catch (ClassNotFoundException e) {
					
					e.printStackTrace();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
				return conn;

		}
	//add a new row 
		public int addrow(Senior_pojo obj) throws SQLException
		{
			int B=0;
		
			try 
			{
				 Connection conn = getConnection();
				 String query="INSERT INTO Senior"+ " (Roll_no,S_Name,S_year,Area_Interest,Branch,Gender,email_id) values "+"(?,?,?,?,?,?,?);";
				 PreparedStatement pst = conn.prepareStatement(query);
				
				 pst.setInt(1,obj.getRoll_no());
				 pst.setString(2,obj.getS_Name());
				 pst.setInt(3,obj.getS_year());
				 pst.setString(4,obj.getArea_Interest());
				 pst.setString(5,obj.getBranch());
				 pst.setString(6,obj.getGender());
				 pst.setString(7,obj.getEmail_id());
				
			System.out.println(pst.toString());	 
			B=pst.executeUpdate();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
	       
			return B;
			
		}

}
