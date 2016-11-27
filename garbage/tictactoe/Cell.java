package garbage.tictactoe;

public enum Cell {
	Empty('_'),
	PlayerServer('X'),
	PlayerClient('0');
	
	private char value;
	
	Cell(char value) {
		this.value = value;
	}
	
	public char getChar() {
		return value;
	}
}
