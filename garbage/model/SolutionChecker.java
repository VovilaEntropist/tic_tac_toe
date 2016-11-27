package garbage.model;

import i.dont.care.utils.Index;

public class SolutionChecker {
	
	private int chainLengthToWin;
	
	public SolutionChecker(int chainLengthToWin) {
		this.chainLengthToWin = chainLengthToWin;
	}
	
	public Win checkSolution(Board board) {
		CellArray cells = board.getCellArray();
		for (int i = 0; i < cells.getRowCount(); i++) {
			for (int j = 0; j < cells.getColumnCount(); j++) {
				Index index = new Index(i, j);
				if (checkDirect(board, index, Direction.Horizontally)
						|| checkDirect(board, index, Direction.Vertically)
						|| checkDirect(board, index, Direction.DeagonalyRightToLeft)
						|| checkDirect(board, index, Direction.DiagonalyLeftToRight)) {
					return cells.at(i, j).getCellType() == CellType.PlayerServer ? Win.ServerPlayer : Win.ClientPlayer;
				}
			}
		}
		
		return Win.Nobody;
	}

	
	private boolean checkDirect(Board board, Index index, Direction direction) {
		int counter = 0;
		CellArray cells = board.getCellArray();
		CellType currentCellType = cells.at(index.row(), index.column()).getCellType();
		for (int dx = -chainLengthToWin + 1; dx < chainLengthToWin; dx++) {
			int i = index.row();
			int j = index.column();
			
			switch (direction) {
				case Horizontally:
					j += dx;
					break;
				case Vertically:
					i += dx;
					break;
				case DiagonalyLeftToRight:
					i -= dx;
					j += dx;
					break;
				case DeagonalyRightToLeft:
					i += dx;
					j += dx;
					break;
			}

			if (j >= 0 && j < cells.getColumnCount()) {
				continue;
			}
			
			if (cells.at(i, j).getCellType() == currentCellType) {
				counter++;
			} else {
				counter = 0;
			}
		}
		
		return counter >= chainLengthToWin;
	}
	

	
	public enum Win {
		Nobody,
		ServerPlayer,
		ClientPlayer
	}
	
	private enum Direction {
		Horizontally,
		Vertically,
		DiagonalyLeftToRight,
		DeagonalyRightToLeft
	}
}
