package banking.jdbc.step1;

public interface IConnect {
	// 멤버상수 : 오라클드라이버, 커넥션URL 선언
	String ORACLE_DRIVER = "oracle.jdbc.OracleDriver";
	String ORCLE_URL = "jdbc:oracle:thin:@localhost:1521:xe";
	String USER = "education";
	String PW = "1234";
	
	// 멤버메서드
	void dbExecute(); // 쿼리문 실행
	void dbClose();  // DB 자원 반납
	
	String inputValue(String title);
}
