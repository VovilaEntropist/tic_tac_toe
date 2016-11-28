package i.dont.care.tictactoe.model.board;

public enum Mark {
	Empty('_'),
	Player1('X'),
	Player2('0');
	
	private char value;
	
	Mark(char value) {
		this.value = value;
	}
	
	public char getChar() {
		return value;
	}
}
