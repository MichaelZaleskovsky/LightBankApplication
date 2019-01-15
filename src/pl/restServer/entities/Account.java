package pl.restServer.entities;

public class Account {
	private long number;
	private String owner;
	private int sum;
	public Account(long number, String owner) {
		super();
		this.number = number;
		this.owner = owner;
		this.sum = 0;
	}
	public long getNumber() {
		return number;
	}
	public void setNumber(long number) {
		this.number = number;
	}
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	public int getSum() {
		return sum;
	}
	public void setSum(int sum) {
		this.sum = sum;
	}

}
