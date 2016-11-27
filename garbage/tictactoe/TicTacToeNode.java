package garbage.tictactoe;

import garbage.tictactoe.interfaces.INode;

public class TicTacToeNode implements INode {
	
	private Board board;
	
	public TicTacToeNode(Board board) {
		this.board = board;
	}
	
	@Override
	public boolean isWinFor(PlayerType playerType) {
		return false;
	}
	
	@Override
	public boolean isLooseFor(PlayerType playerType) {
		return false;
	}
	
	@Override
	public NodeCollection getChildren() {
		return null;
	}
}
