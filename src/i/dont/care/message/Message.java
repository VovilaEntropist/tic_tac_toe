package i.dont.care.message;

import java.io.Serializable;

public class Message implements Serializable {
	
	public static final int GAME_STARTED = 0;
	public static final int END_OF_MOVE = 1;
	public static final int START_OF_MOVE = 2;
	public static final int BOARD_CHANGED = 3;
	public static final int PLAYER_WIN = 4;
	public static final int GAME_ENDED = 5;
	public static final int KICK_PLAYER = 6;
	public static final int INVALID_MOVE = 7;
	
	public static final String BOARD = "board";
	public static final String PLAYER = "player";
	public static final String REASON = "reason";
	
	private int command;
	private ParameterCollection parameters;
	
	public Message(int command, ParameterCollection parameters) {
		this.command = command;
		this.parameters = parameters;
	}
	
	public Message(int command) {
		this(command, new ParameterCollection());
	}
	
	public int getCommand() {
		return command;
	}
	
	public void addParameter(String key, Object value) {
		parameters.put(key, value);
	}
	
	public void removeParameter(String key) {
		parameters.remove(key);
	}
	
	public Object getParameter(String key) {
		return parameters.get(key);
	}
}
