package banking;

import java.util.InputMismatchException;

public class BankingSystemMain implements ICustomDefine {
	
	public static void showMenu() {
		System.out.println("######## 메뉴를 입력하세요 #########");
		System.out.println("1. 계좌개설 ");
		System.out.println("2. 입금");
		System.out.println("3. 출금");
		System.out.println("4. 계좌정보출력");
		System.out.println("5. 계좌정보삭제");
		System.out.println("6. 프로그램 종료");
		System.out.print("메뉴선택>>>");
	}
	
	public static int menuChoice() {
		try {
			int choice = scan.nextInt();
			scan.nextLine();
			if(choice>6||choice<1) {
				throw new MenuSelectException();
			}
			return choice;
		} catch(MenuSelectException e){
			e.getMessage();
		} catch(Exception e) {
			System.out.println("[예외발생]메뉴선택은 숫자만 입력해주세요.");
			scan.nextLine();
		}
		return 0;
	}
	
	public static void main(String[] args) {
		
		AccountManager manager = new AccountManager();
		
		manager.loadAccount();
		
		while(true) {
			showMenu();
			
			int choice = menuChoice();
			switch(choice) {
			case MAKE: //계좌 개설
				manager.makeAccount();
				break;
			case DEPOSIT:	// 입금
				manager.depositMoney();
				break;
			case WITHDRAW:  // 출금
				manager.withdrawMoney();
				break;
			case INQUIRE:	// 계좌정보 출력
				manager.showAccInfo();
				break;
			case DELETE:
				manager.deleteAccount();
				break;
			case EXIT:		// 종료
				manager.saveAccount();
				System.out.println("프로그램 종료");
				return;
			default:
				System.out.println("1~6까지의 정수를 입력해주세요.");
			}////switch 끝
		}
	}
}


