package i.dont.care.message;

import i.dont.care.tictactoe.model.Player;
import i.dont.care.tictactoe.model.board.CellArray;

public class MessageFactory {
	
	public static Message createGameStarted(CellArray board) {
		Message message = new Message(Message.GAME_STARTED);
		message.addParameter(Message.BOARD, board);
		return message;
	}
	
	public static Message createEndMove(Player player) {
		Message message = new Message(Message.END_OF_MOVE);
		message.addParameter(Message.PLAYER, player);
		return message;
	}
	
	public static Message createStartMove(Player player) {
		Message message = new Message(Message.START_OF_MOVE);
		message.addParameter(Message.PLAYER, player);
		return message;
	}
	
	public static Message createBoardChanged(CellArray board) {
		Message message = new Message(Message.BOARD_CHANGED);
		message.addParameter(Message.BOARD, board);
		return message;
	}
	
	public static Message createPlayerWin(Player player) {
		Message message = new Message(Message.PLAYER_WIN);
		message.addParameter(Message.PLAYER, player);
		return message;
	}
	
	public static Message createGameEnded() {
		Message message = new Message(Message.GAME_ENDED);
		return message;
	}
	
	public static Message createKickPlayer(Player player, String reason) {
		Message message = new Message(Message.KICK_PLAYER);
		message.addParameter(Message.PLAYER, player);
		message.addParameter(Message.REASON, reason);
		return message;
	}
	
	public static Message createInvalidMove(Player player) {
		Message message = new Message(Message.INVALID_MOVE);
		message.addParameter(Message.PLAYER, player);
		return message;
	}
}
