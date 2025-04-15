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

public class AccountManager {
	
	private HashSet<Account> myAccount;
	AutoSaver t;
	
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
			choice = ICustomDefine.scan.nextInt();
			ICustomDefine.scan.nextLine();
			if (choice == 1 || choice == 2) break;
			else System.out.println("잘못된 입력입니다.");
		}
		
		System.out.print("계좌번호: ");
		String accNum = ICustomDefine.scan.nextLine();
		System.out.print("고객이름: ");
		String myName = ICustomDefine.scan.nextLine();
		System.out.print("잔고: ");
		int myMoney = ICustomDefine.scan.nextInt();
		ICustomDefine.scan.nextLine();
		System.out.print("기본이자%(정수형태로입력): ");
		int basicInter = ICustomDefine.scan.nextInt();
		ICustomDefine.scan.nextLine();
		
		if(myMoney<0) {
			System.out.println("초기잔고 입력이 잘못되었습니다");
			System.out.println(": 음의 정수입력");
		}
		if(myMoney%500!=0) {
			System.out.println("초기잔고 입력이 잘못되었습니다");
			System.out.println(": 500원단위로 입금가능");
		}
		
		Account newAcc = null;
		
		if(choice==1) {
			newAcc = new NormalAccount(accNum, myName, myMoney, basicInter);
		}
		else if(choice==2) {
			char credit = 'F';
			while(true) {
				System.out.print("신용등급(A,B,C등급): ");
				String crdStr = ICustomDefine.scan.nextLine();
				
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
			
			newAcc = new HighCreditAccount(accNum, myName, myMoney, basicInter, credit);
		}
		
		if(newAcc==null) return; // newAcc가 생성되지 않았을 경우 메서드 종료
		
		if(myAccount.add(newAcc)) { // 성공했을 경우 개설 완료 메세지
			System.out.println("계좌개설완료");
		}
		else { // 실패의 경우 중복이 있다고 판단
			while(true) {
				System.out.print("중복계좌발견됨. 덮어쓸까요?(y / n)");
				String yn = ICustomDefine.scan.nextLine();
				if(yn.equals("y") || yn.equals("Y")) {
					myAccount.remove(newAcc); // 기존의 계좌와 새로운 계좌가 같다고 판별이 났기 때문에 newAcc로 삭제하면 기존의 계좌가 삭제됨
					myAccount.add(newAcc); // 새로운 계좌를 set에 추가
					System.out.println("계좌 덮어쓰기 완료");
					break;
				} // y를 선택할 경우 기존 계좌를 삭제한 다음 새로운 계좌 추가
				else if (yn.equals("n") || yn.equals("N")) {
					System.out.println("기존 계좌를 유지합니다.");
					break;
				} // n을 선택할 경우 기존 계좌를 유지하므로 메세지만 출력
				else {
					System.out.println("y 또는 n을 입력하세요.");
				} // 잘못된 입력의 경우 while을 벗어나지 못함
			}
			
		} // 중복계좌 처리
		
	}// 계좌개설을 위한 메서드
	
	public void depositMoney() {
		System.out.println("계좌번호와 입금할 금액을 입력하세요.");
		System.out.print("계좌번호: ");
		String acc = ICustomDefine.scan.nextLine();
		System.out.print("입금액: ");
		int money = ICustomDefine.scan.nextInt();
		ICustomDefine.scan.nextLine();
		
		if(money<0) {
			System.out.println("양의 정수를 입력하세요.");
			return;
		}
		
		if(money%500!=0) {
			System.out.println("500원 단위로 입금가능합니다.");
			return;
		}
		
		Account nowAcc = searchAccount(acc);	// 계좌 검색
		
		if(nowAcc!=null) {	// 계좌검색 성공
			int calMoney = nowAcc.getNewBalance(money); // 변화금액 계산
			nowAcc.setMyMoney(calMoney);
			System.out.println(money+"원 입금이 완료되었습니다.");
			System.out.println("현재 잔고> " + nowAcc.getMyMoney());	
		}
		else {
			System.out.println(acc + " 계좌가 없습니다.");
			return;
		}
		
	}// 입금
	
	public void withdrawMoney() {
		System.out.println("계좌번호와 출금할 금액을 입력하세요.");
		System.out.print("계좌번호: ");
		String acc = ICustomDefine.scan.nextLine();
		System.out.print("출금액: ");
		int money = ICustomDefine.scan.nextInt();
		ICustomDefine.scan.nextLine();
		
		if(money<0) {
			System.out.println("양의 정수를 입력하세요.");
			return;
		} // 음수 출금 불가능
		
		if(money%1000!=0) { 
			System.out.println("1000원 단위로 출금가능합니다.");
			return;
		} // 1000원 단위 출금
		
		Account nowAcc = searchAccount(acc);
		
		if(nowAcc!=null) { // 계좌검색 성공
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
	}// 출금
	
	public void showAccInfo() {
		Iterator itr = myAccount.iterator();
		while(itr.hasNext()) {
			System.out.println("-----------------------------");
			Account acc = (Account) itr.next();
			
			acc.showInfo();
			
			System.out.println("-----------------------------");
		}
		System.out.println("전체 계좌 정보가 출력되었습니다");
	}// 전체계좌정보출력
	
	public void deleteAccount() {
		System.out.print("삭제할 계좌번호:");
		String deleteName = ICustomDefine.scan.nextLine();
		//삭제 여부 판단
		boolean isDelete = false;
		
		for(Account acc : myAccount) {
			// 계좌번호를 기준으로 판단
			if(deleteName.equals(acc.getAccountNum())) {
				myAccount.remove(acc);
				isDelete = true;
				break;
			}
		}
		
		if(isDelete) {
			System.out.println(">> 삭제되었습니다");
		}
		else {
			System.out.println(">> 해당 계좌가 없습니다.");
		}
	}	// 계좌 삭제
	
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
	} // obj 파일로 데이터 보내기
	
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
	} // obj 파일 데이터 불러오기
	
	public void autoSave(PrintWriter out){
		for(Account acc : myAccount) {
			acc.autoS(out);
		}
	} // 자동저장
	
	public void autoSaveOn() {
		System.out.println("***자동저장을 시작합니다***");
		try {
			System.out.printf("Thread[%s, %d, %s]\n", t.getName(), t.getPriority(), t.getThreadGroup().getName());
		} catch (Exception e) {
			System.out.println("AutoSaver 예외발생");
		}
		System.out.println("1.자동저장On, 2.자동저장Off");
		int autoOnOff = ICustomDefine.scan.nextInt();
		if(autoOnOff == 1) {
			if(t==null) {
				t = new AutoSaver(this);
			}
			if(t.isAlive()) {
				System.out.println("이미 자동저장이 실행중입니다.");
			}
			else {
				t.setDaemon(true);
				t.start();
				System.out.println("자동저장 On");
			}
		}
		else if (autoOnOff == 2) {
			if(t.isAlive()) {
				t.interrupt();
				t = null;
			}
			else {
				System.out.println("이미 자동저장이 실행중이 아닙니다.");
			}
		}
		else {
			System.out.println("잘못된 입력입니다.");
		}
	} // 자동저장 on/off
}
