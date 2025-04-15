package banking;

import java.io.PrintWriter;

public class HighCreditAccount extends Account {
	private int inter;
	private char credit;
	
	public HighCreditAccount(String accountNum, String name, int myMoney, int inter, char credit) {
		super(accountNum, name, myMoney);
		this.inter = inter;
		this.credit = Character.toUpperCase(credit);
	}
	
	@Override
	public void showInfo() {
		System.out.println("계좌번호> " + getAccountNum());
		System.out.println("고객이름> " + getName());
		System.out.println("잔고> " + getMyMoney());
		System.out.println("기본이자> " + inter +"%");
		System.out.println("신용등급> " + credit);
	} // 계좌정보 출력
	
	@Override
	public int getNewBalance(int money) {
		double newBDouble = this.getCalInter()*super.getMyMoney() + money;
		int newBalance = (int) newBDouble;
		return newBalance;
	} // 이율 계산
	
	
	@Override
	public void autoS(PrintWriter out) {
		out.println("-----------------------------");
		out.println("계좌번호> " + this.getAccountNum());
		out.println("고객이름> " + this.getName());
		out.println("잔고> " + this.getMyMoney());
		out.println("기본이자> " + this.getInter() +"%");
		out.println("신용등급> " + this.getCredit());
		out.println("-----------------------------");
	} // 쓰레드 자동저장
	
	public int getInter() {
		return inter;
	}
	
	public double getCalInter() {
		double calInter = inter*0.01 + this.getGradeInter() + 1;
		return calInter;
	} // 이율 계산
	
	public char getCredit() {
		return credit;
	}
	
	public double getGradeInter() {
		if(credit=='A') {
			return ICustomDefine.A_GRADE;
		}
		else if(credit=='B') {
			return ICustomDefine.B_GRADE;
		}
		else if(credit=='C') {
			return ICustomDefine.C_GRADE;
		}
		else {
			return 0;
		}
	} // 신용등급 별 이율 반환
}
