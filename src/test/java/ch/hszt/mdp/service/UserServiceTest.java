package ch.hszt.mdp.service;

import static org.junit.Assert.assertEquals;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import ch.hszt.mdp.dao.UserDao;
import ch.hszt.mdp.domain.User;

@RunWith(JMock.class)
public class UserServiceTest {

	private Mockery context;

	private UserServiceImpl service;

	@Before
	public void setUp() {
		context = new JUnit4Mockery();
		service = new UserServiceImpl();
	}

	@Test
	public void testCreate() {

		final UserDao dao = context.mock(UserDao.class);
		final User user = getUser();

		// define expectations
		context.checking(new Expectations() {
			{
				one(dao).save(user);
			}
		});

		service.setUserDao(dao);
		service.create(user);

		assertEquals("40bd001563085fc35165329ea1ff5c5ecbdbbeef", user.getPassword());
	}

	private User getUser() {

		User user = new User();
		user.setEmail("gabathuler@gmail.com");
		user.setPrename("Cyril");
		user.setSurname("Gabathuler");
		user.setPassword("123");
		user.setRepeat("123");

		return user;
	}
}
