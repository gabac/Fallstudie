package ch.hszt.mdp.service;

import java.io.IOException;
import java.util.List;

import ch.hszt.mdp.dao.FriendshipDao;
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

	public void setActivityService(ActivityService activityService);

	User getUserByEmail(String email);

	User getUser(int id);

	List<Friendship> getAccepteFriendships(User user);

	List<Friendship> getUnaccepteFriendships(String email);

	void acceptFriend(int friendId, int id);

	void ignoreFriend(int friendId, int id);

	Stream getActivitiesFromFriends(String email);

	void updateUser(User origin, User user);

	void saveUser(User user);

	public List<User> searchUser(String search, User user);

	public byte[] getPhoto(int id, int size, boolean crop) throws IOException;

	public void updatePrivacy(User user);

	public List<User> getUsers();
}
