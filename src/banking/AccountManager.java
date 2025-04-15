package banking;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Iterator;

public class AccountManager implements ICustomDefine {
	
	private HashSet<Account> myAccount;

	public AccountManager() {
		myAccount = new HashSet<Account>();
	}
	
	public Account searchAccount(String accNum) {
		for(Account ac : myAccount) {
			if(accNum.equals(ac.getAccountNum())) {
				return ac;
			}
		}
		return null;
	}
	
	public void makeAccount() {
		int choice = 0;
		while(true) {
			System.out.println("***신규계좌개설***");
			System.out.println("-----계좌선택------");
			System.out.println("1.보통계좌");
			System.out.println("2.신용신뢰계좌");
			System.out.print("선택>> ");
			choice = scan.nextInt();
			scan.nextLine();
			if (choice == 1 || choice == 2) break;
			else System.out.println("잘못된 입력입니다.");
		}
		
		System.out.print("계좌번호: ");
		String accNum = scan.nextLine();
		System.out.print("고객이름: ");
		String myName = scan.nextLine();
		System.out.print("잔고: ");
		int myMoney = scan.nextInt();
		scan.nextLine();
		System.out.print("기본이자%(정수형태로입력): ");
		int basicInter = scan.nextInt();
		scan.nextLine();
		
		if(myMoney<0) {
			System.out.println("초기잔고 입력이 잘못되었습니다");
			System.out.println(": 음의 정수입력");
		}
		if(myMoney%500!=0) {
			System.out.println("초기잔고 입력이 잘못되었습니다");
			System.out.println(": 500원단위로 입금가능");
		}
		
		if(choice==1) {
			NormalAccount normal = new NormalAccount(accNum, myName, myMoney, basicInter);
			if(myAccount.add(normal)) {
				System.out.println("계좌개설완료");
			}
			else {
				while(true) {
					System.out.print("중복계좌발견됨. 덮어쓸까요?(y / n)");
					String yn = scan.nextLine();
					if(yn.equals("y") || yn.equals("Y")) {
						myAccount.remove(normal);
						myAccount.add(normal);
						System.out.println("계좌 덮어쓰기 완료");
						break;
					}
					else if (yn.equals("n")|| yn.equals("N")) {
						System.out.println("기존 계좌를 유지합니다.");
						break;
					}
					else {
						System.out.println("y 또는 n을 입력하세요.");
					}
				}
				
			}
		}
		else if(choice==2) {
			char credit = 'F';
			while(true) {
				System.out.print("신용등급(A,B,C등급): ");
				String crdStr = scan.nextLine();
				
				if (crdStr.length()!=1) {
					System.out.println("A, B, C 중 알파벳 하나만 입력하세요.");
					continue;
				}
				
				credit = crdStr.charAt(0);
				if(credit=='A'||credit=='a'||
				   credit=='B'||credit=='b'||
				   credit=='C'||credit=='c') {
						break;
				}
				else {
					System.out.println(crdStr + "등급은 존재하지 않습니다.");
				}
			}			
			
			HighCreditAccount high = new HighCreditAccount(accNum, myName, myMoney, basicInter, credit);
			if(myAccount.add(high)) {
				System.out.println("계좌개설완료");
			}
			else {
				while(true) {
					System.out.print("중복계좌발견됨. 덮어쓸까요?(y / n)");
					String yn = scan.nextLine();
					if(yn.equals("y") || yn.equals("Y")) {
						myAccount.remove(high);
						myAccount.add(high);
						System.out.println("계좌 덮어쓰기 완료");
						break;
					}
					else if (yn.equals("n") || yn.equals("N")) {
						System.out.println("기존 계좌를 유지합니다.");
						break;
					}
					else {
						System.out.println("y 또는 n을 입력하세요.");
					}
				}
				
			}
		}
		
	}// 계좌개설을 위한 함수
	
	public void depositMoney() {
		System.out.println("계좌번호와 입금할 금액을 입력하세요.");
		System.out.print("계좌번호: ");
		String acc = scan.nextLine();
		System.out.print("입금액: ");
		int money = scan.nextInt();
		scan.nextLine();
		
		if(money<0) {
			System.out.println("양의 정수를 입력하세요.");
			return;
		}
		
		if(money%500!=0) {
			System.out.println("500원 단위로 입금가능합니다.");
			return;
		}
		
		Account nowAcc = searchAccount(acc);	// 계좌 검색
		
		if(nowAcc!=null) {
			double calMoney = nowAcc.getMyMoney();
			if(nowAcc instanceof NormalAccount) {
				NormalAccount normal = (NormalAccount) nowAcc;
				double calInter = normal.getCalInter();
				calMoney = calMoney*calInter + money;
				int inputMoney = (int) calMoney;
				normal.setMyMoney(inputMoney);
			}
			else if(nowAcc instanceof HighCreditAccount) {
				HighCreditAccount high = (HighCreditAccount) nowAcc;
				double calInter = high.getCalInter();
				calMoney = calMoney*calInter + money;
				int inputMoney = (int) calMoney;
				high.setMyMoney(inputMoney);
			}// 이율계산
		}
		else {
			System.out.println(acc + " 계좌가 없습니다.");
			return;
		}
		System.out.println(money+"원 입금이 완료되었습니다.");
		System.out.println("현재 잔고> " + nowAcc.getMyMoney());	
	}// 입    금
	
	public void withdrawMoney() {
		System.out.println("계좌번호와 출금할 금액을 입력하세요.");
		System.out.print("계좌번호: ");
		String acc = scan.nextLine();
		System.out.print("출금액: ");
		int money = scan.nextInt();
		scan.nextLine();
		
		if(money<0) {
			System.out.println("양의 정수를 입력하세요.");
			return;
		}
		
		if(money%1000!=0) {
			System.out.println("1000원 단위로 출금가능합니다.");
			return;
		}
		
		Account nowAcc = searchAccount(acc);
		
		if(nowAcc!=null) {
			if((nowAcc.getMyMoney()-money)<0) {
				System.out.println("잔고가 부족합니다.");
				return;
			}
			nowAcc.setMyMoney(nowAcc.getMyMoney() - money);
			System.out.println(money + "원 출금이 완료되었습니다.");
			System.out.println("현재 잔고> " + nowAcc.getMyMoney());
		}
		else {
			System.out.println(acc + " 계좌가 없습니다.");
		}
	}// 출    금
	
	public void showAccInfo() {
		Iterator itr = myAccount.iterator();
		while(itr.hasNext()) {
			System.out.println("-----------------------------");
			Account acc = (Account) itr.next();
			
			System.out.println("계좌번호> " + acc.getAccountNum());
			System.out.println("고객이름> " + acc.getName());
			System.out.println("잔고> " + acc.getMyMoney());
			
			if(acc instanceof NormalAccount) {
				NormalAccount noAcc = (NormalAccount) acc;
				System.out.println("기본이자> " + noAcc.getInter()+"%");
			}
			else if (acc instanceof HighCreditAccount) {
				HighCreditAccount hiAcc = (HighCreditAccount) acc;
				System.out.println("기본이자> " + hiAcc.getInter()+"%");
				System.out.println("신용등급> " + hiAcc.getCredit());
			}
			System.out.println("-----------------------------");
		}
		System.out.println("전체 계좌 정보가 출력되었습니다");
	}// 전체계좌정보출력
	
	public void deleteAccount() {
		System.out.print("삭제할 계좌번호:");
		String deleteName = scan.nextLine();
		//삭제 여부 판단
		boolean isDelete = false;
		
		for(Account acc : myAccount) {
			if(deleteName.equals(acc.getAccountNum())) {
				myAccount.remove(acc);
				isDelete = true;
				break;
			}
		}
		
		if(isDelete) {
			System.out.println(">>삭제되었습니다");
		}
		else {
			System.out.println(">>삭제된 데이터가 없습니다.");
		}
	}
	
	public void saveAccount() {
		try {
			// 인스턴스를 파일로 저장하기 위해 출력스트림 생성
			ObjectOutputStream out = new ObjectOutputStream
					(new FileOutputStream("src/resource/AccountInfo.obj"));
			
			for(Account acc : myAccount) {
				out.writeObject(acc);
			}
			
			out.close();

		} catch(FileNotFoundException e) {
			System.out.println("[예외] 파일없음");
		} catch(IOException e) {
			System.out.println("[예외] 뭔가없음");
		}
	}
	
	public void loadAccount() {
		try {
			ObjectInputStream in = new ObjectInputStream
					(new FileInputStream("src/resource/AccountInfo.obj"));
			
			while (true) {
	            try {
	                Account acc = (Account) in.readObject(); // 하나씩 읽기
	                myAccount.add(acc); // HashSet에 추가
	            } catch (EOFException e) {
	                break; // 파일 끝에 도달하면 루프 종료
	            }
	        }
			in.close();
			
			if(myAccount.isEmpty()) {
				System.out.println("파일이 생성되지 않았습니다.");
			}
			else {
				this.showAccInfo();
			}
		} catch(ClassNotFoundException e) {
			System.out.println("[예외] 클래스없음");
		} catch(FileNotFoundException e) {
			System.out.println("[예외] 파일없음");
		} catch(IOException e) {
			System.out.println("[예외] 뭔가없음");
		}
	}
	
	public void autoSave(PrintWriter out){
		for(Account acc : myAccount) {
			if(acc instanceof NormalAccount) {
				NormalAccount normal = (NormalAccount) acc;
				out.println("-----------------------------");
				out.println("계좌번호> " + normal.getAccountNum());
				out.println("고객이름> " + normal.getName());
				out.println("잔고> " + normal.getMyMoney());
				out.println("기본이자> " + normal.getInter() +"%");
				out.println("-----------------------------");
			}
			else if (acc instanceof HighCreditAccount) {
				HighCreditAccount high = (HighCreditAccount) acc;
				out.println("-----------------------------");
				out.println("계좌번호> " + high.getAccountNum());
				out.println("고객이름> " + high.getName());
				out.println("잔고> " + high.getMyMoney());
				out.println("기본이자> " + high.getInter() +"%");
				out.println("신용등급> " + high.getCredit());
				out.println("-----------------------------");
			}
		}
	}
	
	public void autoSaveOn(AutoSaver t) {
		System.out.println("1.자동저장On, 2.자동저장Off");
		int auto = scan.nextInt();
		if(auto == 1) {
			if(t.isAlive()) {
				System.out.println("이미 자동저장이 실행중입니다.");
			}
			else {
				t.setDaemon(true);
				t.start();
			}
		}
		else if (auto == 2) {
			if(t.isAlive()) {
				t.interrupt();
			}
			else {
				System.out.println("이미 자동저장이 실행중이 아닙니다.");
			}
		}
		else {
			System.out.println("잘못된 입력입니다.");
		}
	}
}
