package final_project;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.transaction.annotation.Transactional;


public class createaccount extends HttpServlet
{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		
		account a = new account();
		
		 a.setCust_name(req.getParameter("name"));
		 
		 DateFormat format = new SimpleDateFormat("yyyy-MM-dd",Locale.ENGLISH);
		
		 java.util.Date date=null; 
		 try {
		  date	= format.parse(req.getParameter("dob"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		a.setDob(date);
		
		a.setAddress(req.getParameter("address"));
		
		
		if(req.getParameter("type").contains("sb"))
		{
		a.setBalance(1000);
		
		System.out.println("setting bal as 1000");
		}
		
		else
		{
			a.setBalance(0);
		System.out.println("setting bal as 0");	
		}
		a.setEmailid(req.getParameter("emailid"));
		
	    a.setType(req.getParameter("type"));
	    
		ApplicationContext context = new ClassPathXmlApplicationContext("final_project/bean.xml");
		
		accountdao daoobject = (accountdao) context.getBean("acc");
		daoobject.save(a);
		
		System.out.println("account saved");
		
		resp.setContentType("text/html");
		PrintWriter writer = resp.getWriter();
		writer.print("new account has been created");
		
	RequestDispatcher rd =	req.getRequestDispatcher("createaccount.html");
		rd.include(req,resp);
		
		
		
	}
	
	
}
