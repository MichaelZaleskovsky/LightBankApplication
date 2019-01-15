package pl.restServer.entities;

public class AccountJS {
	private String number;
	private String owner;
	private int sum;
	
	public AccountJS() {
	}
	
	public AccountJS(Account acc) {
		this.owner = acc.getOwner();
		this.sum = acc.getSum();
		this.number = ""+acc.getNumber();
	}
	
	public String getNumber() {
		return number;
	}
	
	public void setNumber(String number) {
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
