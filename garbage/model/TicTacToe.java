package garbage.model;

public class TicTacToe extends SolutionChecker {
	
	public static final int CHAIN_LENGTH_TO_WIN = 3;
	
	public TicTacToe() {
		super(CHAIN_LENGTH_TO_WIN);
	}
	
	@Override
	public Win checkSolution(Board board) {
		
	}
}
