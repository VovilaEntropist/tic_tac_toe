package i.dont.care.tictactoe.serverside.board;

public class Cell {
	
	private Mark mark;
	
	public Cell() {
		this.mark = Mark.Empty;
	}
	
	public Cell(Mark mark) {
		this.mark = mark;
	}
	
	public Mark getMark() {
		return mark;
	}
	
	public void setMark(Mark mark) {
		this.mark = mark;
	}
	
	@Override
	public String toString() {
		return String.valueOf(mark.getChar());
	}
}
