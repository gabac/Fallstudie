package ch.hszt.mdp.service;

import java.util.List;

import ch.hszt.mdp.dao.UserDao;
import ch.hszt.mdp.domain.Friendship;
import ch.hszt.mdp.domain.User;
/**
 * Interface for the User Service, defined in mdp-service.xml
 * @author Fabian Vogler
 *
 */
public interface UserService {

	public void create(User user);

	void setUserDao(UserDao userDao);
	
	public List<User> getUserByEmail(String email);

}
