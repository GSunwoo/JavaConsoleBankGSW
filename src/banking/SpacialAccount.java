package banking;

public class SpacialAccount extends NormalAccount{
	private int dopositNum;
	
	public SpacialAccount(String accountNum, String name, int myMoney, int inter) {
		super(accountNum, name, myMoney, inter);
		this.dopositNum = 0;
	}
	
	@Override
	public void deposit(int money) {
		this.dopositNum++; // 입금 횟수 증가
		double newBDouble = getCalInter()*getMyMoney() + money;
		int newBalance = (int) newBDouble;
		System.out.println(this.dopositNum + "회차 입금입니다.");
		if(this.dopositNum%2==0) {
			newBalance += 500;
			System.out.println("축하금 500원 지급");
		}		
		setMyMoney(newBalance);
	} // 입금 후 금액 계산
	
	@Override
	public void showInfo() {
		System.out.println("계좌번호> " + getAccountNum());
		System.out.println("고객이름> " + getName());
		System.out.println("잔고> " + getMyMoney());
		System.out.println("기본이자> " + getInter() +"%");
		System.out.println("입금횟수> " + dopositNum);
	} // 계좌정보 출력
}
