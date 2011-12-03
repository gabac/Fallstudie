package ch.hszt.mdp.service;

import java.util.List;

import ch.hszt.mdp.dao.UserDao;
import ch.hszt.mdp.domain.Activity;
import ch.hszt.mdp.domain.User;

/**
 * Interface for the User Service, defined in mdp-service.xml
 * 
 * @author Fabian Vogler
 * 
 */
public interface UserService {

	public void create(User user);

	void setUserDao(UserDao userDao);

	User getUserByEmail(String email);

	User getUser(int id);
	
	List<Activity> getActivitiesFromFriends(String email);

}
