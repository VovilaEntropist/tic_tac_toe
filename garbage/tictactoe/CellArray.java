package garbage.tictactoe;

import i.dont.care.utils.Index;
import i.dont.care.utils.IntWrapper;

import java.util.function.BiConsumer;

public class CellArray {
	
	private Cell[][] cells;
	
	public CellArray(int rows, int columns) {
		initArray(rows, columns);
	}
	
	public CellArray(CellArray other) {
		initArray(other.getRowCount(), other.getColumnCount());
		fillArray(other);
	}
	
	private void initArray(int rows, int columns) {
		cells = new Cell[rows][];
		for (int i = 0; i < rows; i++) {
			cells[i] = new Cell[columns];
			for (int j = 0; j < columns; j++) {
				cells[i][j] = Cell.Empty;
			}
		}
	}
	
	private void fillArray(CellArray other) {
		other.forEach((index, cell) ->
				cells[index.row()][index.column()] = cell);
	}
	
	public int getRowCount() {
		return cells.length;
	}
	
	public int getColumnCount() {
		return cells[0].length;
	}
	
	public Cell at(int row, int column) {
		return cells[row][column];
	}
	
	public void set(int row, int column, Cell mark) {
		cells[row][column] = mark;
	}
	
	public void set(Index index, Cell mark) {
		set(index.row(), index.column(), mark);
	}
	
	public void forEach(BiConsumer<Index, Cell> c) {
		for (int i = 0; i < getRowCount(); i++) {
			for (int j = 0; j < getColumnCount(); j++) {
				c.accept(new Index(i, j), cells[i][j]);
			}
		}
	}
	
	public int getEmptyCount() {
		IntWrapper counter = new IntWrapper();
		forEachEmpty((index, cell) -> counter.value++);
		return counter.value;
	}
	
	public void forEachEmpty(BiConsumer<Index, Cell> c) {
		forEach((index, cell) -> {
			if (cell == Cell.Empty) {
				c.accept(index, cell);
			}
		});
	}
	
}
