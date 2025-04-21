package banking.jdbc.step1;

import java.sql.SQLException;
import java.sql.Types;



public class UpdateAcc extends AccConnection {
	
	int mod;
	
	public UpdateAcc(int mod) {
		super(USER, PW);
		this.mod = mod;
	}
	
	int result;
	
	@Override
	public void dbExecute() {
		
		try {
			/*
			프로시저는 return값이 없으므로 함수처럼 ?= 로 시작하지 않는다. 
			out 파라미터를 통해 값을 설정한다. */
			
			//인파라미터 설정 : 사용자로부터 입력받은 값을 세팅 
			String accNum = inputValue("계좌번호");
			int money = Integer.parseInt(inputValue("금액"));
			
			stmt = con.createStatement();
			
			String query = "select balance, interestRate from banking1 where accNum= '" 
										+ accNum + "'";
			
			//쿼리문을 실행한 후 결과는 ResultSet으로 반환 
			rs = stmt.executeQuery(query);
			
			rs.next();
			
			int balance = rs.getInt("balance");
			int inter = rs.getInt("interestRate");
			
			if(mod == ICustomDefine.DEPOSIT) {
				double temp = money + balance*(1+inter*0.01);
				balance = (int) temp;
			}
			else if (mod == ICustomDefine.WITHDRAW) {
				balance -= money;
				if(balance<0) {
					System.out.println("잔액부족");
					return;
				}
			}
			
			psmt = con.prepareStatement("update banking1 set balance = ? where accNum = ?");
			
			psmt.setInt(1, balance);
			psmt.setString(2, accNum);
			
			result = psmt.executeUpdate();
			System.out.println("[psmt] "+ result +"행 입력됨");

		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			dbClose();
		}
	}
}
