package i.dont.care.mvc;

import i.dont.care.tictactoe.gamelogic.Player;
import i.dont.care.utils.Index;

public interface Model {
	
	void doMove(Player player, Index position);
	
	void addPlayer(Player player);
	
	void removePlayer(Player player);
	
	void notifyGameStarted();
	
	void notifyPlayerGoes();
	
	void notifyBoardChanged();
	
	void notifyGameEnded();
	
}