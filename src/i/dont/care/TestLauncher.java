package i.dont.care;

import i.dont.care.clientserver.Client;
import i.dont.care.clientserver.RequestProcessor;
import i.dont.care.tictactoe.clientside.model.Model;
import i.dont.care.tictactoe.clientside.mvc.IController;
import i.dont.care.tictactoe.clientside.mvc.IModel;
import i.dont.care.clientserver.Server;
import i.dont.care.tictactoe.clientside.controller.Controller;
import i.dont.care.tictactoe.serverside.TicTacToe;
import i.dont.care.tictactoe.clientside.view.console.ConsoleUI;

public class TestLauncher {
	
	public static final String START = "start";
	public static final String CONNECT = "connect";
	
	private static void serverStart(int port) {
		RequestProcessor processor = new TicTacToe();
		new Server(processor, port).start();
	}
	
	public static void main(String[] args) {
		
		String action = args[0];
		String ip = args[1];
		int port = Integer.parseInt(args[2]);
		
		if (action.equalsIgnoreCase(START)) {
			serverStart(port);

//			Client client = new Client(ip, port);
//			IModel model = new Model(client);
//			IController controller = new Controller(model);
//			ConsoleUI view = new ConsoleUI(controller);
//			model.addViewObserver(view);
//			view.printName();
		}
		
		if (action.equalsIgnoreCase(START) || action.equalsIgnoreCase(CONNECT)) {
			Client client = new Client(ip, port);
			IModel model = new Model(client);
			IController controller = new Controller(model);
			ConsoleUI view = new ConsoleUI(controller);
			model.addViewObserver(view);
			view.printName();
		}
		
		
		
	}
	
}
