package i.dont.care.tictactoe.clientside.view.swing.content;

import i.dont.care.tictactoe.clientside.view.swing.Board;
import i.dont.care.tictactoe.clientside.view.swing.ContentType;
import i.dont.care.tictactoe.clientside.view.swing.ImageLoader;
import i.dont.care.tictactoe.clientside.view.swing.content.listener.ContentListener;

import java.awt.*;
import java.io.File;

public class Game extends Content {
	
	private Board board;
	
	public Game(Rectangle rectangle, ContentType contentType, ContentListener listener) {
		super(rectangle, contentType, listener);
		init();
	}
	
	private void init() {
		board = new Board(this.getBounds(), 3, 3, this, listener,
				ImageLoader.load(new File("src/images/cross.png")),
				ImageLoader.load(new File("src/images/nought.png")));
		
		this.setLayout(null);
		this.add(board);
	}
	
	public Board getBoard() {
		return board;
	}
}
