package i.dont.care.tictactoe.view.console;

import i.dont.care.clientserver.message.Message;
import i.dont.care.mvc.IController;
import i.dont.care.mvc.IView;
import i.dont.care.tictactoe.model.Player;
import i.dont.care.tictactoe.model.board.CellArray;
import i.dont.care.utils.Index;

import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;

public class ConsoleUI implements IView, Observer {
	
	private IController controller;
	private Player player;
	private Scanner scanner;
	
	public ConsoleUI(IController controller, Player player) {
		this.controller = controller;
		this.scanner = new Scanner(System.in);
		this.player = player;
	}
	
	public void start() {
		connect(player);
	}
	
	private void printBoard(CellArray board) {
		System.out.println(board + "\n");
	}
	
	private Index getIndexByNumPad(int num) {
		int i = (9 - num) / 3;
		int j = 2 - ((9 - num) % 3);
		return new Index(i, j);
	}
	
	@Override
	public void doMove(Player player, Index index) {
		controller.doMove(player, index);
	}
	
	@Override
	public void connect(Player player) {
		controller.addPlayer(player);
	}
	
	@Override
	public void disconnect(Player player) {
		controller.removePlayer(player);
	}
	
	@Override
	public void startGame(CellArray board) {
		printBoard(board);
	}
	
	@Override
	public void prepareMove(Player targetPlayer) {
		if (!player.equals(targetPlayer)) {
			return;
		}
		
		System.out.println("Выбите ход (1-9): ");
		int num = scanner.nextInt();
		doMove(player, getIndexByNumPad(num));
	}
	
	@Override
	public void endMove(Player targetPlayer) {
//		if (!player.equals(targetPlayer)) {
//			return;
//		}
	}
	
	@Override
	public void updateBoard(CellArray board) {
		printBoard(board);
	}
	
	@Override
	public void winPlayer(Player winner) {
		System.out.println("Выиграл " + winner.getNickname());
	}
	
	@Override
	public void endGame() {
		disconnect(player);
	}
	
	@Override
	public void kickPlayer(Player targetPlayer, String reason) {
		
	}
	
	@Override
	public void denyMove(Player targetPlayer) {
		
	}

	@Override
	public void update(Observable o, Object arg) {
		//TODO Проверки и исключения тут сыпать
		Message message = (Message) arg;
		
		//TODO Проверки на нул или ещё что-то
		CellArray board = (CellArray) message.getParameter(Message.BOARD);
		Player player = (Player) message.getParameter(Message.PLAYER);
		String reason = (String) message.getParameter(Message.REASON);
		
		int command = message.getCommand();
		switch (command) {
			case Message.GAME_STARTED:
				startGame(board);
				break;
			case Message.END_OF_MOVE:
				endMove(player);
				break;
			case Message.START_OF_MOVE:
				prepareMove(player);
				break;
			case Message.BOARD_CHANGED:
				updateBoard(board);
				break;
			case Message.PLAYER_WIN:
				winPlayer(player);
				break;
			case Message.GAME_ENDED:
				endGame();
				break;
			case Message.KICK_PLAYER:
				kickPlayer(player, reason);
				break;
			case Message.INVALID_MOVE:
				denyMove(player);
				break;
		}
	}
}
