package banking;

public class OneTwoException extends Exception{
	public OneTwoException() {
		super("1과 2중 하나를 입력하세요");
	}
	
	public void printEx() {
		System.out.println("[예외발생] " + super.getMessage());
	}
}
