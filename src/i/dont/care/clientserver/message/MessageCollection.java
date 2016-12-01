package i.dont.care.clientserver.message;


import java.util.ArrayList;
import java.util.Arrays;

public class MessageCollection extends ArrayList<Message> {
	
	public MessageCollection(Message[] messages) {
		super();
		this.addAll(Arrays.asList(messages));
	}
	
	public MessageCollection(Message message) {
		this(new Message[] { message });
	}
	
	public MessageCollection() {
		super();
	}
}
