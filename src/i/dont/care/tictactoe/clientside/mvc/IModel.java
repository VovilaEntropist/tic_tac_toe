package i.dont.care.tictactoe.clientside.mvc;

import i.dont.care.tictactoe.serverside.Player;
import i.dont.care.tictactoe.serverside.board.CellArray;
import i.dont.care.utils.Index;

public interface IModel {
	
	void doMove(Index position);
	
	void addPlayer(Player player);
	
	void removePlayer();
		
	default void notifyGameStarted(CellArray cellArray) {
		
	}
	
	default void notifyPlayerGoes(Player targetPlayer) {
		
	}
	
	default void notifyBoardChanged(CellArray cellArray) {
		
	}
	
	default void notifyPlayerEndsOfMove(Player targetPlayer) {
		
	}
	
	default void notifyInvalidMove(Player targetPlayer) {
		
	}
	
	default void notifyPlayerKicked(Player targetPlayer, String reason) {
		
	}
	
	default void notifyPlayerWin(Player winner) {
		
	}
	
	default void notifyTie() {
		
	}
	
	default void notifyGameEnded() {
		
	}
	
}
