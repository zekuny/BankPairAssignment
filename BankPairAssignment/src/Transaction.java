import java.util.Date;

public class Transaction {
	int typeID;
	String accountNumber;
	double amount;
	Date date;

	public Transaction() {
		typeID = 0;
		accountNumber = "0000000";
		amount = 0.0;
		date = new Date();
	}

	public Transaction(int t, String a, double am, Date d) {
		typeID = t;
		accountNumber = a;
		amount = am;
		date = d;
	}

	public int getTypeID() {
		return typeID;
	}

	public void setType(int typeID) {
		this.typeID = typeID;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public double getAmount() {
		return amount;
	}
 
	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}