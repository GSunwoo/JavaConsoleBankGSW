package banking.jdbc.step1;

import java.util.Scanner;

public class BankingSystemMain {
	
	//키보드 입력을 위한 인스턴스
	static Scanner scan = new Scanner(System.in);
	//계좌정보 저장을 위한 인스턴스배열
	static Account[] accounts = new Account[50];
	//개설된 계좌정보 카운트용 변수 
	static int accCnt = 0;
	
	public static Account searchAccount(String accNum, Account[] accounts) {
		for(Account ac : accounts) {
			if(accNum.equals(ac.getAccNum())) {
				return ac;
			}
		}
		return null;
	}
	
	public static void showMenu() {
		System.out.println("1.계좌계설");
		System.out.println("2.입금");
		System.out.println("3.출금");
		System.out.println("4.전체계좌정보출력");
		System.out.println("5.검색계좌정보출력");
		System.out.println("6.프로그램종료");
	}
 
	// 계좌개설을 위한 함수
	public static void makeAccount() {
		InsertAcc jdbc = new InsertAcc();
		jdbc.dbExecute();
	}   
	// 입 금
	public static void depositMoney() {
		UpdateAcc jdbc = new UpdateAcc(ICustomDefine.DEPOSIT);
		jdbc.dbExecute();
	}   
	// 출 금
	public static void withdrawMoney() {
		UpdateAcc jdbc = new UpdateAcc(ICustomDefine.WITHDRAW);
		jdbc.dbExecute();
	}
	// 전체계좌정보출력
	public static void showAccInfo() {
		SelectAllAcc jdbc = new SelectAllAcc();
		jdbc.dbExecute();
		
		System.out.println("**전체계좌정보가 출력됨**");
	}
	
	public static void showSearchAccInfo() {
		SelectAcc jdbc = new SelectAcc();
		jdbc.dbExecute();
		
		System.out.println("**전체계좌정보가 출력됨**");
	}  

	public static void main(String[] args) {
	
		while(true) {
			//메뉴출력
			showMenu();
			System.out.print("메뉴입력:");
			int key = scan.nextInt();
			scan.nextLine();//버퍼에 남은 엔터키 제거
			switch(key) {
			case ICustomDefine.MAKE: 
				//계좌개설
				System.out.println("계좌개설");
				makeAccount();
				break;
			case ICustomDefine.DEPOSIT: 
				//입금
				System.out.println("입금");
				depositMoney();
				break;
			case ICustomDefine.WITHDRAW: 
				//출금
				System.out.println("출금");
				withdrawMoney();
				break;
			case ICustomDefine.INQUIRE: 
				//계좌정보출력
				System.out.println("전체계좌정보출력");
				showAccInfo();
				break;
			case ICustomDefine.SEARCH: 
				//계좌정보출력
				System.out.println("검색계좌정보출력");
				showSearchAccInfo();
				break;
			case ICustomDefine.EXIT: 
				//프로그램종료
				System.out.println("프로그램종료");
				System.exit(0);
				break;
			}//switch 끝
		}//while 끝	
	}//main 끝
}//class 끝


