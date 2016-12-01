package i.dont.care.clientserver;

import i.dont.care.mvc.IModel;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends Thread {
	
	private IModel model;
	private int port;
	
	public Server(IModel model, int port) {
		this.model = model;
		this.port = port;
	}
	
	@Override
	public void run() {
		try {
		ServerSocket serverSocket = new ServerSocket(port);
		
		while(true) {
			Socket client = null;
			while (client == null) {
				client = serverSocket.accept();
			}
			
			new RequestReceiver(model, client, chanel).start();
		}
			
		} catch (IOException e) {
			e.printStackTrace();
			//TODO Исправвить
		}
	}
	
	
}
