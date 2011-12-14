package ch.hszt.mdp.util;

import java.io.Serializable;

public class Message implements Serializable {

	private static final long serialVersionUID = 1L;

	private String type;

	private String text;

	public Message(String type, String text) {
		this.type = type;
		this.text = text;
	}

	public Message(String text) {
		this("success", text);
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
}
