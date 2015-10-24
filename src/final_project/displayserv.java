package final_project;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class displayserv extends HttpServlet
{
	
    HttpSession session;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		session = req.getSession();
		
		
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd",Locale.ENGLISH);
		
		 java.util.Date fromdate=null; 
		 try {
		  fromdate	= format.parse(req.getParameter("fromdate"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
			
		 java.util.Date enddate=null; 
		 try {
		  enddate	= format.parse(req.getParameter("enddate"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		resp.setContentType("text/html");
		PrintWriter writer = resp.getWriter();
		
	RequestDispatcher rd =	req.getRequestDispatcher("display.html");
		rd.include(req,resp);
		
		
		
ApplicationContext context = new ClassPathXmlApplicationContext("final_project/bean.xml");
		
		accountdao daoobject = (accountdao) context.getBean("acc");
		List<displaystat> ds = daoobject.retrieve(session.getAttribute("name").toString(), fromdate, enddate);
		
		if(ds.size() != 0)
		{
		
		writer.print("<html><body><table border=\"1\" style=\"width:100%\"><tr><td>date</td><td>description</td><td>withdraw</td>"
				+ "<td>deposit</td><td>available balance</td></tr>");
		
		for (displaystat displaystat : ds) {
			
		writer.print("<tr><td>"+(displaystat.getTrans_date().toString()).substring(0,10) +"</td><td>"+displaystat.getDescription() +"</td><td>"+displaystat.getWithdraw()+"</td>"
				+ "<td>"+displaystat.getDeposit()+"</td><td>"+displaystat.getBalance()+"</td></tr>");
			
			
		}
		
	
		
		}
		
		else
		{
			
			writer.print("no records to display");
			
		}
		
	}

	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		doGet(req, resp);
	}
}
