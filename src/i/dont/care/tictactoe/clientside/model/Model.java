package i.dont.care.tictactoe.clientside.model;

import i.dont.care.clientserver.Client;
import i.dont.care.clientserver.message.Message;
import i.dont.care.clientserver.message.MessageCollection;
import i.dont.care.clientserver.message.MessageFactory;
import i.dont.care.tictactoe.Configuration;
import i.dont.care.tictactoe.clientside.mvc.IModel;
import i.dont.care.tictactoe.serverside.Player;
import i.dont.care.tictactoe.serverside.board.CellArray;
import i.dont.care.utils.Index;

import java.util.Observable;

public class Model extends Observable implements IModel {
	
	public static final int CELLS = 3;
	public static final int ROWS = 3;
	
	private Player player;
	private CellArray board;
	private boolean exit = false;
	private Client client;
	
	public Model(Client client) {
		this.client = client;
	}
	
	public void start() {
		new Thread(() -> {
			while (!exit) {
				checkGame();
				
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();
	}
	
	private void checkGame() {
		handleResponse(client.request(MessageFactory.createGetGameState(player)));
	}
	
	@Override
	public void doMove(Index position) {
		handleResponse(client.request(MessageFactory.createMove(player, position)));
	}
	
	@Override
	public void addPlayer(Player player) {
		this.player = player;
		handleResponse(client.connect(player));
		start();
	}
	
	@Override
	public void removePlayer() {
		handleResponse(client.disconect(player));
		exit = true;
	}
	
	private void handleResponse(MessageCollection response) {
		for (Message message : response) {
			
			switch (message.getCommand()) {
				case Configuration.BOARD_CHANGED:
					CellArray board = (CellArray) message.getParameter(Configuration.BOARD);
					if (this.board == null) {
						this.board = new CellArray(ROWS, CELLS);
					}
					
					if (!this.board.equals(board)) {
						this.board = board.copy();
					}
					break;
				case Configuration.GAME_ENDED:
					exit = true;
				default:
					setChanged();
					notifyObservers(message);
			}
		}
	}
	
}
