package i.dont.care.mvc;

import i.dont.care.tictactoe.model.Player;
import i.dont.care.tictactoe.model.board.CellArray;
import i.dont.care.utils.Index;

import java.util.Observer;

public interface IModel {
	
	void start();
	
	void doMove(Player player, Index position);
	
	void addPlayer(Player player);
	
	void removePlayer(Player player);
	
	void checkGame(Player player);
	
	
	void notifyGameStarted(CellArray cellArray);
	
	void notifyPlayerGoes(Player targetPlayer);
	
	void notifyBoardChanged(CellArray cellArray);
	
	void notifyPlayerEndsOfMove(Player targetPlayer);
	
	void notifyInvalidMove(Player targetPlayer);
	
	void notifyPlayerKicked(Player targetPlayer, String reason);
	
	void notifyPlayerWin(Player winner);
	
	void notifyGameEnded();
	
	
	void addObserverToModel(Observer o);
	
}
