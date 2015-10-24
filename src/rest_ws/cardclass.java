package rest_ws;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;


@Path("/cardvalidate")
public class cardclass 
{

@GET
@Produces(MediaType.TEXT_HTML)
public String validate(@QueryParam("name") String name,@QueryParam("cardno") String cardno,@QueryParam("cvv") String cvv,@QueryParam("amount") String amount)
{
	

	String returnparam = new String();
	Connection con = null;
	
	try
	{
		
	
	Class.forName("com.mysql.jdbc.Driver");

      con = DriverManager.getConnection("jdbc:mysql://localhost/f1","ramji","mysql");

	PreparedStatement ps = con.prepareStatement("Select * from credit_card where name = ? and cardnumber = ? and cvv = ?"); 
	
	ps.setString(1, name);
	ps.setString(2, cardno);
	ps.setInt(3, Integer.parseInt(cvv));
	
	ResultSet rs = ps.executeQuery();
	
	if(rs.next())
	{
		

		if(Integer.parseInt(amount)>100000)
		{
			returnparam= "<html><body><h3>Transaction amount has exceeded the limit. Transaciton cancelled</h3>"
					+ "</body></html>";
		}
		
		
		else
		{
			returnparam = "<html><body><h3>Transaciton successfull</h3>"
					+ "</body></html>";
		}
			
		}
		
	
	else
	{
		
		returnparam = "<html><body><h3>Invalid card details. Transaction cancelled</h3>"
				+ "</body></html>";
		
	}
	
	
	} catch (SQLException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
     } 
	catch (ClassNotFoundException e) {
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

	return returnparam;
	
}
}
	
