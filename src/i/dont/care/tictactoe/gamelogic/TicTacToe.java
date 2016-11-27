package i.dont.care.tictactoe.gamelogic;

import i.dont.care.mvc.Model;
import i.dont.care.tictactoe.gamelogic.ai.Step;
import i.dont.care.tictactoe.gamelogic.ai.TicTacToeChecker;
import i.dont.care.tictactoe.gamelogic.ai.TicTacToeNode;
import i.dont.care.tictactoe.gamelogic.board.CellArray;
import i.dont.care.tictactoe.gamelogic.board.Mark;
import i.dont.care.utils.Index;
import i.dont.care.utils.ObjectCollection;

import java.util.Observable;

public class TicTacToe extends Observable implements Model {
	
	public static final int ROW_COUNT = 3;
	public static final int COLUMN_COUNT = 3;
	private static final int PLAYER_COUNT = 2;
	private static final int CHAIN_LENGTH = 3;
	
	private final CellArray currentBoard;
	private Step lastStep;
	private final PlayerCollection players;
	
	public TicTacToe() {
		players = new PlayerCollection();
		currentBoard = new CellArray(ROW_COUNT, COLUMN_COUNT);
	}
	
	@Override
	public void doMove(Player player, Index index) {
		if (!currentBoard.isValidIndex(index)
				&& currentBoard.at(index).getMark() != Mark.Empty) {
			notifyInvalidMove(player);
			return;
		}
		
		Mark mark = player.getMark();

		if (lastStep != null && mark != lastStep.getMark()) {
			notifyInvalidMove(player);
		} else {
			lastStep = new Step(index, mark);
		}
		
		currentBoard.set(index, mark);
		notifyEndOfMove(player);
		notifyBoardChanged(currentBoard);
		
		TicTacToeChecker checker = new TicTacToeChecker(mark, CHAIN_LENGTH);
		if (checker.isDecision(new TicTacToeNode(currentBoard, new Step(index, mark)))) {
			notifyPlayerWin(player);
			notifyGameEnded();
		} else if (currentBoard.getEmptyCount() == 0) {
			notifyPlayerWin(null);
			notifyGameEnded();
		} else {
			notifyPlayerGoes(players.getNext(player));
		}
	}
	
	@Override
	public void addPlayer(Player player) {
		if (players.size() >= PLAYER_COUNT) {
			notifyBanPlayer(player, "Игроков слишком много");
			return;
		}
		
		if (players.isNameTaken(player)) {
			notifyBanPlayer(player, "Такое имя уже используется");
			return;
		}
		
		if (players.isMarkTaken(player)) {
			notifyBanPlayer(player, "Такая отметка уже используется");
			return;
		}
		
		players.add(player);
		
		if (players.size() == PLAYER_COUNT) {
			notifyGameStarted(currentBoard);
		}
	}
	
	@Override
	public void removePlayer(Player player) {
		if (players.remove(player)) {
			notifyGameEnded();
		}
	}
	
	@Override
	public void notifyGameStarted(CellArray cellArray) {
		this.setChanged();
		this.notifyObservers(new ObjectCollection((cellArray)));
	}
	
	@Override
	public void notifyEndOfMove(Player player) {
		this.setChanged();
		this.notifyObservers(new ObjectCollection(player));
	}
	
	@Override
	public void notifyPlayerGoes(Player player) {
		this.setChanged();
		this.notifyObservers(new ObjectCollection(player));
	}
	
	@Override
	public void notifyBoardChanged(CellArray cellArray) {
		this.setChanged();
		this.notifyObservers(new ObjectCollection(cellArray));
	}
	
	@Override
	public void notifyPlayerWin(Player player) {
		this.setChanged();
		this.notifyObservers(new ObjectCollection(player));
	}
	
	@Override
	public void notifyGameEnded() {
		this.setChanged();
		this.notifyObservers();
	}
	
	@Override
	public void notifyBanPlayer(Player player, String reason) {
		this.setChanged();
		this.notifyObservers(new ObjectCollection(player, reason));
	}
	
	@Override
	public void notifyInvalidMove(Player player) {
		this.setChanged();
		this.notifyObservers(new ObjectCollection(player));
	}

}
