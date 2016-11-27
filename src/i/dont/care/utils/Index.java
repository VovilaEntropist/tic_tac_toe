package i.dont.care.utils;

public class Index {
	
	private int row;
	private int column;
	
	public Index(int row, int column) {
		this.row = row;
		this.column = column;
	}
	
	public int row() {
		return row;
	}
	
	public int column() {
		return column;
	}
	
	public void setRow(int row) {
		this.row = row;
	}
	
	public void setColumn(int column) {
		this.column = column;
	}
}
