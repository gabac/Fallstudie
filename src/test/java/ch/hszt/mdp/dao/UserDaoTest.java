package ch.hszt.mdp.dao;

import static org.junit.Assert.assertNotNull;

import java.util.Calendar;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ch.hszt.mdp.domain.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:mdp-test-daos.xml" })
public class UserDaoTest {

	// this instance will be dependency injected by type
	private UserDao userDao;

	private User user;

	@Autowired
	public void setTitleDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Before
	public void setup() {

		Calendar calendar = Calendar.getInstance();
		calendar.set(2000, 1, 1);

		user = new User();
		user.setEmail("gabathuler@gmail.com");
		user.setPrename("Cyril");
		user.setSurname("Gabathuler");
		user.setPassword("123");
		user.setRepeat("123");
		user.setBirthdate(calendar.getTime());
		user.setCity("Baden");
	}

	@After
	public void cleanup() {
		userDao.delete(user);
	}

	@Test
	public void testSaveUser() {

		userDao.save(user);

		assertNotNull(userDao.getUserByEmail("gabathuler@gmail.com"));
	}
}
