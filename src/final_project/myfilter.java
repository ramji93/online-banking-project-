package final_project;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;



public class myfilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
	
		HttpSession session; 
		PrintWriter writer = resp.getWriter(); 
		HttpServletRequest request = (HttpServletRequest) req;
        session = request.getSession();
        String path = request.getRequestURI();
        
        if(session.getAttribute("name")==null ) 
        {   
        	if(!(path.startsWith("/final_project/loginpage.html")) && !(path.startsWith("/final_project/login_validation")))
        			{
        	
        		
        	resp.setContentType("text/html");
        	writer.print("Access Denied ");
        	RequestDispatcher rd = req.getRequestDispatcher("loginpage.html");
        	rd.include(req, resp);
        			}
        	
        	else{
        		
        		chain.doFilter(req, resp);
        	}
        }
        else
        {
        	chain.doFilter(req, resp);
        }
        
        
        
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}
	
	

}
