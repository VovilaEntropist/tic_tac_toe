package i.dont.care.clientserver;

import i.dont.care.clientserver.message.Message;
import i.dont.care.clientserver.message.MessageCollection;
import i.dont.care.clientserver.message.MessageFactory;
import i.dont.care.tictactoe.serverside.Player;

import java.io.IOException;
import java.net.Socket;

public class Client {
	
	private String ip;
	private int port;
	
	private SocketChanel chanel;
	
	public Client(String ip, int port) {
		this.ip = ip;
		this.port = port;
	}
	
	public MessageCollection connect(Player player) {
		try {
			chanel = new SocketChanel(new Socket(ip, port));
		} catch (IOException e) {
			e.printStackTrace();
			return null; //TODO возврат сообщения о невозможности подключиться
		}
		
		if (chanel.create()) {
			return null; //TODO
		}
		
		return request(new MessageCollection(MessageFactory.createConnectPlayer(player)));
	}
	
	public MessageCollection disconect(Player player) {
		return request(new MessageCollection(MessageFactory.createDisconnectPlayer(player)));
	}
	
	public MessageCollection request(MessageCollection messages) {
		chanel.write(messages);
		Object response = chanel.read();
		
		if (response == null) {
			return null; //TODO
		}
		
		if (!(response instanceof MessageCollection)) {
			return null; //TODO
		}
		
		return (MessageCollection) response;
	}
	
	public MessageCollection request(Message message) {
		return request(new MessageCollection(message));
	}
	
	
}
