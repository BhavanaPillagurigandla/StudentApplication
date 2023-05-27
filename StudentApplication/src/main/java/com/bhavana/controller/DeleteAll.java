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

@WebServlet("/DeleteAll")
public class DeleteAll extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		Connection connection=null;
		PreparedStatement ps=null;
		int rows=0;
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
				ps=connection.prepareStatement("delete from studentapp");
			}
			if(ps!=null)
			{
				 rows=ps.executeUpdate();
				 if(rows!=0)
				 {
					 out.print("<p>Successfully Deleted....</p>");
				 }
				 else
				 {
					 out.print("<p>Sorry!...Database is Empty....</p>");
				 }
			}
			
			out.print("<a href='http://localhost:8080/StudentApplication/'>Click here to go HomePage</a>");
			out.print("</body></html>");
			
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}

}
