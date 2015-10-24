package final_project;

import java.util.Date;

public class displaystat {
    int sno;
	String name;
	Date trans_date;
	String description;
	int withdraw;
	int deposit;
	int balance;
	
public displaystat() {
	
	deposit=0;
	withdraw=0;
	sno=1;
}
	
public void setBalance(int balance) {
	this.balance = balance;
}

public void setDeposit(int deposit) {
	this.deposit = deposit;
}

public void setDescription(String description) {
	this.description = description;
}

public void setName(String name) {
	this.name = name;
}

public void setTrans_date(Date trans_date) {
	this.trans_date = trans_date;
}

public void setWithdraw(int withdraw) {
	this.withdraw = withdraw;
}

public int getBalance() {
	return balance;
}
public int getDeposit() {
	return deposit;
}
public String getDescription() {
	return description;
}
public String getName() {
	return name;
}
public Date getTrans_date() {
	return trans_date;
}
public int getWithdraw() {
	return withdraw;
}
public void setSno(int sno) {
	this.sno = sno;
}
public int getSno() {
	return sno;
}


}
