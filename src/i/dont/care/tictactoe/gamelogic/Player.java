package i.dont.care.tictactoe.gamelogic;

import i.dont.care.tictactoe.gamelogic.board.Mark;

public class Player {
	
	private String nickname;
	private Mark mark;
	private boolean ai;
	
	public Player(String nickname, Mark mark, boolean ai) {
		this.nickname = nickname;
		this.mark = mark;
		this.ai = ai;
	}
	
	public String getNickname() {
		return nickname;
	}
	
	public Mark getMark() {
		return mark;
	}
	
	public boolean isAi() {
		return ai;
	}
}
