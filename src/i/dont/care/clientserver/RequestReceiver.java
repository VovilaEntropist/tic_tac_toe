package i.dont.care.clientserver;

import i.dont.care.clientserver.message.Message;
import i.dont.care.clientserver.message.MessageCollection;

public class RequestReceiver extends Thread {
	
	private RequestProcessor processor;
	private SocketChanel chanel;
	
	public RequestReceiver(RequestProcessor processor, SocketChanel chanel) {
		this.processor = processor;
		this.chanel = chanel;
		chanel.create();
	}
	
	@Override
	public void run() {
		while (true) {
			MessageCollection request = null;
			
			request = (MessageCollection) chanel.read();
			if (request == null) {
				continue;
			}
			
			handleRequest(request);
		}
	}
	
	
	private void handleRequest(MessageCollection requests) {
		for (Message request : requests) {
			chanel.write(processor.handleRequest(request));
		}
	}
}
