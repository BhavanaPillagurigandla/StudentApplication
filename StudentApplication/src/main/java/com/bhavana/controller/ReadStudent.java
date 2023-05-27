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

@WebServlet("/ReadStudent")
public class ReadStudent extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		Connection connection=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		String fname=request.getParameter("fname");
		String lname=request.getParameter("lname");
		String roll=request.getParameter("roll");
		String age=request.getParameter("age");
		String mobile=request.getParameter("mobile");
		String email=request.getParameter("email");
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
				+ "        }"+"table{margin-left:auto;margin-right:auto;}table,th,td{border:1px solid white;}th{color:gold;}td{color:green;}");
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
			if(fname!=""||lname!=""||roll!=""||email!="")
			{
				ps=null;
				ps=connection.prepareStatement("select * from studentapp where First_name=? or Last_name=? or Roll_no=? or Email=?");
				if(ps!=null)
				{
					ps.setString(1, fname);
					ps.setString(2, lname);
					ps.setString(3, roll);
					ps.setString(4, email);
					rs=null;
					rs=ps.executeQuery();
				}
				if(rs!=null)
				{
					int count=1;
					out.print("<table><tr><th>S.No</th><th>First_Name</th><th>Last_Name</th><th>Roll_No</th><th>Age</th><th>Mobile_No</th><th>Email</th></tr>");
					while(rs.next())
					{
						out.print("<tr><td>"+count+"</td><td>"+rs.getString(1)+"</td><td>"+rs.getString(2)+"</td><td>"+rs.getString(3)+"</td><td>"+rs.getInt(4)+"</td><td>"+rs.getLong(5)+"</td><td>"+rs.getString(6));
						count++;
					}
					out.print("</table>");
				}
			}
			
			else if(age!=""||mobile!="")
			{
				ps=null;
				ps=connection.prepareStatement("select * from studentapp where Age=? or Mobile_no=?");
				if(ps!=null)
				{
					ps.setInt(1, Integer.parseInt(age));
					ps.setLong(1, Long.parseLong(mobile));
					rs=null;
					rs=ps.executeQuery();
				}
				if(rs!=null)
				{
					int count=1;
					out.print("<table><tr><th>S.No</th><th>First_Name</th><th>Last_Name</th><th>Roll_No</th><th>Age</th><th>Mobile_No</th><th>Email</th></tr>");
					while(rs.next())
					{
						out.print("<tr><td>"+count+"</td><td>"+rs.getString(1)+"</td><td>"+rs.getString(2)+"</td><td>"+rs.getString(3)+"</td><td>"+rs.getInt(4)+"</td><td>"+rs.getLong(5)+"</td><td>"+rs.getString(6));
						count++;
					}
					out.print("</table>");
				}
			}
			else
			{
				out.print("<p>Your Not entered anything in the form...Try again!!!!!</p>");
			}
			out.print("<a href='http://localhost:8080/StudentApplication/'>Click here to go HomePage</a>");
			out.print("</body></html>");
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}

}
