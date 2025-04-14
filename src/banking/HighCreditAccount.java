package banking;

public class HighCreditAccount extends Account implements ICustomDefine{
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
	}
	
	public int getInter() {
		return inter;
	}
	
	public char getCredit() {
		return credit;
	}
	
	public double getGradeInter() {
		if(credit=='A') {
			return A_GRADE;
		}
		else if(credit=='B') {
			return B_GRADE;
		}
		else if(credit=='C') {
			return C_GRADE;
		}
		else {
			return 0;
		}
	}
}
