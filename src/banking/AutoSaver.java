package banking;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class AutoSaver extends Thread {
	AccountManager acc;

	public AutoSaver(AccountManager acc) {
		this.acc = acc;
	}
	
	@Override
	public void run() {
		while(true) {
			try {
				sleep(5000);
				PrintWriter out = new PrintWriter(new FileWriter("src/resource/AutoSaveAccount.txt", false));
																					  // false옵션으로 이미 있는 파일 덮어쓰기
				this.acc.autoSave(out); // 생성되어있는 Account 데이터를 저장
				System.out.println("\n자동저장 성공");
				// 스트림 소멸(닫기)
				out.close();
			} catch (InterruptedException e) {
				System.out.println("\n자동저장 Off");
				break;
			} catch (IOException e) {
				System.out.println("\n입출력스트림 오류");
			}
		}
	}
	
	
}
