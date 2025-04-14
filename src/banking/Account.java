package banking;

import java.io.Serializable;
import java.util.Objects;

public abstract class Account implements Serializable{
	private String accountNum;	// 계좌번호
	private String name;		// 예금주
	private int myMoney;		// 잔고

	public Account(String accountNum, String name, int myMoney) {
		this.accountNum = accountNum;
		this.name = name;
		this.myMoney = myMoney;
	}
	
	public String getAccountNum() {
		return accountNum;
	}

	public String getName() {
		return name;
	}

	public int getMyMoney() {
		return myMoney;
	}

	public void setMyMoney(int myMoney) {
		this.myMoney = myMoney;
	}

	
	public abstract void showInfo(); // 계좌정보 출력

	@Override
	public int hashCode() {
		return Objects.hash(accountNum);
	}

	@Override
	public boolean equals(Object obj) {
		Account acc = (Account) obj;
		if (this.getAccountNum().equals(acc.getAccountNum())) {
			return true;
		}
		else {
			return false;
		}
	}
}
