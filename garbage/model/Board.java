package garbage.model;

import i.dont.care.utils.DoubleWrapper;
import i.dont.care.utils.Index;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Board implements Comparable<Board> {
	
	private CellArray cells;
	
	public Board(int rows, int columns) {
		this.cells = new CellArray(rows, columns);
	}
	
	private Board(Board other) {
		this.cells = new CellArray(other.getCellArray());
	}
	
	public Set<Board> getAvailableMoves(boolean serverPlayerGoes) {
		Set<Board> moves = new HashSet<Board>();
		CellType currentCellType = serverPlayerGoes ? CellType.PlayerServer : CellType.PlayerClient;
		
		this.cells.forEachEmpty((index, cell) -> {
			Board board = new Board(this);
			board.getCellArray().at(index.row(), index.column()).setCellType(currentCellType);
			moves.add(board);
		});
		
		return moves;
	}
	
	public Board getNearestMove(boolean serverPlayerGoes) {
		if (cells.getEmptyCount() <= 0) {
			return null;
		}
		
		List<Index> targetIndexes = new ArrayList<>();
		cells.forEach((index, cell) -> {
			if (serverPlayerGoes ? cell.getCellType() == CellType.PlayerServer
					: cell.getCellType() == CellType.PlayerClient) {
				targetIndexes.add(index);
			}
		});
		
		DoubleWrapper minDif = new DoubleWrapper(calcHypotenuse(cells.getRowCount(), cells.getColumnCount()));
		Index resultIndex = new Index(0, 0);
		cells.forEachEmpty((index, cell) -> {
			for (Index targetIndex : targetIndexes) {
				double difference = calcHypotenuse(index.row() - targetIndex.row(),
						index.column() - targetIndex.column());
				if (difference < minDif.value) {
					minDif.value = difference;
					resultIndex.setRow(index.row());
					resultIndex.setColumn(index.column());
				}
			}
		});
		
		Board resultBoard = new Board(this);
		resultBoard.getCellArray().at(resultIndex.row(), resultIndex.column())
				.setCellType(serverPlayerGoes ? CellType.PlayerServer : CellType.PlayerClient);
		
		return resultBoard;
	}
	
	private double calcHypotenuse(double side1, double side2) {
		return Math.sqrt(Math.pow(side1, 2) + Math.pow(side2, 2));
	}
	
	
	public CellArray getCellArray() {
		return this.cells;
	}
	
	@Override
	public int compareTo(Board o) {
		return this.cells.formLineBoard().compareTo(o.getCellArray().formLineBoard());
	}
	

}
