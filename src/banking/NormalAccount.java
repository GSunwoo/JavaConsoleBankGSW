package banking;

import java.io.PrintWriter;

public class NormalAccount extends Account {
	private int inter; // 이자율
	
	public NormalAccount(String accountNum, String name, int myMoney, int inter) {
		super(accountNum, name, myMoney);
		this.inter = inter;
	}
	
	@Override
	public void showInfo() {
		System.out.println("계좌번호> " + getAccountNum());
		System.out.println("고객이름> " + getName());
		System.out.println("잔고> " + getMyMoney());
		System.out.println("기본이자> " + inter +"%");
	}

	public int getInter() {
		return inter;
	}
	
	public double getCalInter() {
		double calInter = inter*0.01 + 1;
		return calInter;
	}
	
	@Override
	public int getNewBalance(int money) {
		double newBDouble = this.getCalInter()*super.getMyMoney() + money;
		int newBalance = (int) newBDouble;
		return newBalance;
	}
	
	@Override
	public void autoS(PrintWriter out) {
		out.println("-----------------------------");
		out.println("계좌번호> " + this.getAccountNum());
		out.println("고객이름> " + this.getName());
		out.println("잔고> " + this.getMyMoney());
		out.println("기본이자> " + this.getInter() +"%");
		out.println("-----------------------------");
		
	}
}
