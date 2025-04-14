package banking;

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

}
