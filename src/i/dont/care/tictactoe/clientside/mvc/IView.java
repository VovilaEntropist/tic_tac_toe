package i.dont.care.tictactoe.clientside.mvc;

import i.dont.care.tictactoe.serverside.Player;
import i.dont.care.utils.Index;

public interface IView {
	
	void doMove(Player player, Index position);
	
	void connect(Player player);
	
	void disconnect(Player player);
	
	void checkGame(Player player);
}
