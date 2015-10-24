package final_project;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class transaction extends HttpServlet


{
	
	HttpSession session;
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		
		int ch=0;
		session = req.getSession();
		
        ApplicationContext context = new ClassPathXmlApplicationContext("final_project/bean.xml");
		
		accountdao daoobject = (accountdao) context.getBean("acc");
		ch=daoobject.checkaccount(Integer.parseInt(req.getParameter("accountno")));
		
		if(ch==1)
		
		{
		
		if (req.getParameter("debit") != null)
		{
			
			debit(req,resp);
		}
		
		if (req.getParameter("credit") != null)
		{
			
			credit(req,resp);
		}
		
		}
		
		else 
		{
			
			resp.setContentType("text/html");
			PrintWriter writer = resp.getWriter();
			writer.print("invalid account number");
			
		RequestDispatcher rd =	req.getRequestDispatcher("transactions.html");
			rd.include(req,resp);
			
			
			
		}
		
		
		
		
	}
	
void debit(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException
{
	
	ApplicationContext context = new ClassPathXmlApplicationContext("final_project/bean.xml");
	
	accountdao daoobject = (accountdao) context.getBean("acc");
	int st = daoobject.debitfrom(Integer.parseInt(req.getParameter("accountno")), session.getAttribute("name").toString(),Integer.parseInt(req.getParameter("amount")));
	resp.setContentType("text/html");
	PrintWriter writer = resp.getWriter();
	if (st == 0)
	{
		writer.print("don't have sufficient balance to perform transaction");	
	}
	else
	{
	writer.print("debit transaction completed");
	}
RequestDispatcher rd =	req.getRequestDispatcher("transactions.html");
	rd.include(req,resp);
	
	
	
}
	

void credit(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException
{
	
ApplicationContext context = new ClassPathXmlApplicationContext("final_project/bean.xml");
	
	accountdao daoobject = (accountdao) context.getBean("acc");
	int st = daoobject.creditto(Integer.parseInt(req.getParameter("accountno")), session.getAttribute("name").toString(),Integer.parseInt(req.getParameter("amount")));
	resp.setContentType("text/html");
	PrintWriter writer = resp.getWriter();
	if(st==0)
	{
		writer.print("don't have sufficient balance to perform transaction");
	}
	else
	{
	writer.print("credit transaction completed");
	}
	
RequestDispatcher rd =	req.getRequestDispatcher("transactions.html");
	rd.include(req,resp);
	
}


}
