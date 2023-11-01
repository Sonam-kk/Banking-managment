import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Random;


public class Examm1{
	public static void main(String[] args) throws ParseException{
		Account ca = new CurrentAccount("C12345", 1000, 5000);
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		Customer c1 = new Customer("Alka", sdf.parse("19-07-2004"), "A34567", ca);
		Account sa = new SavingAccount("C7890", 500, 4.5);
		Customer c2 = new Customer("Ankit", sdf.parse("27-08-2003"), "A2467", sa);
		sa.deposit(5000);
		System.out.println(sa.getBalance());
		sa.withdraw(300);
		System.out.println(sa.getBalance());
		sa.printTransactions();
		SavingAccount ssa = (SavingAccount) sa;
		double closingBalance = ssa.getBalance() + ssa.endOfDayInterest();
		System.out.println(closingBalance);
	}
	
	
}
class Customer{
    private String name;
    private Date dob;
    private String aadharNumber;
    private Account account;
 


    Customer(String name, Date dob, String aadharNumber, Account account){
	this.name = name;
	this.dob = dob;
	this.aadharNumber = aadharNumber;
	this.account = account;
	}
}

abstract class Account {
	private String accountNumber;
	private double balance;
	private ArrayList<Transaction> transactions;
	
	Account(String accountNumber, double balance){
	this.accountNumber = accountNumber;
	this.balance = balance;
	this.transactions = new ArrayList<Transaction>();
	
	}
	public double getBalance(){
		return balance;
	}
	
	protected void setBalance(double balance){
		this.balance = balance;
	}
	boolean deposit(double amount){
		setBalance(getBalance() + amount);
		Transaction t = createTransaction(Transaction.CREDIT, amount);
		transactions.add(t);
		return true;
		}
	
	boolean withdraw(double amount){
		double balance = getBalance();
		if (amount<=balance){
			setBalance(getBalance() - amount);
			Transaction t = createTransaction(Transaction.DEBIT, amount);
			transactions.add(t);
			return true;
			}
		else{
			return false;	
			}
		}

	Transaction createTransaction(String type, double amount){
		Transaction t = new Transaction(type, amount);
		transactions.add(t);
		return t;
	}

	void printTransactions(){
		for (int i=0; i<transactions.size(); i++){
			System.out.println(transactions.get(i));
			}
	}

}

class CurrentAccount extends Account{
	private double overdraftLimit;
	
	CurrentAccount(String accountNumber, double balance, double overdraftLimit){
	    super(accountNumber,balance);
	    this.overdraftLimit = overdraftLimit;
	    
	}

	@Override
	boolean withdraw(double amount){
		double balance = getBalance();
		    if (amount<=balance+ overdraftLimit || balance>0){
			balance-= amount;
			Transaction t = createTransaction(Transaction.DEBIT, amount);
			return true;
			}
		
		else{
			return false;	
			}
	}
	
	@Override
	Transaction createTransaction(String type, double amount){
		return new Transaction(type, amount);
	}
}

class SavingAccount extends Account{
	private double interestRate;
	
	SavingAccount(String accountNumber, double balance, double interestRate){
	    super(accountNumber, balance);
	    this.interestRate = interestRate;
	}
	
	double endOfDayInterest(){
		double balance = getBalance();
		return balance * (interestRate /36500);
	}

}

class Transaction{
	private String transactionId;
	private Date date;
	private String type;
	private double amount;	
	public static final String DEBIT = "debit";
	public static final String CREDIT = "credit";
	

	Transaction(String type, double amount){
		Random r = new Random();
		this.type = type;
		this.amount = amount;
		this.transactionId = "T" + r.nextInt();
		this.date = new Date();
		}
	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder("Transaction id:" + transactionId );
		sb.append("Date :" + date);
		sb.append("Type :" + type);
		sb.append("Amount :" + amount );
		return sb.toString();
	}
}


