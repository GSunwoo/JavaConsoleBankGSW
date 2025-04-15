package banking;

import java.util.Scanner;

public interface ICustomDefine {
	// 스캐너
	Scanner scan = new Scanner(System.in);
	
	// 메인함수 선택지
	int MAKE = 1;
	int DEPOSIT = 2;
	int WITHDRAW = 3;
	int INQUIRE = 4;
	int DELETE = 5;
	int AUTO_SAVE = 6;
	int EXIT = 7;
	
	// 신용등급 별 추가이율
	double A_GRADE = 0.07;
	double B_GRADE = 0.04;
	double C_GRADE = 0.02;
	
}
