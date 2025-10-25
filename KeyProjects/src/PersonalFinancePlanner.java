import java.util.ArrayList;
import java.time.LocalDateTime;

public class PersonalFinancePlanner{
  private String name;
  private double balance;
  private double withdrawalFee;
  private double annualInterestRate;
  private static final int MAXIMUM_NAME_LENGTH = 20;
  
  private static int nextAccountID = 1000000;
  private int accountID;
  
  private ArrayList<Transaction> transactions = new ArrayList<Transaction>();
  
  public PersonalFinancePlanner(String name) {
	  this(name, 0);
  }
  
  public PersonalFinancePlanner(String name, double balance) {
	  this(name, balance, 0.0, 0.0);
  }
  
  public PersonalFinancePlanner(String name, double balance, double withdrawalFee, double annualInterestRate) {
	  this.name = (name.length() <= MAXIMUM_NAME_LENGTH) ? name : name.substring(0, MAXIMUM_NAME_LENGTH);
	  this.balance = balance;
	  this.withdrawalFee = withdrawalFee;
	  this.annualInterestRate = annualInterestRate;
	  accountID = nextAccountID; 
	  nextAccountID ++;
  }
  
  public String getAccountName() {
	  return name;
  }
  
  public double getBalance() {
	  return balance;
  }
  
  public double getWithdrawalFee() {
	  return withdrawalFee;
  }
  
  public double getAnnualInterestRate() {
	  return annualInterestRate;
  }
  
  public void setWithdrawalFee(double amount) {
	  withdrawalFee = amount;
  }
  
  public void setAnnualInterestRate(double amount) {
	  annualInterestRate = amount;
  }
  
  public void deposit(double amount) {
	  deposit(amount, "");
  }
  
  public void deposit(double amount, String transactionName) {
	  deposit(LocalDateTime.now(), amount, transactionName);
  }
  
  public void deposit(LocalDateTime transactionTime, double amount, String transactionName) {
	  this.balance += amount;
	  insert(transactionTime, amount, transactionName, true);
  }
  
  public void withdraw(double amount) {
	  withdraw(amount, "");
  }
  
  public void withdraw(double amount, String transactionName) {
	  withdraw(LocalDateTime.now(), amount, transactionName);
  }
  
  public void withdraw(LocalDateTime transactionTime, double amount, String transactionName) {
	  this.balance -= (amount + withdrawalFee);
	  insert(transactionTime, amount, transactionName, false);
  }
  
  private void insert(LocalDateTime transactionTime, double amount, String transactionName, boolean isDeposit) {
	  if (transactionTime == null) {
		  transactionTime = LocalDateTime.now();
	  }
	  if (transactionName == null || transactionName.isEmpty()) {
		  if (isDeposit) {
			  transactionName = "deposit";
		  } else {
			  transactionName = "withdrawal";
		  }
	  }
	  
	  Transaction newTransaction = new Transaction(transactionTime, amount, transactionName);
	  int i = 0;
	  
	  while (i < transactions.size() && transactions.get(i).getTransactionTime().isBefore(transactionTime)) {
		  i++;
	  }
	  
	  transactions.add(i, newTransaction);
  }
  
  public boolean isOverDrawn() {
	  if (balance < 0) {
		  return true;
	  } else {
		  return false;
	  }
  }
  
  public void addAnnualInterest() {
	  if (!isOverDrawn()) {
		  balance = balance * (1 + (annualInterestRate / 100 )); //converting annualInterestRate to decimal
	  }
  }
  
  public String toString() {
	  if (!isOverDrawn()) {
		  return String.format("BankAccount: name = '%s'; balance = $%.2f", name, balance);
	  } else {
		  return String.format("BankAccount: name = '%s'; balance = ($%.2f)", name, balance*-1); //changing the sign from negative to positive
	  }
  }
  
  public int getAccountID() {
	  return accountID;
  }
  
  public static int getNextAccountID() {
	  return nextAccountID;
  }
  
  public ArrayList<Transaction> getTransactions(LocalDateTime startTime, LocalDateTime endTime){
	  ArrayList<Transaction> finalArray = new ArrayList<Transaction>();
	  for (Transaction t : transactions) {
		  LocalDateTime time = t.getTransactionTime();
		  if (( startTime == null || !time.isBefore(startTime)) && (endTime == null || !time.isAfter(endTime))) {
			  finalArray.add(t);
		  }
	  }
	  
	  return finalArray;
	 
  }
}