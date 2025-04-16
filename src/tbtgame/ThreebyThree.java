package tbtgame;

import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;

public class ThreebyThree {
	private HashSet<Point> tbt;
	
	int width;
	int height;
	
	public static Scanner scan = new Scanner(System.in);
	
	boolean initC = false;
	
	public ThreebyThree( int width, int height) {
		this.width = width;
		this.height = height;
		tbt = new HashSet<Point>();
	}

	public void makeTable() {
		int count = 1;
		for(int i = 0; i<width;i++) {
			for(int j = 0; j<height ; j++) {
				String num = Integer.toString(count++);
				Point p = new Point(i, j, num);
				tbt.add(p);
			}
		}
		Point p = findPoint(width-1, height-1);
		p.setVal("x");
	}
	
	public Point findPoint(int x, int y) {
		for(Point p : tbt) {
			if(p.getX()==x && p.getY()==y) {
				return p;
			}
		}
		return null;
	}
	
	public Point findX() {
		for(Point p : tbt) {
			if(p.getVal().equals("x")) {
				return p;
			}
		}
		return null;
	}
	
	public void printTable() {
		System.out.println("=====");
		for(int i = 0; i<width;i++) {
			for(int j = 0; j<height ; j++) {
				System.out.print(findPoint(i, j).getVal() + " ");
			}
			System.out.println();
		}
		System.out.println("=====");
	}
	
	public void change(Point x, Point num) {
		Point temp = new Point(x.getX(), x.getY(), x.getVal());
		x.setX(num.getX());
		x.setY(num.getY());
		num.setX(temp.getX());
		num.setY(temp.getY());
	}
	
	public void left() {
		Point p = findX();
		Point n = findPoint(p.getX(), p.getY()+1);
		if(n==null) {
			if(initC) System.out.println("이동할 수 없습니다.");
			return;
		}
		change(p,n);
	}
	
	public void right() {
		Point p = findX();
		Point n = findPoint(p.getX(), p.getY()-1);
		if(n==null) {
			if(initC) System.out.println("이동할 수 없습니다.");
			return;
		}
		change(p,n);
		
	}
	
	public void up() {
		Point p = findX();
		Point n = findPoint(p.getX()+1, p.getY());
		if(n==null) {
			if(initC) System.out.println("이동할 수 없습니다.");
			return;
		}
		change(p,n);
	}
	
	public void down() {
		Point p = findX();
		Point n = findPoint(p.getX()-1, p.getY());
		if(n==null) {
			if(initC) System.out.println("이동할 수 없습니다.");
			return;
		}
		change(p,n);
	}
	
	public void sfTable() {
		Random rd = new Random();
		
		for(int i = 0; i<100 ; i++) {
			int sf = rd.nextInt()%4;
			
			switch (sf) {
			case 0: 
				left();
				break;
			case 1:
				right();
				break;
			case 2:
				up();
				break;
			case 3:
				down();
			}
		}
		initC = true;
	}
	
	public boolean checkAnswer() {
		int count = 1;
		for(int i = 0; i<width;i++) {
			for(int j = 0; j<height ; j++) {
				String num = Integer.toString(count++);
				if(!num.equals(findPoint(i, j).getVal())) {
					if(i!=width-1 && j !=height-1) {
						return false;
					}
					else {
						if(!findPoint(i, j).getVal().equals("x")) {
							return false;
						}
					}
				}
			}
		}
		initC = false;
		return true;
	}
	
	public static void main(String[] args) {
		ThreebyThree tbt = new ThreebyThree(3,3);
		
		tbt.makeTable();
		while(true) {
			tbt.sfTable();
			tbt.printTable();
			
			while(true) {
				System.out.println("[이동] |W:up| |A:left| |S:down| |D:right|");
				System.out.println("[종료] |X:exit|");
				String choice = scan.nextLine().toLowerCase();
				
				switch (choice) {
				case "a": 
					tbt.left();
					break;
				case "d":
					tbt.right();
					break;
				case "w":
					tbt.up();
					break;
				case "s":
					tbt.down();
					break;
				case "x":
					System.out.println("게임 종료");
					System.exit(0);
				}
				tbt.printTable();
				if(tbt.checkAnswer()) {
					System.out.println("정답입니다.");
				}
				System.out.println("재시작하려면 r, 종료하려면 아무키나 입력하세요");
				choice = scan.nextLine().toLowerCase();
				if(choice.equals("r")) {
					System.out.println("재시작합니다.");
				}
				else {
					System.out.println("게임 종료");
					System.exit(0);
				}
			}
			
		}
	}
}

