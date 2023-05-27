package com.bhavana.controller;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bhavana.utility.JdbcConnection;

@WebServlet("/CreateStudent")
public class CreateStudent extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		Connection connection=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		int rows=0;
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		String fname=request.getParameter("fname");
		String lname=request.getParameter("lname");
		String roll=request.getParameter("roll");
		String email=request.getParameter("email");
		int age=Integer.parseInt(request.getParameter("age"));
		long mobile=Long.parseLong(request.getParameter("mobile"));
		out.print("<!DOCTYPE html>\r\n"
				+ "<html lang=\"en\">\r\n"
				+ "<head>\r\n"
				+ "    <meta charset=\"UTF-8\">\r\n"
				+ "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\r\n"
				+ "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n"
				+ "    <title>HomePage</title>\r\n"
				+ "    <style>\r\n"
				+ "        body{\r\n"
				+ "            background-color: rgb(32, 29, 29);\r\n"
				+ "        }\r\n"
				+ "        h1{\r\n"
				+ "            text-align: center;\r\n"
				+ "            color: white;\r\n"
				+ "        }\r\n"
				+ "        p{\r\n"
				+ "            text-align: center;\r\n"
				+ "            color: azure;\r\n"
				+ "            font-size:large;\r\n"
				+ "        }");
		out.print(" </style>\r\n"
				+ "</head>\r\n"
				+ "<body>\r\n"
				+ "    <header>\r\n"
				+ "        <h1>Welcome to the Student Application</h1>\r\n"
				+ "        <p>This is used to performing CRUD Operations....Choose the below options to perform actions..</p>\r\n"
				+ "        <hr>\r\n"
				+ "        <br><br>\r\n"
				+ "    </header>");
		try
		{
			if(connection==null)
			{
				connection=JdbcConnection.getConnection();
			}
			if(connection!=null)
			{
				ps=connection.prepareStatement("select count(*) from studentapp where Roll_no=? or Email=?");
			}
			if(ps!=null)
			{
				ps.setString(1, roll);
				ps.setString(2, email);
				rs=ps.executeQuery();
				rs.next();
				if(rs.getInt(1)==0)
				{
					ps=null;
					if(ps==null)
					{
						ps=connection.prepareStatement("insert into studentapp values(?,?,?,?,?,?)");
					}
					if(ps!=null)
					{
						ps.setString(1, fname);
						ps.setString(2, lname);
						ps.setString(3, roll);
						ps.setInt(4, age);
						ps.setLong(5, mobile);
						ps.setString(6, email);
						rows=ps.executeUpdate();
					}
					if(rows!=0)
					{
						out.print("<p>Successfully Created...</p>");
					}
				}
				else
				{
					out.print("<p>It seems Roll Number or Email already Exist....</p>");
				}
			}
			out.print("<a href='http://localhost:8080/StudentApplication/'>Click here to go HomePage</a>");
			out.print("</body></html>");
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}

}
