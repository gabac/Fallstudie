package ch.hszt.mdp.dao;

import java.util.List;

import ch.hszt.mdp.domain.User;

public interface UserDao {
	void save(User user);
	
	List<User> getUserByEmail(String email);
}
