package ch.hszt.mdp.domain;

public class User {
	private String name;
	private String email;
	private String nickname;

	public User(String name, String email, String nickname) {
		super();
		this.name = name;
		this.email = email;
		this.nickname = nickname;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

}
