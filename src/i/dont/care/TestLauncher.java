package i.dont.care;

import i.dont.care.client.Client;
import i.dont.care.tictactoe.clientside.mvc.IController;
import i.dont.care.tictactoe.clientside.mvc.IModel;
import i.dont.care.tictactoe.clientside.mvc.IView;
import i.dont.care.clientserver.Server;
import i.dont.care.tictactoe.clientside.controller.Controller;
import i.dont.care.tictactoe.serverside.Player;
import i.dont.care.tictactoe.serverside.TicTacToe;
import i.dont.care.tictactoe.serverside.board.Mark;
import i.dont.care.tictactoe.clientside.view.console.ConsoleUI;

public class TestLauncher {
	
	public static final String START = "start";
	public static final String CONNECT = "connect";
	
	private static void serverStart(int port) {
		
		IModel model = new TicTacToe();
		new Server(model, port).start();
	}
	
	public static void main(String[] args) {
		
		String action = args[0];
		String playerName = args[1];
		
		if (action.equalsIgnoreCase(START)) {
			int port = Integer.parseInt(args[2]);
			serverStart(port);
			
			IModel model = new Client("localhost", port);
			IController controller = new Controller(model);
			IView view = new ConsoleUI(controller, new Player(playerName, Mark.Player1, false));
		}
		
		if (action.equalsIgnoreCase(START) || action.equalsIgnoreCase(CONNECT)) {
			
		}
		
		
		
	}
	
}
