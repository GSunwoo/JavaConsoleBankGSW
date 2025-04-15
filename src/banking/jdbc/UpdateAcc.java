package banking.jdbc;

import java.sql.SQLException;
import java.sql.Types;

import banking.Account;


public class UpdateAcc extends AccConnection {
	
	public UpdateAcc(Account acc) {
		super(USER, PW, acc);
	}
	
	@Override
	public void dbExecute() {
		try {
			/*
			프로시저는 return값이 없으므로 함수처럼 ?= 로 시작하지 않는다. 
			out 파라미터를 통해 값을 설정한다. */
			csmt = con.prepareCall("{call ShopUpdateGoods(?,?,?,?,?)}");
			//인파라미터 설정 : 사용자로부터 입력받은 값을 세팅 
			csmt.setString(1, inputValue("이름"));
			csmt.setString(2, inputValue("가격"));
			csmt.setString(3, inputValue("상품코드"));
			csmt.setString(4, inputValue("제품일련번호"));
			//아웃파라미터 설정 : 반환값에 대한 자료형만 설정하면 된다. 
			csmt.registerOutParameter(5, Types.NUMERIC);
			//프로시저 실행
			csmt.execute();
			//out파라미터에 할당된 값을 읽어 결과를 출력한다. 
			int affected = csmt.getInt(5);
			if(affected==0) 
				System.out.println("오류발생:수정실패");
			else 
				System.out.println(affected +"행 수정성공");
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			dbClose();
		}
	}
}
