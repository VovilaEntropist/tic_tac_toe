package i.dont.care.tictactoe.controller;

import i.dont.care.mvc.IController;
import i.dont.care.mvc.IModel;
import i.dont.care.tictactoe.model.Player;
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
}
