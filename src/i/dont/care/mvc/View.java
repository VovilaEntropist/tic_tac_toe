package i.dont.care.mvc;

import i.dont.care.tictactoe.gamelogic.Player;
import i.dont.care.tictactoe.gamelogic.board.CellArray;

public interface View {
	
	void doMove();
	
	void connect();
	
	void disconnect();
	
	void startGame(CellArray board);
	
	void prepareMove(CellArray board);
	
	void updateBoard(CellArray board);
	
	void endGame(CellArray board, Player winner);
	
	void denyMove(CellArray board);
	
}
