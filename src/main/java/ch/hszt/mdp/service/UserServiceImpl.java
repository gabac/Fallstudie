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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ch.hszt.mdp.dao.FriendshipDao;
import ch.hszt.mdp.dao.UserDao;
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

	@Autowired
	private FriendshipDao friendshipDao;

	public void setActivityService(ActivityService activityService) {
		this.activityService = activityService;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public void setFriendshipDao(FriendshipDao friendshipDao) {
		this.friendshipDao = friendshipDao;
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

			// set random API key
			user.setApiKey(sha1(Math.random() + ""));

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

	public List<User> getUsers() {
		return userDao.getUsers();
	}

	@Override
	public void acceptFriend(int friendId, int id) {
		userDao.acceptFriend(friendId, id);
		activityService.acceptFriendship(getUser(friendId), getUser(id));

		// User friend1 = getUser(friendId);
		// User user1 = getUser(id);

		Friendship friendship = new Friendship();
		friendship.setPrimaryUser(getUser(friendId));
		friendship.setSecondaryUser(getUser(id));
		friendship.setAccepted(1);

		friendshipDao.save(friendship);

	}

	@Override
	public void ignoreFriend(int friendId, int id) {
		// TODO Auto-generated method stub
		userDao.ignoreFriend(friendId, id);
	}

	public List<Friendship> getAccepteFriendships(User user) {

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

		User user = getUserByEmail(email);
		List<Friendship> friends = getAccepteFriendships(user);

		Stream stream = new Stream();

		stream.addActivites(user.getActivities());

		for (Friendship friend : friends) {

			stream.addActivites(friend.getSecondaryUser().getActivities());
		}

		// sort the activities descending
		Collections.sort(stream.getTodaysActivities(), new ActivityComparator());
		Collections.sort(stream.getYesterdaysActivities(), new ActivityComparator());
		Collections.sort(stream.getPastActivities(), new ActivityComparator());
		Collections.sort(stream.getActivities(), new ActivityComparator());

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

	public List<User> searchUser(String search, User user) {

		return userDao.searchUser(search, user.getId());
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

	@Override
	public void updatePrivacy(User user) {

		User origin = getUser(user.getId());

		origin.setPrivacyProfile(user.getPrivacyProfile());
		origin.setPrivacyEmail(user.getPrivacyEmail());

		saveUser(origin);
	}

}
