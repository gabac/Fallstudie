package ch.hszt.mdp.dao.validation;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import javax.validation.ConstraintValidatorContext;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import ch.hszt.mdp.dao.UserDao;
import ch.hszt.mdp.validation.UniqueEmailValidator;

@RunWith(JMock.class)
public class UniqueEmailValidatorTest {

	private Mockery mockery;

	private ConstraintValidatorContext context;

	private UserDao dao;

	private UniqueEmailValidator validator;

	@Before
	public void setUp() {

		mockery = new JUnit4Mockery();
		context = mockery.mock(ConstraintValidatorContext.class);
		dao = mockery.mock(UserDao.class);

		validator = new UniqueEmailValidator();
		validator.setUserDao(dao);
	}

	@Test
	public void testIsValid() {

		mockery.checking(new Expectations() {
			{
				one(dao).duplicate("foo@bar.com");
				will(returnValue(false));
			}
		});

		assertTrue(validator.isValid("foo@bar.com", context));
	}

	@Test
	public void testIsNotValid() {

		mockery.checking(new Expectations() {
			{
				one(dao).duplicate("foo@bar.com");
				will(returnValue(true));
			}
		});

		assertFalse(validator.isValid("foo@bar.com", context));
	}
}
