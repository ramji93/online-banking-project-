package final_project;

import java.util.Date;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;

import antlr.collections.List;

public class accountdao {
	
	HibernateTemplate template;
	
	public void setTemplate(HibernateTemplate template) {
		this.template = template;
	}
	
	@Transactional(readOnly=false)
	public void save(account a)
	{
	
	    Session session = template.getSessionFactory().openSession();
		
	    Transaction t = session.beginTransaction();
	    
	    System.out.println("address " + a.getAddress() + "dob " + a.getDob() + "type " + a.getType() + "balance "+ a.getBalance());
	    
		session.save(a);
		
		t.commit();
		
		session.close();
		
	}
	
	public int debitfrom(int accountno,String name,int amount)
	
	{
		int status = 1;
		
		 Session session = template.getSessionFactory().openSession();
			
		    Transaction t = session.beginTransaction();
		    
		Criteria criteria1 = session.createCriteria(account.class);
		
		criteria1.add(Restrictions.eq("cust_name", name));
		
			             
		
         account a = (account) criteria1.uniqueResult();
	
         a.setBalance(a.balance+amount);
         
         session.update(a);
         
         displaystat ds1 = new displaystat();
         
         ds1.setName(a.getCust_name());
         ds1.setTrans_date(new Date());
         
         ds1.setDescription("debitted from account no."+accountno);
         ds1.setDeposit(amount);
         ds1.setBalance(a.getBalance());
         
         session.save(ds1);
         
         
         
         Criteria criteria2 = session.createCriteria(account.class);
 		
 		criteria2.add(Restrictions.eq("accno", accountno));
 		

        account b = (account) criteria2.uniqueResult();
         
        b.setBalance(b.balance-amount);
        
        session.update(b);
        
        displaystat ds2 = new displaystat();
        
        ds2.setName(b.getCust_name());
        ds2.setTrans_date(new Date());
        
        ds2.setDescription("creditted to account no."+a.getAccno());
        ds2.setWithdraw(amount);
        ds2.setBalance(b.getBalance());
        
        session.save(ds2);
        
        
        if(b.getBalance()<0)
        {
        	t.rollback();
        	status = 0;
        }
        else
        {
        
        t.commit();
        status = 1;
        }
        
        session.close();
		
		return status;
	}
	

	public int creditto(int accountno,String name,int amount)
	
	{
		int status = 1;
		 Session session = template.getSessionFactory().openSession();
			
		    Transaction t = session.beginTransaction();
		    
		Criteria criteria1 = session.createCriteria(account.class);
		
		criteria1.add(Restrictions.eq("cust_name", name));
		
			             
		
         account a = (account) criteria1.uniqueResult();
	
         a.setBalance(a.balance-amount);
         
         session.update(a);
         
         displaystat ds1 = new displaystat();
         
         ds1.setName(a.getCust_name());
         ds1.setTrans_date(new Date());
         
         ds1.setDescription("credited to account no."+accountno);
         ds1.setWithdraw(amount);
         ds1.setBalance(a.getBalance());
         
         session.save(ds1);
         
         Criteria criteria2 = session.createCriteria(account.class);
 		
 		criteria2.add(Restrictions.eq("accno", accountno));
 		

        account b = (account) criteria2.uniqueResult();
         
        b.setBalance(b.balance+amount);
        
        session.update(b);
        
        displaystat ds2 = new displaystat();
        
        ds2.setName(b.getCust_name());
        ds2.setTrans_date(new Date());
        
        ds2.setDescription("debitted from account no."+a.getAccno());
        ds2.setDeposit(amount);
        ds2.setBalance(b.getBalance());
        
        session.save(ds2);
        
        if(a.getBalance()<0)
        {
        	t.rollback();
        	status = 0;
        }
        else
        {
        t.commit();
        status = 1;
        }
        
        session.close();
			
        return status;
	}
	
	public java.util.List<displaystat> retrieve(String name,Date fromdate,Date todate)
	{
		Session session = template.getSessionFactory().openSession();
		
	    Transaction t = session.beginTransaction();
	
	    Criteria crit1 = session.createCriteria(displaystat.class);
	    crit1.add(Restrictions.between("trans_date",fromdate ,todate));
	    crit1.add(Restrictions.eq("name",name));
	    java.util.List<displaystat> ds = crit1.list();
	    t.commit();
	    session.close();
	    return ds;
	    
		
	}
	
	public int checkaccount (int accno)
	{
		 Session session = template.getSessionFactory().openSession();
			
		    Transaction t = session.beginTransaction();
		    
		Criteria criteria1 = session.createCriteria(account.class);
		
		criteria1.add(Restrictions.eq("accno", accno));
		
      account a = (account) criteria1.uniqueResult();
      
        t.commit();
        
        session.close();
      
       if(a !=null)
       {
    	   return (1);
       }
       
       else
    	   
    	   return (0);
	
		
	}
	
	
}
