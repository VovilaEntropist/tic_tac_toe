package i.dont.care.clientserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends Thread {
	
	private RequestProcessor processor;
	private int port;
	
	public Server(RequestProcessor processor, int port) {
		this.processor = processor;
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
			
			new RequestReceiver(processor, new SocketChanel(client)).start();
		}
			
		} catch (IOException e) {
			e.printStackTrace();
			//TODO Исправвить
		}
	}
	
	
}
