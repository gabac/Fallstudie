package ch.hszt.mdp.service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

<<<<<<< HEAD
import org.springframework.beans.factory.annotation.Autowired;
=======
import org.joda.time.DateTime;
>>>>>>> d84b39346de3dfa1453f1180fce507420ac32628
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ch.hszt.mdp.dao.UserDao;
import ch.hszt.mdp.domain.Activity;
import ch.hszt.mdp.domain.Friendship;
import ch.hszt.mdp.domain.Stream;
import ch.hszt.mdp.domain.User;

/**
 * Implementation for UserService. This handles the registration on back-end side.
 * 
 * @author Fabian Vogler
 * 
 */
@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
public class UserServiceImpl implements UserService {

	private UserDao userDao;
	
	private ActivityService activityService;
	
	public void setActivityService(ActivityService activityService) {
		this.activityService = activityService;
	}
	
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	/**
	 * This method saves a user into the database.
	 * 
	 * @param password
	 * @param user
	 * @param userDao
	 *            User Data Access object defination for hibernate
	 */
	public void create(User user) {

		String password = user.getPassword();

		try {

			// convert password to SHA1
			password = sha1(password);

			user.setPassword(password);
			user.setRepeat(password);

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		userDao.save(user);
	}

	public String sha1(String password) throws NoSuchAlgorithmException {

		MessageDigest md = MessageDigest.getInstance("SHA1");
		md.reset();

		byte[] buffer = password.getBytes();
		md.update(buffer);

		byte[] digest = md.digest();

		String hexStr = "";
		for (int i = 0; i < digest.length; i++) {
			hexStr += Integer.toString((digest[i] & 0xff) + 0x100, 16).substring(1);
		}

		return hexStr;
	}

	/**
	 * Creates a UserList with User Objects. The Userlist is collected from the User Database.
	 */
	public User getUserByEmail(String email) {
		return userDao.getUserByEmail(email);
	}

	public List<Friendship> getAccepteFriendships(String email) {

		User user = getUserByEmail(email);
		List<Friendship> acceptedFriends = new ArrayList<Friendship>();

		for (Friendship friend : user.getFriendships()) {

			if (friend.getAccepted() == 1) {
				acceptedFriends.add(friend);
			}
		}

		return acceptedFriends;

	}
<<<<<<< HEAD
	
	public List<Friendship> getUnaccepteFriendships(String email) {

		User user = getUserByEmail(email);
		List<Friendship> unacceptedFriends = new ArrayList<Friendship>();

		for (Friendship friend : user.getFriendships()) {

			if (friend.getAccepted() == 0) {
				unacceptedFriends.add(friend);
			}
		}

		return unacceptedFriends;

	}
	
	public List<Activity> getActivitiesFromFriends(String email) {
=======

	public Stream getActivitiesFromFriends(String email) {
>>>>>>> d84b39346de3dfa1453f1180fce507420ac32628
		List<Friendship> friends = getAccepteFriendships(email);

		DateTime now = new DateTime();

		DateTime startOfToday = now.toDateMidnight().toInterval().getStart();
		DateTime endOfToday = now.toDateMidnight().toInterval().getEnd();
		DateTime startOfYesterDay = now.minusDays(1).toDateMidnight().toInterval().getStart();

		Stream stream = new Stream();

		for (Friendship friend : friends) {

			for (Activity activity : friend.getSecondaryUser().getActivities()) {
				if (activity.getTime().isAfter(startOfToday) && activity.getTime().isBefore(endOfToday)) {
					stream.addTodaysActivity(activity);
				} else if (activity.getTime().isBefore(startOfToday) && activity.getTime().isAfter(startOfYesterDay)) {
					stream.addYesterdaysActivities(activity);
				} else {
					stream.addPastActivities(activity);
				}

			}
		}

		return stream;
	}

	@Override
	public User getUser(int id) {
		return userDao.getUser(id);
	}
<<<<<<< HEAD

	@Override
	public void acceptFriend(int friendId, int id) {
		userDao.acceptFriend(friendId, id);
		activityService.acceptFriendship(getUser(friendId), getUser(id));
		
	}

	@Override
	public void ignoreFriend(int friendId, int id) {
		// TODO Auto-generated method stub
		userDao.ignoreFriend(friendId, id);
=======
	
	public void saveUser(User user) {
		userDao.save(user);
>>>>>>> d84b39346de3dfa1453f1180fce507420ac32628
	}
}
