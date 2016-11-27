package i.dont.care.mvc;

import i.dont.care.tictactoe.gamelogic.Player;
import i.dont.care.tictactoe.gamelogic.board.CellArray;
import i.dont.care.utils.Index;

public interface Model {
	
	void doMove(Player player, Index position);
	
	void addPlayer(Player player);
	
	void removePlayer(Player player);
	
	void notifyGameStarted(CellArray cellArray);
	
	void notifyEndOfMove(Player player);
	
	void notifyPlayerGoes(Player player);
	
	void notifyBoardChanged(CellArray cellArray);
	
	void notifyPlayerWin(Player player);
	
	void notifyGameEnded();
	
	void notifyBanPlayer(Player player, String reason);
	
	void notifyInvalidMove(Player player);
	
}
