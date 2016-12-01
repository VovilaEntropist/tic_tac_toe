package i.dont.care.mvc;

import i.dont.care.tictactoe.model.Player;
import i.dont.care.tictactoe.model.board.CellArray;
import i.dont.care.utils.Index;

public interface IView {
	
	void doMove(Player player, Index position);
	
	void connect(Player player);
	
	void disconnect(Player player);

	void startGame(CellArray board);
	
	void prepareMove(Player targetPlayer);
	
	void updateBoard(CellArray board);
	
	void endMove(Player targetPlayer);
	
	void denyMove(Player targetPlayer);
	
	void kickPlayer(Player targetPlayer, String reason);
	
	void winPlayer(Player winner);
	
	void endGame();
	
	
	
	
	
}
