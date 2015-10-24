package final_project;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.sql.*;

public class loginvalidation extends HttpServlet
{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		String un =  req.getParameter("username").toString();
		String pw = req.getParameter("password").toString();
		
		Connection con = null;
		
		try
		{
			
		
		Class.forName("com.mysql.jdbc.Driver");
	
	      con = DriverManager.getConnection("jdbc:mysql://localhost/f1","ramji","mysql");
	
		PreparedStatement ps = con.prepareStatement("Select cust_name from user_pass where username= ? and password = ?"); 
		
		ps.setString(1, un);
		ps.setString(2, pw);
		
		ResultSet rs = ps.executeQuery();
		
		if(rs.next())
		{
			

			HttpSession session = req.getSession();
			session.setAttribute("name",rs.getObject(1) );
			
		/*
			resp.setContentType("text/html");
			PrintWriter writer = resp.getWriter();
			writer.print(session.getAttribute("name"));*/
			RequestDispatcher rd =	req.getRequestDispatcher("mainmenu.html");
			rd.include(req,resp);
			
			
		}
		else
		{
			
			resp.setContentType("text/html");
			PrintWriter writer = resp.getWriter();
			writer.print("invalid user");
			
		RequestDispatcher rd =	req.getRequestDispatcher("loginpage.html");
			rd.include(req,resp);
			
		}
		
		
		} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		finally{
			
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}
	
	
}
