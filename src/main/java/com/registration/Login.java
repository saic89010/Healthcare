package com.registration;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String uemail=request.getParameter("username");
		String upwd=request.getParameter("password");
		Connection con =null;
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/project?useSSL=false","root","surendra");
			PreparedStatement pst  = con.prepareStatement("select * from register where uemail=? and upwd =?");
			pst.setString(1, uemail);
			pst.setString(2, upwd);
			ResultSet rs=pst.executeQuery();
			if(rs.next())
			{
				System.out.println("My Index page");
				RequestDispatcher rd=request.getRequestDispatcher("index.jsp");
				rd.forward(request, response);
			}
			else
			{
				System.out.println("My Login page");
				RequestDispatcher rd=request.getRequestDispatcher("login.jsp");
				rd.forward(request, response);
			}
			}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
			
			
		
	}

}
