package ch.hszt.mdp.service;

import ch.hszt.mdp.dao.UserDao;
<<<<<<< HEAD
import ch.hszt.mdp.domain.Activity;
import ch.hszt.mdp.domain.Friendship;
=======
import ch.hszt.mdp.domain.Stream;
>>>>>>> d84b39346de3dfa1453f1180fce507420ac32628
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
	
<<<<<<< HEAD
	List<Activity> getActivitiesFromFriends(String email);
	List<Friendship> getAccepteFriendships(String email);
	List<Friendship> getUnaccepteFriendships(String email);

	void acceptFriend(int friendId, int id);
	
	void ignoreFriend(int friendId, int id);
=======
	Stream getActivitiesFromFriends(String email);
	
	void saveUser(User user);
>>>>>>> d84b39346de3dfa1453f1180fce507420ac32628

}
