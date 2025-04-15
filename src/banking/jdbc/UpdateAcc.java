package banking.jdbc;

import java.sql.SQLException;
import java.sql.Types;

import banking.Account;


public class UpdateAcc extends AccConnection {
	
	public UpdateAcc(Account acc) {
		super(USER, PW, acc);
	}
	
	int result;
	
	@Override
	public void dbExecute() {
		try {
			/*
			프로시저는 return값이 없으므로 함수처럼 ?= 로 시작하지 않는다. 
			out 파라미터를 통해 값을 설정한다. */
			psmt = con.prepareStatement("update banking set balance = ? where accNum = ?");
			//인파라미터 설정 : 사용자로부터 입력받은 값을 세팅 
			psmt.setInt(1, acc.getMyMoney());
			psmt.setString(2, acc.getAccountNum());

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
