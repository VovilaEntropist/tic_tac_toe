package i.dont.care.tictactoe.gamelogic;

import i.dont.care.mvc.Model;
import i.dont.care.tictactoe.gamelogic.board.CellArray;
import i.dont.care.utils.Index;

import java.util.Observable;

public class TicTacToe extends Observable implements Model {
	
	public static final int ROW_COUNT = 3;
	public static final int COLUMN_COUNT = 3;
	
	CellArray currentBoard;
	
	public TicTacToe() {
		currentBoard = new CellArray(ROW_COUNT, COLUMN_COUNT);
	}
	
	@Override
	public void doMove(Player player, Index index) {

	}
	
	@Override
	public void addPlayer(Player player) {
		
	}
	
	@Override
	public void removePlayer(Player player) {
		
	}
	
	@Override
	public void notifyGameStarted() {
		
	}
	
	@Override
	public void notifyPlayerGoes() {
		
	}
	
	@Override
	public void notifyBoardChanged() {
		
	}
	
	@Override
	public void notifyGameEnded() {
		
	}
	
	
}
