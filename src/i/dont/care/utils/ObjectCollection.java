package i.dont.care.utils;

import java.util.ArrayList;

public class ObjectCollection extends ArrayList<Object> {
	
	public ObjectCollection(Object... objects) {
		fill(objects);
	}
	
	private void fill(Object[] objects) {
		for (Object object : objects) {
			this.add(objects);
		}
	}
}
