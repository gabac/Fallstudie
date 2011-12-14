package ch.hszt.mdp.util;

import java.io.Serializable;
import java.util.LinkedList;

public class Messages implements Serializable {

	private static final long serialVersionUID = 1L;

	private LinkedList<Message> messages = new LinkedList<Message>();

	public void addMessage(String type, String text) {
		messages.add(new Message(type, text));
	}

	public void addMessage(String text) {
		messages.add(new Message(text));
	}

	public Object[] getAll() {

		Object[] messages = this.messages.toArray();

		this.messages.clear();

		return messages;
	}
}
