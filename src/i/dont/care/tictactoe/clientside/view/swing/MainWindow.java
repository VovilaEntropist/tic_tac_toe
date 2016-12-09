package i.dont.care.tictactoe.clientside.view.swing;

import i.dont.care.clientserver.message.Message;
import i.dont.care.tictactoe.Configuration;
import i.dont.care.tictactoe.clientside.controller.Controller;
import i.dont.care.tictactoe.clientside.model.Model;
import i.dont.care.tictactoe.clientside.mvc.IController;
import i.dont.care.tictactoe.clientside.mvc.IModel;
import i.dont.care.tictactoe.clientside.mvc.IView;
import i.dont.care.tictactoe.clientside.view.swing.content.*;
import i.dont.care.tictactoe.clientside.view.swing.content.listener.ContentEvent;
import i.dont.care.tictactoe.clientside.view.swing.content.listener.ContentListener;
import i.dont.care.tictactoe.serverside.Player;
import i.dont.care.tictactoe.serverside.board.CellArray;
import i.dont.care.tictactoe.serverside.board.Mark;
import i.dont.care.utils.Index;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class MainWindow extends JFrame implements IView, ContentListener, Observer {
	
	private IController controller;
	private ContentCollection contents;
	private JPanel contentPanel;
	
	private JPanel messagePanel;
	
	private String playerNickName;
	private Mark playerMark;
	private String serverIp;
	private int serverPort;
	
	private boolean enabledMoves;
	
	
	public MainWindow(IController controller) throws HeadlessException {
		this.controller = controller;
		contents = new ContentCollection();
		init();
		initContent();
		validate();
	}
	
	private void initContent() {
		Rectangle contentBounds = new Rectangle(0, 0, contentPanel.getWidth(),
				contentPanel.getHeight());
		Content mainMenu =  new MainMenu(contentBounds, ContentType.MainMenu, this);
		Content playerChoice = new PlayerChoice(contentBounds, ContentType.PlayerChoice,
				this, mainMenu);
		Content serverCreate = new ServerCreate(contentBounds, ContentType.ServerCreate,
				this, mainMenu);
		Content serverConnect = new ServerConnect(contentBounds, ContentType.ServerConnect,
				this, mainMenu);
		Content gameContent = new Game(contentBounds, ContentType.Game, this);
		
		
		contents.put(ContentType.MainMenu, mainMenu);
		contents.put(ContentType.ServerCreate, serverCreate);
		contents.put(ContentType.ServerConnect, serverConnect);
		contents.put(ContentType.PlayerChoice, playerChoice);
		contents.put(ContentType.Game, gameContent);
		
		contents.show(ContentType.MainMenu);
		
		contentPanel.setLayout(null);
		
		contentPanel.add(mainMenu);
		contentPanel.add(serverCreate);
		contentPanel.add(serverConnect);
		contentPanel.add(playerChoice);
		contentPanel.add(gameContent);
	}
	
	private void init() {
		messagePanel = new JPanel();
		contentPanel = new JPanel();
		
		this.setLayout(new BorderLayout());
		this.add(contentPanel, BorderLayout.CENTER);
		
		this.setSize(500, 500);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	@Override
	public void handleContentEvent(Content content, ContentEvent event, Object[] args) {
		switch (event) {
			case ServerBtnClick:
				contents.show(ContentType.ServerCreate);
				break;
			case ConnectBtnClick:
				contents.show(ContentType.ServerConnect);
				break;
			case AiBtnClick:
				break;
			case ExitBtnClick:
				break;
			case BackPressed:
				Content from = (Content) args[0];
				if (from != null) {
					contents.show(from.getContentType());
				}
				break;
			case TileCilck:
				if (enabledMoves) {
					Index index = (Index) args[0];
					doMove(index);
				}
				break;
			case StartServerBtnClick:
				this.serverPort = (int) args[0];
				((BackButtonContent) contents.get(ContentType.PlayerChoice))
						.setFrom(contents.get(ContentType.ServerCreate));
				contents.show(ContentType.PlayerChoice);
				break;
			case ConnectServerBtnClick:
				this.serverIp = (String) args[0];
				this.serverPort = (int) args[1];
				
				((BackButtonContent) contents.get(ContentType.PlayerChoice))
						.setFrom(contents.get(ContentType.ServerConnect));
				contents.show(ContentType.PlayerChoice);
				break;
			case StartServer:
				this.playerNickName = (String) args[0];
				this.playerMark = (Mark) args[1];
				
				startServer(serverPort);
				connect(new Player(playerNickName, playerMark, false), serverIp, serverPort);
				break;
			case ConnectServer:
				this.playerNickName = (String) args[0];
				this.playerMark = (Mark) args[1];
								
				connect(new Player(playerNickName, playerMark, false), serverIp, serverPort);
				break;
			case Error:
				break;
		}
		

	}
	
	@Override
	public void doMove(Index position) {
		controller.doMove(position);
	}
	
	@Override
	public void connect(Player player, String ip, int port) {
		controller.addPlayer(player, ip, port);
	}
	
	@Override
	public void disconnect() {
		controller.removePlayer();
	}
	
	@Override
	public void startServer(int port) {
		controller.startServer(port);
	}
	
	@Override
	public void stopServer() {
		controller.stopServer();
	}
	
	private void startGame(CellArray board) {
		contents.show(ContentType.Game);
	}
	
	private void endMove(Player player) {
		enabledMoves = false;
	}
	
	private void prepareMove(Player player) {
		enabledMoves = true;
	}
	
	private void updateBoard(CellArray board) {
		((Game) contents.get(ContentType.Game)).getBoard().updateBoard(board);
		repaint();
	}
	
	@Override
	public void update(Observable o, Object arg) {
		//TODO Проверки и исключения тут сыпать
		Message message = (Message) arg;
		
		//TODO Проверки на нул или ещё что-то
		CellArray board = (CellArray) message.getParameter(Configuration.BOARD);
		Player player = (Player) message.getParameter(Configuration.PLAYER);
		String reason = (String) message.getParameter(Configuration.REASON);
		
		int command = message.getCommand();
		switch (command) {
			case Configuration.GAME_STARTED:
				startGame(board);
				break;
			case Configuration.END_OF_MOVE:
				endMove(player);
				break;
			case Configuration.START_OF_MOVE:
				prepareMove(player);
				break;
			case Configuration.BOARD_CHANGED:
				updateBoard(board);
				break;
			case Configuration.PLAYER_WIN:
				//winPlayer(player);
				break;
			case Configuration.GAME_ENDED:
				//endGame();
				break;
			case Configuration.KICK_PLAYER:
				//kickPlayer(player, reason);
				break;
			case Configuration.INVALID_MOVE:
				//denyMove(player);
				break;
			case Configuration.INVALID_COMMAND:
				//denyCommand();
				break;
			case Configuration.TIE:
				//endGameTie();
				break;
			case Configuration.CONNECTION_ERROR:
				//disconnect();
				System.out.println("Ошибка подключения: " + reason);
				break;
		}
	}
	
	
	public static void main(String[] args) {
		IModel model = new Model();
		IController controller = new Controller(model);
		MainWindow view = new MainWindow(controller);
		model.addViewObserver(view);
	}
	
	
}
