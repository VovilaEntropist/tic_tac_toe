package garbage.tictactoe;

import i.dont.care.utils.Index;

public class Board {
	
	private CellArray cells;
	
	public Board(int rows, int columns) {
		this.cells = new CellArray(rows, columns);
	}
	
	public Board(Board other) {
		this.cells = new CellArray(other.cells);
	}
	
	public Board(Board other, Index newMarkIndex, Cell newMark) {
		this(other);
		setMark(newMarkIndex, newMark);
	}
	
	public void setMark(Index newMarkIndex, Cell newMark) {
		this.cells.set(newMarkIndex, newMark);
	}
	
	
	
	public boolean isTie() {
		return cells.getEmptyCount() <= 0;
	}
}
