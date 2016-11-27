package garbage.model;

public interface IGraphSearch {
	
	Board getBestMove(Board initial, boolean serverPlayerGoes, SolutionChecker solutionChecker);
	
}
