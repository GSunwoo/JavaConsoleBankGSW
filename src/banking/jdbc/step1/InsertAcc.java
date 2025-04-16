package banking.jdbc.step1;

import java.sql.SQLException;

public class InsertAcc extends AccConnection {
	
	public InsertAcc() {
		super(USER, PW);
	}
	
	String query; //쿼리문 작성
	int result; //쿼리 실행 후 결과 반환 
	
	@Override
	public void dbExecute() {
		try {
			query = "insert into banking1 values"
					+ "(seq_banking_idx_1.nextval,?,?,?,?)";
			
			psmt = con.prepareStatement(query);
			// 정보 입력받음
			psmt.setString(1, inputValue("계좌번호"));
			psmt.setString(2, inputValue("고객이름"));
			psmt.setString(3, inputValue("잔고"));
			psmt.setString(4, inputValue("이자율"));
			//쿼리문 실행 및 결과 반환 
			result = psmt.executeUpdate();
			System.out.println("[psmt] "+ result +"행 입력됨");
		}
		 catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
}
