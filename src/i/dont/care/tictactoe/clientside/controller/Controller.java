package i.dont.care.tictactoe.clientside.controller;

import i.dont.care.tictactoe.clientside.mvc.IController;
import i.dont.care.tictactoe.clientside.mvc.IModel;
import i.dont.care.tictactoe.serverside.Player;
import i.dont.care.utils.Index;

public class Controller implements IController {
	
	private IModel model;
	
	public Controller(IModel model) {
		this.model = model;
	}
	
	@Override
	public void doMove(Player player, Index position) {
		model.doMove(player, position);
	}
	
	@Override
	public void addPlayer(Player player) {
		model.addPlayer(player);
	}
	
	@Override
	public void removePlayer(Player player) {
		model.removePlayer(player);
	}
	
	@Override
	public void checkGame(Player player) {
		model.checkGame(player);
	}
}
