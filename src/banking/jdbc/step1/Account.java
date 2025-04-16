package banking.jdbc.step1;

public class Account {
	//계좌번호(String형), 이름(String형), 잔액(int형)
	private String accNum;
	private String name;
	private int balance;
	private int inter;
	
	//생성자
	public Account(String accNum, String name, int balance, int inter) {
		super();
		this.accNum = accNum;
		this.name = name;
		this.balance = balance;
		this.inter = inter;
	}
	//게터/세터
	public String getAccNum() {
		return accNum;
	}
	public void setAccNum(String accNum) {
		this.accNum = accNum;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getBalance() {
		return balance;
	}
	public void setBalance(int balance) {
		this.balance = balance;
	}
	public int getInter() {
		return inter;
	}
	public void setInter(int inter) {
		this.inter = inter;
	}
	//toString 오버라이딩
	@Override
	public String toString() {
		return "Account [계좌번호=" + accNum + ", 이름=" + name + ", 잔고=" + balance + ", 이자율="+ inter +"]";
	}
	
}
