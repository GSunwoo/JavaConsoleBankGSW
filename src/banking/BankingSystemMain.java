package banking;

public class BankingSystemMain {
	
	public static void showMenu() {
		System.out.println("######## 메뉴를 입력하세요 #########");
		System.out.println("1. 계좌개설");
		System.out.println("2. 입금");
		System.out.println("3. 출금");
		System.out.println("4. 계좌정보출력");
		System.out.println("5. 계좌정보삭제");
		System.out.println("6. 저장옵션");
		//System.out.println("7. 계좌검색");
		System.out.println("7. 프로그램 종료");
		System.out.print("메뉴선택>>> ");
	}
	
	public static int menuChoice() {
		try {
			int choice = ICustomDefine.scan.nextInt();
			ICustomDefine.scan.nextLine();
			if(choice>7||choice<1) {
				throw new MenuSelectException();
			}
			return choice;
		} catch(MenuSelectException e){
			e.printEx();
		} catch(Exception e) {
			System.out.println("[예외발생] 메뉴선택은 숫자만 입력해주세요.");
			ICustomDefine.scan.nextLine();
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
			case ICustomDefine.MAKE: //계좌 개설
				manager.makeAccount();
				break;
			case ICustomDefine.DEPOSIT:	// 입금
				manager.depositMoney();
				break;
			case ICustomDefine.WITHDRAW:  // 출금
				manager.withdrawMoney();
				break;
			case ICustomDefine.INQUIRE:	// 계좌정보 출력
				manager.showAccInfo();
				break;
			case ICustomDefine.DELETE:  // 계좌정보 삭제
				manager.deleteAccount();
				break;
			case ICustomDefine.AUTO_SAVE: // 자동저장 on/off
				manager.autoSaveOn();
				break;
//			case ICustomDefine.SEARCH:	  // 계좌 검색(DB)
//				manager.searchAccInfo();
//				break;
			case ICustomDefine.EXIT:		// 종료
				manager.saveAccount();
				System.out.println("프로그램 종료");
				return;
			}////switch 끝
		} // while
	}
}


