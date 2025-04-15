package banking.jdbc;

import java.sql.SQLException;

import banking.Account;


public class SelectAllAcc extends AccConnection{
	
	public SelectAllAcc(Account acc) {
		super(USER, PW, acc);
	}
	
	String query; //쿼리문 작성
	int result; //쿼리 실행 후 결과 반환 
	
	@Override
	public void dbExecute() {
		try {
			stmt = con.createStatement();
			
			query = "select * from banking order by id";
			
			//쿼리문을 실행한 후 결과는 ResultSet으로 반환 
			rs = stmt.executeQuery(query);
			System.out.println("DB출력");
			//반복 인출
			while(rs.next()) {
				//인출시 인덱스와 컬럼명 2가지로 표현 가능 
				String accNum = rs.getString("accNum");
				String name = rs.getString("name");
				String balance = rs.getString("balance");
				String interestRate = rs.getString("interestRate");
				if(rs.getString("creditGrade").equals("default")) {
					String creditGrade = rs.getString("creditGrade");
					System.out.printf("계좌번호> %s\n고객이름> %s\n잔고> %s\n이자율> %s\n신용등급> %s\n",
							accNum, name, balance, interestRate, creditGrade);
				}
				else {
					System.out.printf("계좌번호> %s\n고객이름> %s\n잔고> %s\n이자율> %s\n",
							accNum, name, balance, interestRate);
				}
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
