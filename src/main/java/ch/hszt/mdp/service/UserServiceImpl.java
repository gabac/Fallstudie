package ch.hszt.mdp.service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ch.hszt.mdp.dao.UserDao;
import ch.hszt.mdp.domain.User;

@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
/**
 * Implementation for UserService. This handles the registration on back-end side.
 * @author Fabian Vogler
 * 
 */
public class UserServiceImpl implements UserService {

	private UserDao userDao;

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	/**
	 * This method saves a user into the database.
	 * @param password
	 * @param user
	 * @param userDao User Data Access object defination for hibernate
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
	public List<User> getUserByEmail(String email) {
		return userDao.getUserByEmail(email);
	}


	
}
