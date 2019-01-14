package pl.restServer;

public class Transaction {
	private long accountFrom;
	private long accountTo;
	private int sum;
	public Transaction() {
	}
	public long getAccountFrom() {
		return accountFrom;
	}
	public void setAccountFrom(long accountFrom) {
		this.accountFrom = accountFrom;
	}
	public long getAccountTo() {
		return accountTo;
	}
	public void setAccountTo(long accountTo) {
		this.accountTo = accountTo;
	}
	public int getSum() {
		return sum;
	}
	public void setSum(int sum) {
		this.sum = sum;
	}
	
}
