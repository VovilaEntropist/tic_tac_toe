package i.dont.care.client;

import i.dont.care.message.MessageFactory;
import i.dont.care.mvc.IModel;
import i.dont.care.tictactoe.model.Player;
import i.dont.care.tictactoe.model.board.CellArray;
import i.dont.care.utils.Index;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Observable;
import java.util.Observer;

public class Client extends Observable implements IModel {
	
	private String ip;
	private int port;

	private Socket socket;
	
	private ObjectOutputStream outputStream;
	private ObjectInputStream inputStream;
	
	public Client(String ip, int port) {
		this.ip = ip;
		this.port = port;
	}
	
	private void init() throws IOException {
		socket = new Socket(ip, port);
		
		outputStream = new ObjectOutputStream(socket.getOutputStream());
		inputStream = new ObjectInputStream(socket.getInputStream());
	}
	
	@Override
	public void start() {
		new Thread(() -> {
			checkGame();
		})
	}
	
	@Override
	public void doMove(Player player, Index position) {
		try {
			outputStream.writeObject(MessageFactory.createMove(player, position));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void addPlayer(Player player) {
		try {
			outputStream.writeObject(MessageFactory.createConnectPlayer(player));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void removePlayer(Player player) {
		try {
			outputStream.writeObject(MessageFactory.createDisconnectPlayer(player));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void checkGame(Player player) {
		
	}
	
	@Override
	public void notifyGameStarted(CellArray board) {
		this.setChanged();
		this.notifyObservers(MessageFactory.createGameStarted(board));
	}
	
	@Override
	public void notifyPlayerEndsOfMove(Player targetPlayer) {
		this.setChanged();
		this.notifyObservers(MessageFactory.createEndMove(targetPlayer));
	}
	
	@Override
	public void notifyPlayerGoes(Player targetPlayer) {
		this.setChanged();
		this.notifyObservers(MessageFactory.createStartMove(targetPlayer));
	}
	
	@Override
	public void notifyBoardChanged(CellArray board) {
		this.setChanged();
		this.notifyObservers(MessageFactory.createBoardChanged(board));
	}
	
	@Override
	public void notifyPlayerWin(Player winner) {
		this.setChanged();
		this.notifyObservers(MessageFactory.createPlayerWin(winner));
	}
	
	@Override
	public void notifyGameEnded() {
		this.setChanged();
		this.notifyObservers(MessageFactory.createGameEnded());
	}
	
	@Override
	public void notifyPlayerKicked(Player targetPlayer, String reason) {
		this.setChanged();
		this.notifyObservers(MessageFactory.createKickPlayer(targetPlayer, reason));
	}
	
	@Override
	public void notifyInvalidMove(Player targetPlayer) {
		this.setChanged();
		this.notifyObservers(MessageFactory.createInvalidMove(targetPlayer));
	}
	
	@Override
	public synchronized void addObserverToModel(Observer o) {
		this.addObserver(o);
	}
}
