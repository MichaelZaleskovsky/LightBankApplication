package pl.restServer;

import java.util.List;

public class AccountManager {
	private DAO db;

	public AccountManager() {
		db = new DAO();
		
		// tempo
		long ac1 = db.createAccount("Sasha");
		long ac2 = db.createAccount("Masha");
		try {
			db.credit(ac1, 2000);
			db.credit(ac2, 1000);
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
		db.credit(account, sum);
	}
	
	public void debit(long account, int sum) throws Exception {
		// In real application check all permissions for account
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
		Account accountFrom = db.getAccount(trans.getAccountFrom());
		if (accountFrom == null) throw new Exception("Account number "+accountFrom+" not exist");

		Account accountTo = db.getAccount(trans.getAccountTo());
		if (accountTo == null) throw new Exception("Account number "+accountTo+" not exist");
		
		if (accountFrom.getSum() < trans.getSum()) throw new Exception("Account number "+accountFrom+" have no enough money");
		
		db.debit(trans.getAccountFrom(), trans.getSum());
		
		try {
			db.credit(trans.getAccountTo(), trans.getSum());
		} catch (Exception ex) {
			db.credit(trans.getAccountFrom(), trans.getSum());
			throw new Exception("Transaction was not completed");
		}
	}
}
