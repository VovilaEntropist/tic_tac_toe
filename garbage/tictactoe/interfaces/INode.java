package garbage.tictactoe.interfaces;

import garbage.tictactoe.NodeCollection;
import garbage.tictactoe.PlayerType;

public interface INode {

	boolean isWinFor(PlayerType playerType);
	
	boolean isLooseFor(PlayerType playerType);
	
	NodeCollection getChildren();
}
