package garbage.model;

public class Game {
	
	private IGraphSearch graphSearch;
	private SolutionChecker solutionChecker;
	private boolean serverPlayerGoes;
	private Board currentBoard;
	
	private void moveAI() {
		Board bestMove = graphSearch.getBestMove(currentBoard, serverPlayerGoes, solutionChecker);
		
		if (bestMove == null) {
			bestMove = currentBoard.getNearestMove(serverPlayerGoes);
		}
		
		currentBoard = bestMove;
	}
	
	private void movePlayer() {
		
	}
	
	private void print() {
		
	}
	
	
	
	private void start() {
		SolutionChecker.Win win = SolutionChecker.Win.Nobody;
		while(true) {
			moveAI();
			if (solutionChecker.checkSolution(currentBoard) == ;
			print();
			movePlayer();
						
			
		}
	}

	
}
