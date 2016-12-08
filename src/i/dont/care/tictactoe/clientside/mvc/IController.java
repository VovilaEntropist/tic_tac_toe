package i.dont.care.tictactoe.clientside.mvc;

import i.dont.care.tictactoe.serverside.Player;
import i.dont.care.utils.Index;

public interface IController {
	
	void doMove(Index position);
	
	void addPlayer(Player player);
	
	void removePlayer();
}
