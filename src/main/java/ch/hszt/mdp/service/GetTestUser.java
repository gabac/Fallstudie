package ch.hszt.mdp.service;


import ch.hszt.mdp.domain.User;

public class GetTestUser {
	public static User getUser() {
	
		User user = new User();
		user.setEmail("gabathuler@gmail.com");
		user.setPrename("Cyril");
		user.setSurname("Gabathuler");
		user.setPassword("123");
		user.setRepeat("123");
		user.setId(1);

		return user;
	}

}
