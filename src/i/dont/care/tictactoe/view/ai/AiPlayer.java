package i.dont.care.tictactoe.view.ai;

import i.dont.care.mvc.IController;
import i.dont.care.mvc.IView;
import i.dont.care.tictactoe.gamelogic.Player;
import i.dont.care.tictactoe.gamelogic.board.CellArray;
import i.dont.care.message.ObjectCollection;

import java.util.Observable;
import java.util.Observer;

public class AiPlayer implements IView, Observer {
	
	private Player player;
	private IController controller;
	
	public AiPlayer(Player player) {
		this.player = player;
	}
	
	@Override
	public void doMove() {
		//TODO генерация хода
		//controller.doMove(player);
	}
	
	@Override
	public void connect() {
		controller.addPlayer(player);
	}
	
	@Override
	public void disconnect() {
		controller.removePlayer(player);
	}
	
	@Override
	public void startGame(CellArray board) {
		
	}
	
	@Override
	public void prepareMove(CellArray board) {
		
	}
	
	@Override
	public void updateBoard(CellArray board) {
		
	}
	
	@Override
	public void endGame(CellArray board, Player winner) {
		
	}
	
	@Override
	public void denyMove(CellArray board) {
		
	}
	
	@Override
	public void update(Observable o, Object arg) {
		//TODO Тут по хорошему нужна проерка на тип и если что, то бахать исключения.
		ObjectCollection objects = (ObjectCollection) arg;
		
		
	}
}
