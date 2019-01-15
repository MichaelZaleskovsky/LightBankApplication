package pl.restServer.main;

import java.util.List;

import pl.restServer.dao.DAO;
import pl.restServer.entities.Account;
import pl.restServer.entities.Transaction;

public class AccountManager {
	private DAO db;

	public AccountManager() {
		db = new DAO();
		
		// tempo
		long ac1 = db.createAccount("Alexandr");
		long ac2 = db.createAccount("Masha");
		long ac3 = db.createAccount("Agnieszka");
		long ac4 = db.createAccount("Stanislaw");
		try {
			db.credit(ac1, 5000);
			db.credit(ac2, 1000);
			db.credit(ac3, 10000);
			db.credit(ac4, 500);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public long createAccount(String owner) {
		// In real application check all permissions and validation of owner
		return db.createAccount(owner);
	}
	
	public void credit(long account, int sum) throws Exception {
		// In real application check all permissions for account
		if (sum <= 0) throw new Exception("Transfer account must be more then zero");
		db.credit(account, sum);
	}
	
	public void debit(long account, int sum) throws Exception {
		// In real application check all permissions for account
		if (sum <= 0) throw new Exception("Transfer account must be more then zero");
		db.debit(account, sum);
	}
	
	public Account getAccount(long account) {
		// In real application check authorization
		return db.getAccount(account);
	}
	
	public List<Account> getAllAccounts() {
		// In real application check authorization
		return db.getAllAccounts();
	}
	
	public void transfer(Transaction trans) throws Exception {
		long accFromNum = Long.parseLong(trans.getAccountFrom());
		long accToNum = Long.parseLong(trans.getAccountTo());
		
		if (accFromNum == accToNum) throw new Exception("Source and destination accounts must be different");
		if (trans.getSum() <= 0) throw new Exception("Transfer account must be more then zero");
		
		Account accountFrom = db.getAccount(accFromNum);
		if (accountFrom == null) throw new Exception("Account number "+trans.getAccountFrom()+" not exist");

		Account accountTo = db.getAccount(accToNum);
		if (accountTo == null) throw new Exception("Account number "+trans.getAccountTo()+" not exist");
		
		if (accountFrom.getSum() < trans.getSum()) throw new Exception("Account number "+trans.getAccountFrom()+" have no enough money");
		
		db.debit(accountFrom.getNumber(), trans.getSum());
		
		try {
			db.credit(accountTo.getNumber(), trans.getSum());
		} catch (Exception ex) {
			db.credit(accountFrom.getNumber(), trans.getSum());
			throw new Exception("Transaction was not completed");
		}
	}
}
