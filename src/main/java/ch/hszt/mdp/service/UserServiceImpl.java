package ch.hszt.mdp.service;

import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ch.hszt.mdp.dao.UserDao;
import ch.hszt.mdp.domain.User;

@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
public class UserServiceImpl implements UserService {

	private UserDao userDao;

	@Override
	public List<User> queryEmail(String email) {
		return userDao.getUserByEmail(email);
	}

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public void create(User user) {
		userDao.save(user);
	}
}
