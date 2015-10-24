package final_project;

import java.util.Date;

public class account {

	int accno;
	String cust_name;
	Date dob;
	String address;
	String emailid;
	String type;
	int balance;
	
	public void setAccno(int accno) {
		this.accno = accno;
	}
	
	public int getAccno() {
		return accno;
	}
	
	public void setCust_name(String cust_name) {
		this.cust_name = cust_name;
	}
	
    public String getCust_name() {
		return cust_name;
	}
	
	public void setDob(Date dob) {
		this.dob = dob;
	}
	
	public Date getDob() {
		return dob;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getAddress() {
		return address;
	}
	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}
	
	public String getEmailid() {
		return emailid;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public String getType() {
		return type;
	}
	
    public void setBalance(int balance) {
		this.balance = balance;
	}
    
    public int getBalance() {
		return balance;
	}
	
	
}
