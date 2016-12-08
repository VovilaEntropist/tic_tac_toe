package i.dont.care.tictactoe.serverside;

import i.dont.care.tictactoe.serverside.board.Mark;

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
	
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		
		Player player = (Player) o;
		
		if (ai != player.ai) return false;
		if (nickname != null ? !nickname.equals(player.nickname) : player.nickname != null) return false;
		return mark == player.mark;
	}
	
	@Override
	public int hashCode() {
		int result = nickname != null ? nickname.hashCode() : 0;
		result = 31 * result + (mark != null ? mark.hashCode() : 0);
		result = 31 * result + (ai ? 1 : 0);
		return result;
	}
}
