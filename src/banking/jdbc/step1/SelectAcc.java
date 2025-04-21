package banking.jdbc.step1;

import java.sql.SQLException;

public class SelectAcc extends AccConnection{
	
	public SelectAcc() {
		super(USER, PW);
	}
	
	String query; //쿼리문 작성
	int result; //쿼리 실행 후 결과 반환 
	
	@Override
	public void dbExecute() {
		try {
			stmt = con.createStatement();
			
			query = "select * from banking1 where accNum= '" + inputValue("검색할 계좌")+"'";
			
			//쿼리문을 실행한 후 결과는 ResultSet으로 반환 
			rs = stmt.executeQuery(query);
			System.out.println("검색된 계좌(DB에서 가져와 출력)");
			//반복 인출
			rs.next();
			//인출시 인덱스와 컬럼명 2가지로 표현 가능 
			String accNum = rs.getString("accNum");
			String name = rs.getString("name");
			String balance = rs.getString("balance");
			String inter = rs.getString("interestRate");
			System.out.println("-----------------------------");
			System.out.printf("계좌번호> %s\n고객이름> %s\n잔고> %s\n이자율> %s\n",
								accNum, name, balance, inter);
			System.out.println("-----------------------------");
				
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}