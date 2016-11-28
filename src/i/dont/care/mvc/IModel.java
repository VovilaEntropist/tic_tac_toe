package i.dont.care.mvc;

import i.dont.care.tictactoe.model.Player;
import i.dont.care.tictactoe.model.board.CellArray;
import i.dont.care.utils.Index;

public interface IModel {
	
	void doMove(Player player, Index position);
	
	void addPlayer(Player player);
	
	void removePlayer(Player player);
	
	void notifyGameStarted(CellArray cellArray);
	
	void notifyEndOfMove(Player targetPlayer);
	
	void notifyPlayerGoes(Player targetPlayer);
	
	void notifyBoardChanged(CellArray cellArray);
	
	void notifyPlayerWin(Player winner);
	
	void notifyGameEnded();
	
	void notifyKickPlayer(Player targetPlayer, String reason);
	
	void notifyInvalidMove(Player targetPlayer);
	
}
