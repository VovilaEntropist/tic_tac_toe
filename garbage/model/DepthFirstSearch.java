package garbage.model;

import java.util.*;

public class DepthFirstSearch implements IGraphSearch {
	
	private List<Board> discoveredBoards = new ArrayList<>();
	
	private boolean isWin(SolutionChecker.Win win, boolean serverPlayerGoes) {
		if (win != SolutionChecker.Win.Nobody) {
			return serverPlayerGoes ? win == SolutionChecker.Win.ServerPlayer
					: win == SolutionChecker.Win.ClientPlayer;
		}
		
		return false;
	}
	
	public boolean isWinningMove(Board initial, boolean serverPlayerGoes, SolutionChecker checker) {
		if (isWin(checker.checkSolution(initial), serverPlayerGoes)) {
			return true;
		}
		
		Stack<Board> stack = new Stack<>();
		stack.push(initial);
		
		while (!stack.isEmpty()) {
			Board current = stack.pop();
			
			if (isWin(checker.checkSolution(initial), serverPlayerGoes)) {
				return true;
			}
			
			if (!isDiscovered(current)) {
				discoveredBoards.add(current);
				current.getAvailableMoves(serverPlayerGoes).forEach(stack::push);
			}
		}
		
		return false;
	}
	
	@Override
	public Board getBestMove(Board initial, boolean serverPlayerGoes, SolutionChecker checker) {
		Set<Board> set = initial.getAvailableMoves(serverPlayerGoes);
		
		for (Board board : set) {
			if(isWinningMove(board, serverPlayerGoes, checker)) {
				return board;
			}
		}
		
		return null;
	}
	
	public boolean isDiscovered(Board board) {
		Collections.sort(discoveredBoards);
		return Collections.binarySearch(discoveredBoards, board) >= 0;
	}
}
