package ch.hszt.mdp.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

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

	private UserService service;

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

	@Test
	public void testPasswordNotUpdated() {
		final UserDao dao = context.mock(UserDao.class);
		
		final User user = getUser();
		
		user.setId(1);
		user.setPassword("123");
		user.setRepeat("123");
		
		User u2 = getUser();
		u2.setPassword("");
		u2.setRepeat("");
		
		context.checking(new Expectations() {
			{
				one(dao).save(user);
			}
		});
		
		service.setUserDao(dao);
		service.create(user);
		
		final String sha1Pwd = user.getPassword();
		
		context.checking(new Expectations() {
			{
				one(dao).save(user);
			}
		});
		
		service.updateUser(user, u2);
		assertTrue(user.getPassword().equals(sha1Pwd));
	}

	@Test
	public void testPasswordUpdated() {
final UserDao dao = context.mock(UserDao.class);
		
		final User user = getUser();
		
		user.setId(1);
		user.setPassword("123");
		user.setRepeat("123");
		
		User u2 = getUser();
		u2.setPassword("456");
		u2.setRepeat("456");
		
		context.checking(new Expectations() {
			{
				one(dao).save(user);
			}
		});
		
		service.setUserDao(dao);
		service.create(user);
		
		final String sha1Pwd = user.getPassword();
		
		context.checking(new Expectations() {
			{
				one(dao).save(user);
			}
		});
		
		service.updateUser(user, u2);
		assertTrue(!user.getPassword().equals(sha1Pwd));

	}

	private void getAcceptedFriends() {
		UserServiceImpl userService = new UserServiceImpl();

		if (userService.getAccepteFriendships("roger.bollmann@gmail.com").get(0).equals("Raphael Marques")) {
			System.out.println("Test successfully");
		} else {
			System.out.println("TEST NOT SUCCESSFULLY!!!!!");
		}

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

}
