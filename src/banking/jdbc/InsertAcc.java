package banking.jdbc;

import java.sql.SQLException;

import banking.Account;


public class InsertAcc extends AccConnection {
	
	Account acc;
	
	public InsertAcc(Account acc) {
		super(USER, PW, acc);
	}
	
	String query; //쿼리문 작성
	int result; //쿼리 실행 후 결과 반환 
	
	@Override
	public void dbExecute() {
		try {
			query = "insert into banking values"
					+ "(seq_banking_idx.nextval,?,?,?,?,?)";
			
			psmt = con.prepareStatement(query);
			// 정보 입력받음
			psmt.setString(1, acc.getAccountNum());
			psmt.setString(2, acc.getName());
			psmt.setInt(3, acc.getMyMoney());
			psmt.setInt(4, acc.getInter());
			psmt.setString(5, acc.getCreditGrade());			
			//쿼리문 실행 및 결과 반환 
			result = psmt.executeUpdate();
			System.out.println("[psmt] "+ result +"행 입력됨");
		}
		 catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
}
