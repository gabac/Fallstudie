package ch.hszt.mdp.service;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.imageio.ImageIO;

import org.imgscalr.Scalr;
import org.joda.time.DateTime;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ch.hszt.mdp.dao.FriendshipDao;
import ch.hszt.mdp.dao.UserDao;
import ch.hszt.mdp.domain.Activity;
import ch.hszt.mdp.domain.Friendship;
import ch.hszt.mdp.domain.Stream;
import ch.hszt.mdp.domain.User;
import ch.hszt.mdp.util.ActivityComparator;

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

	private FriendshipDao friendshipDao;

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

	@Override
	public void acceptFriend(int friendId, int id) {
		userDao.acceptFriend(friendId, id);
		activityService.acceptFriendship(getUser(friendId), getUser(id));

	}

	@Override
	public void ignoreFriend(int friendId, int id) {
		// TODO Auto-generated method stub
		userDao.ignoreFriend(friendId, id);
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

	public Stream getActivitiesFromFriends(String email) {
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

			// sort the activities descending
			Collections.sort(stream.getTodaysActivities(), new ActivityComparator());
			Collections.sort(stream.getYesterdaysActivities(), new ActivityComparator());
			Collections.sort(stream.getPastActivities(), new ActivityComparator());
		}

		return stream;
	}

	@Override
	public User getUser(int id) {
		return userDao.getUser(id);
	}

	@Override
	public void updateUser(User origin, User user) {

		if (!user.getPassword().equals("")) {

			String password = user.getPassword();

			try {
				// convert password to SHA1
				password = sha1(password);

			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			} finally {
				origin.setPassword(password);
				origin.setRepeat(password);
			}
		}

		if (user.getHasPhoto() && user.getPhoto().length != 0) {
			origin.setPhoto(user.getPhoto());
		}

		origin.setRepeat(origin.getPassword());
		origin.setEmail(user.getEmail());
		origin.setPrename(user.getPrename());
		origin.setSurname(user.getSurname());
		origin.setBirthdate(user.getBirthdate());
		origin.setCity(user.getCity());

		userDao.save(origin);
	}

	public void saveUser(User user) {
		userDao.save(user);
	}


	public List<User> searchUser(String search) {

		return userDao.searchUser(search);
	}


	public boolean askForFriendship(User friend, User user) throws NullPointerException {

		if (friendshipDao.checkFriendship(friend, user) == true) {

			return false;

		} else {

			Friendship friendship = new Friendship();
			friendship.setPrimary_user(user.getId());
			friendship.setSecondary_user(friend.getId());
			friendship.setAccepted(0);

			friendshipDao.save(friendship);
			return true;
		}

	}

	public byte[] getPhoto(int id, int size, boolean crop) throws IOException {

		User user = getUser(id);

		InputStream in = new ByteArrayInputStream(user.getPhoto());
		BufferedImage image = ImageIO.read(in);

		// portrait
		Scalr.Mode mode = Scalr.Mode.FIT_TO_WIDTH;

		if (crop && image.getWidth() > image.getHeight()) {

			// landscape
			mode = Scalr.Mode.FIT_TO_HEIGHT;
		}

		BufferedImage thumbnail = Scalr.resize(image, mode, size, Scalr.OP_BRIGHTER);

		if (crop) {
			thumbnail = Scalr.crop(thumbnail, size, size);
		}

		ByteArrayOutputStream bas = new ByteArrayOutputStream();
		ImageIO.write(thumbnail, "png", bas);

		return bas.toByteArray();
	}

}
