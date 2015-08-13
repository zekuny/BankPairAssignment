import java.text.SimpleDateFormat;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Scanner;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class BankApp {

	public static void main(String[] args) throws SQLException {
		//URL of Oracle database server
        String url = "jdbc:oracle:thin:testuser/password@localhost"; 
        String sql="";
        //properties for creating connection to Oracle database
        Properties props = new Properties();
        props.setProperty("user", "testdb");
        props.setProperty("password", "password");
      
        //creating connection to Oracle database using JDBC
        Connection conn = DriverManager.getConnection(url,props);
        PreparedStatement preStatement ;
        ResultSet result;
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Welcome to Evil Banking System!");
		System.out.println("*****************************************************");
		String accountNumber = Validator.getString(sc, "Enter an account # or -1 to stop entering accounts : ");
		String regex = "\\d+";
		String regex2 = "[a-zA-Z ]+";
		while(!accountNumber.equals("-1") && !accountNumber.matches(regex)){
			accountNumber = Validator.getString(sc, "Enter a valid number for acct #: ");
		}
		while(!accountNumber.equalsIgnoreCase("-1")){
			// judge if the account Number exists in the database
			sql = "select * from account where accountNumber = '" + accountNumber + "'";
			preStatement = conn.prepareStatement(sql);
	        result = preStatement.executeQuery();
	        if(!result.next()){
				String name = Validator.getString(sc, "Enter the name for acct # " + accountNumber + " : ");
				while(!name.matches(regex2)){
					name = Validator.getString(sc, "Enter a valid name: ");
				}
				
				double balance = Validator.getDouble(sc, "Enter the balance for acct # " + accountNumber + " : ");
				sql = "insert into Account (accountid, accountnumber, accountname, balance) values (NULL, '" + accountNumber + "', '" + name + "', " + balance + ")";
				//creating PreparedStatement object to execute query
				preStatement = conn.prepareStatement(sql);
		        preStatement.executeQuery();
				// write into database
	        }else{
	        	System.out.println("Account already exist!");
	        }
	        System.out.println("------------------------------------------------------");
			accountNumber = Validator.getString(sc, "Enter an account # or -1 to stop entering accounts : ");
			while(!accountNumber.equals("-1") && !accountNumber.matches(regex)){
				accountNumber = Validator.getString(sc, "Enter a valid number for acct #: ");
			}
		}
		System.out.println("*****************************************************");
		List<Transaction> transactionList = new ArrayList<Transaction>();
		System.out.println("Enter transaction information: ");
		int typeID = Validator.getInt(sc, "Enter a transaction type (1 - Deposit 2 - Check 3 - Withdrawal 4 - Debit Card) or -1 to finish : ");
		while(typeID != -1 && typeID != 1 && typeID != 2 && typeID != 3 && typeID != 4){
			typeID = Validator.getInt(sc, "Enter a transaction type (1 - Deposit 2 - Check 3 - Withdrawal 4 - Debit Card) or -1 to finish : ");
		}
		while(typeID != -1){
			accountNumber = Validator.getString(sc, "Enter the account # : ");
			while(!accountNumber.equals("-1") && !accountNumber.matches(regex)){
				accountNumber = Validator.getString(sc, "Enter a valid number for acct #: ");
			}
			
			double amount = Validator.getDouble(sc, "Enter the amount of the check:", 0, 1000000000000000000.0);
			String tmp = Validator.getString(sc, "Enter the date of the transaction: (please enter in dd-MMM-yyyy format): ");
			String regexDate = "^([012]?\\d|3[01])-([Jj][Aa][Nn]|[Ff][Ee][bB]|[Mm][Aa][Rr]|[Aa][Pp][Rr]|[Mm][Aa][Yy]|[Jj][Uu][Nn]|[Jj][u]l|[aA][Uu][gG]|[Ss][eE][pP]|[oO][Cc]|[Nn][oO][Vv]|[Dd][Ee][Cc])-(19|20)\\d\\d$";
			while(!tmp.matches(regexDate)){
				tmp = Validator.getString(sc, "Enter a valid date of the transaction: (please enter in dd-MMM-yyyy format): ");
			}
			
			SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yy");
			Date date = new Date();
			
			try{
				date = format.parse(tmp);
			}catch(Exception e){
				
			}
			Transaction t = new Transaction(typeID, accountNumber, amount, date);
			transactionList.add(t);
			
			//sql = "insert into transaction (transactionid, accountnumber, amount, typeid, \"date\") values (NULL, '333', 9, 2, '02-jul-15')";
			sql = "insert into transaction (transactionid, accountnumber, amount, typeid, \"date\") values (NULL, '" + accountNumber + "', " + amount + ", " + typeID + ", '" + tmp + "')";
			//creating PreparedStatement object to execute query
			preStatement = conn.prepareStatement(sql);
	        preStatement.executeQuery();
	        System.out.println("------------------------------------------------------");
			typeID = Validator.getInt(sc, "Enter a transaction type (1 - Deposit 2 - Check 3 - Withdrawal 4 - Debit Card) or -1 to finish : ");
			while(typeID != -1 && typeID != 1 && typeID != 2 && typeID != 3 && typeID != 4){
				typeID = Validator.getInt(sc, "Enter a transaction type (1 - Deposit 2 - Check 3 - Withdrawal 4 - Debit Card) or -1 to finish : ");
			}
		}
		
		Comparator<Transaction> dateComparator = new Comparator<Transaction>(){
			public int compare(Transaction t1, Transaction t2){
				return t1.getDate().compareTo(t2.getDate());
			}
		};

		Collections.sort(transactionList, dateComparator);		
		
		for(Transaction t : transactionList){
			String accNumber = t.getAccountNumber();
			// judge......
			sql = "select * from account where accountnumber = '" + t.getAccountNumber() + "'";
			preStatement = conn.prepareStatement(sql);
	        result = preStatement.executeQuery();
	        System.out.println("--------------------");
			if(result.next()){
				System.out.println("**************************");
				// get the current balance
				sql = "select balance from Account where accountNumber = '" + accNumber + "'"; 
				preStatement = conn.prepareStatement(sql);
		        result = preStatement.executeQuery();
		        double tmp=0;;
		        if(result.next()){
		        	tmp = Double.valueOf(result.getString("balance"));
		        	System.out.println(tmp);
		        }else{
		        	System.out.println("not found");
		        }
				// update 
				if(t.getTypeID() == 1){
					tmp = tmp + t.getAmount();
				}else{
					tmp = tmp - t.getAmount();
				}
				sql = "update Account set balance = " + tmp + " where accountNumber = '" + accNumber + "'";
				preStatement = conn.prepareStatement(sql);
				preStatement.executeQuery();
			}	
		} 
		System.out.println("*****************************************************");
		System.out.println("Print account infomation");
		// get all account information
		sql = "select * from Account";
		preStatement = conn.prepareStatement(sql);
        result = preStatement.executeQuery();
        System.out.printf("\n%-12s %-20s %-10s\n", "Account", "Name", "Balance");
        while(result.next()){
        	//System.out.println( result.getString("amount"));
        	
        	System.out.printf("\n%-12s %-20s %-10s\n", result.getString("accountnumber"), result.getString("accountname"), result.getString("balance"));
            //System.out.println("Current Date from Oracle : " +         result.getString("current_day"));
        }
        System.out.println("*****************************************************");
        System.out.println("Print transaction infomation");
        sql ="select transactionID, accountNumber, amount, typeid, TO_CHAR(\"date\",\'YYYY-MM-DD\') as tDate from transaction order by \"date\"";
        preStatement = conn.prepareStatement(sql);
        result = preStatement.executeQuery();
        System.out.printf("\n%-12s%-12s%-5s%-15s\n", "Account", "Amount", "type", "Date");
        while(result.next()){
        	//System.out.println( result.getString("amount"));
        	System.out.printf("\n%-12s%-12s%-5s%-15s\n", result.getString("accountnumber"), result.getString("Amount"), result.getString("typeid"),result.getString("tDate"));
            //System.out.println("Current Date from Oracle : " +         result.getString("current_day"));
        }
	}
}
