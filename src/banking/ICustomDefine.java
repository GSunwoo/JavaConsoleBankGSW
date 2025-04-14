package banking;

import java.util.Scanner;

public interface ICustomDefine {
	Scanner scan = new Scanner(System.in);
	
	int MAKE = 1;
	int DEPOSIT = 2;
	int WITHDRAW = 3;
	int INQUIRE = 4;
	int DELETE = 5;
	int AUTO_SAVE = 6;
	int EXIT = 7;
	
	double A_GRADE = 0.07;
	double B_GRADE = 0.04;
	double C_GRADE = 0.02;
	
//	public void showMenu();
//	public void makeAccount();
//	public void depositMoney();
//	public void withdrawMoney();
//	public void showAccInfo();
	
}
