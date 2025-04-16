package banking.jdbc.step1;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;


/*
인터페이스를 구현한 클래스로 extends 대신 implements를 사용한다.
또한 용어도 '상속'이 아닌 '구현'이라 표현한다.
 */
public class AccConnection implements IConnect{
	
	// 멤버변수
	public Connection con; 		   // DB연결
	public ResultSet rs; 		   // select의 실행결과 반환
	public Statement stmt; 		   // 정적 쿼리문 실행
	public PreparedStatement psmt; // 동적 쿼리문 실행
	public CallableStatement csmt; // 프로시저 실행
	
	// 생성자. 매개변수로 오라클 계정의 아이디, 패스워드를 전달받음
	public AccConnection(String user, String pass) {
		try {
			// 오라클 드라이버를 메모리에 로드(인터페이스 상수 사용)
			Class.forName(ORACLE_DRIVER);
			// 오라클 연결
			con = DriverManager.getConnection(ORCLE_URL, user, pass);
			// Account 객체 멤버로 받기
		} catch (Exception e) {
			System.out.println("DB 커넥션 예외발생");
			e.printStackTrace();
		}
	}
	
	@Override
	public void dbExecute() {}
	
	// JDBC 작업을 위해 연결된 모든 자원을 해제
	@Override
	public void dbClose() {
		try {
			// 연결되어 사용 중이라면 자원을 반납한다.
			if(con!=null) con.close();
			if(rs!=null) rs.close();
			if(stmt!=null) stmt.close();
			if(psmt!=null) psmt.close();
			if(csmt!=null) csmt.close();
		} catch (Exception e) {
			System.out.println("DB 자원 반납시 예외발생");
			e.printStackTrace();
		}
		
	}
	
	@Override
	public String inputValue(String title) {
		Scanner sc = new Scanner(System.in);
		System.out.print(title + " 입력(exit->종료): ");
		String inputStr = sc.nextLine();
		/*
		equalsIgnoreCase(): equals()와 동일하게 문자열이 동일한지 비교하는 메서드로
							대소문자를 구분하지 않고 비교한다.
							즉 EXIT와 exit를 같은 문자열로 판단한다.
		 */
		if("EXIT".equalsIgnoreCase(inputStr)) {
			System.out.println("프로그램을 종료합니다.");
			// 자원반납
			dbClose();
			// 프로그램 자체를 종료시킨다.
			System.exit(0);
		}
		// 종료가 아니라면 입력한 값을 반환한다.
		return inputStr;
	}
	
}
