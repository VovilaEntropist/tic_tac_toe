package garbage.model;

public enum CellType {
	Empty('_'),
	PlayerServer('X'),
	PlayerClient('0');
	
	private char value;
	
	CellType(char value) {
		this.value = value;
	}
	
	public char getChar() {
		return value;
	}
}
