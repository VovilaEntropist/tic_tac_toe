package garbage.tictactoe;

public class Player {
	
	private PlayerType playerType;
	private char symbol;
	private String name;
	
	public Player(PlayerType playerType, char symbol, String name) {
		this.playerType = playerType;
		this.symbol = symbol;
		this.name = name;
	}
	
	public PlayerType getPlayerType() {
		return playerType;
	}
	
	public char getSymbol() {
		return symbol;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
}
