package banking.jdbc;

import java.sql.SQLException;

import banking.Account;


public class SelectAcc extends AccConnection{
	
	public SelectAcc(Account acc) {
		super(USER, PW, acc);
	}
	
	String query; //쿼리문 작성
	int result; //쿼리 실행 후 결과 반환 
	
	@Override
	public void dbExecute() {
		try {
			stmt = con.createStatement();
			
			query = "";
			
			//쿼리문을 실행한 후 결과는 ResultSet으로 반환 
			rs = stmt.executeQuery(query);
			
			//반복 인출
			while(rs.next()) {
				//인출시 인덱스와 컬럼명 2가지로 표현 가능 
				String id = rs.getString("g_idx");
				String name = rs.getString("goods_name");
				String price = rs.getString("goods_price");
				String regidate = rs.getString("d1")
						.substring(0,13);
				String code = rs.getString("p_code");
				
				System.out.printf("%s %s %s %s %s\n",
						id, name, price, regidate, code);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
