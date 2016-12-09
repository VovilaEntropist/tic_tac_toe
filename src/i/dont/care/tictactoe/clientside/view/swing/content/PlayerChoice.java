package i.dont.care.tictactoe.clientside.view.swing.content;

import i.dont.care.tictactoe.clientside.view.swing.CenterPanel;
import i.dont.care.tictactoe.clientside.view.swing.ContentType;
import i.dont.care.tictactoe.clientside.view.swing.ImageLoader;
import i.dont.care.tictactoe.clientside.view.swing.ImagePanel;
import i.dont.care.tictactoe.clientside.view.swing.content.listener.ContentEvent;
import i.dont.care.tictactoe.clientside.view.swing.content.listener.ContentListener;
import i.dont.care.tictactoe.serverside.board.Mark;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

public class PlayerChoice extends BackButtonContent {
	
	private JTextField nickNameEdt;
	private ImagePanel xPanel;
	private ImagePanel oPanel;
	
	
	public PlayerChoice(Rectangle rectangle, ContentType contentType, ContentListener listener, Content from) {
		super(rectangle, contentType, listener, from);
		init();
		initListeners();
	}
	
	private void init() {
		JLabel lbl1 = new JLabel("Наховите себя, затем выберите сторону");
		nickNameEdt = new JTextField();
		xPanel = new ImagePanel(new Rectangle(0, 0, 50, 50), ImageLoader.load(new File("src/images/cross.png")));
		oPanel = new ImagePanel(new Rectangle(0, 0, 50, 50), ImageLoader.load(new File("src/images/nought.png")));
		
		xPanel.setPreferredSize(new Dimension(50, 50));
		oPanel.setPreferredSize(new Dimension(50, 50));
		
		JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		bottomPanel.add(xPanel);
		bottomPanel.add(oPanel);
		
		JPanel centerPanel = new CenterPanel(this.getBounds(), 1, 3);
		centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
		
		centerPanel.add(lbl1);
		centerPanel.add(nickNameEdt);
		centerPanel.add(bottomPanel);
		
		this.add(centerPanel);
	}
	
	private void initListeners() {
		xPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				listener.handleContentEvent(PlayerChoice.this, defineContentEvent(), nickNameEdt.getText(), Mark.Player1);
			}
		});
		
		oPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				listener.handleContentEvent(PlayerChoice.this, defineContentEvent(), nickNameEdt.getText(), Mark.Player2);
			}
		});
	}
	
	private ContentEvent defineContentEvent() {
		ContentEvent event = ContentEvent.Error;
		if (from instanceof ServerCreate) {
			event = ContentEvent.StartServer;
		} else if (from instanceof ServerConnect) {
			event = ContentEvent.ConnectServer;
		}
		
		return event;
	}
}
