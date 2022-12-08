package com.servlet;

import java.io.*;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.senior_dao;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.pojo.Senior_pojo;

/**
 * Servlet implementation class Senior_add
 */
@WebServlet("/Senior_add")
public class Senior_add extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public Senior_add() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		
		
		response.addHeader("Access-Control-Allow-Origin","*");
		response.setContentType("application/json");
		StringBuilder str = new StringBuilder();
		PrintWriter P = response.getWriter();
	
		
		Gson gson = new Gson();
		
		
		InputStream is=request.getInputStream();
		BufferedReader br =new BufferedReader(new InputStreamReader(is));
		char[] ch=new char[128];
		int bytesRead=-1;
		while((bytesRead= br.read(ch))>1)
		{
			str.append(ch,0,bytesRead);
		}	
		
		String body=str.toString();
		JsonParser parser=new JsonParser();
		JsonObject jB=(JsonObject)parser.parse(body);
		
		
		Senior_pojo x = new Senior_pojo();
		
		
		
		x.setRoll_no(jB.get("Roll_no").getAsInt());
		
		x.setS_name(jB.get("S_Name").getAsString());
		x.setS_year(jB.get("S_year").getAsInt());
		x.setArea_Interest(jB.get("Area_Interest").getAsString());
	
		x.setBranch(jB.get("Branch").getAsString());
		x.setGender(jB.get("Gender").getAsString());
		
		x.setEmail_id(jB.get("email_id").getAsString());
		
		
		senior_dao q = new senior_dao();
		String output="";
		try {
			int a = q.addrow(x);
			if(a!=0)
				output="{Success: \" The row has been succsessfully added\"}";
			else
				output="{Error: \" There is an error\"}";
			
			JsonObject result=(JsonObject)parser.parse(output);
	        P.print(result);
	  
		} 
		catch (SQLException e) {
			response.setStatus(400);
			output="{Error: \" There is an error: "+e.getLocalizedMessage()+"\"}";
			JsonObject result=(JsonObject)parser.parse(output);
	        P.print(result);
		}
			
		
	}

}
