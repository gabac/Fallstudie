package ch.hszt.mdp.service;

import java.util.List;

import ch.hszt.mdp.dao.UserDao;
import ch.hszt.mdp.domain.Friendship;
import ch.hszt.mdp.domain.Stream;
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

	List<Friendship> getAccepteFriendships(String email);

	List<Friendship> getUnaccepteFriendships(String email);

	void acceptFriend(int friendId, int id);

	void ignoreFriend(int friendId, int id);

	Stream getActivitiesFromFriends(String email);

	void updateUser(User origin, User user);

	void saveUser(User user);
	
	public boolean askForFriendship(User friend, User user);

}
