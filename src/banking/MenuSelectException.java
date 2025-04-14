package banking;

public class MenuSelectException extends Exception{
	
	public MenuSelectException() {
		super("1~6까지의 정수를 입력해주세요.");
	}
	
}
