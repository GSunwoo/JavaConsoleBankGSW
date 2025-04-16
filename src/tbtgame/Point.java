package tbtgame;

public class Point {
	int x;
	int y;
	String val;
	
	public Point(int x, int y, String val) {
		super();
		this.x = x;
		this.y = y;
		this.val = val;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public String getVal() {
		return val;
	}

	public void setVal(String val) {
		this.val = val;
	}
	
}
