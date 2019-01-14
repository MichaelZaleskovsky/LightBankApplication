package pl.restServer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class DAO {
	
	//Simulation of "in-memory" db
	private Map<Long, Account> accounts;
	
	public DAO() {
		accounts = new HashMap<>();
	}
	
	public void debit(long account, int sum) throws Exception {
		Account acc = accounts.get(account);
		if (acc == null) throw new Exception("Account number "+account+" not exist");
		if (acc.getSum() < sum) throw new Exception("Not enough money on account "+account);
		acc.setSum(acc.getSum() - sum);
		accounts.put(account, acc);
	}
	
	public void credit(long account, int sum) throws Exception {
		Account acc = accounts.get(account);
		if (acc == null) throw new Exception("Account number "+account+" not exist");
		acc.setSum(acc.getSum() + sum);
		accounts.put(account, acc);
	}
	
	public Account getAccount(long account) {
		return accounts.get(account);
	}
	
	public long createAccount(String owner) {
		Random rd = new Random(owner.length());
		long accountNumber;
		do {
			accountNumber = Math.abs(rd.nextLong());
		} while (accounts.get(accountNumber) != null);
		accounts.put(accountNumber, new Account(accountNumber, owner));
		return accountNumber;
	}
	
	public List<Account> getAllAccounts(){
		List<Account> accList = new ArrayList<>();
		for(long number: accounts.keySet()) {
			accList.add(accounts.get(number));
		}
		return accList;
	}
}
