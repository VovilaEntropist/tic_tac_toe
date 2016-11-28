package i.dont.care.mvc;

import i.dont.care.tictactoe.model.Player;
import i.dont.care.tictactoe.model.board.CellArray;
import i.dont.care.utils.Index;

public interface IView {
	
	void doMove(Index index);
	
	void connect();
	
	void disconnect();
	
	void startGame(CellArray board);
	
	void prepareMove(Player targetPlayer);
	
	void endMove(Player targetPlayer);
	
	void updateBoard(CellArray board);
	
	void winPlayer(Player winner);
	
	void endGame();
	
	void kickPlayer(Player targetPlayer, String reason);
	
	void denyMove(Player targetPlayer);
	
}
