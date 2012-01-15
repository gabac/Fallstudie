package ch.hszt.mdp.service;

import static org.junit.Assert.assertEquals;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import ch.hszt.mdp.dao.ActivityDao;
import ch.hszt.mdp.domain.Activity;
import ch.hszt.mdp.domain.Activity.ActivityType;

@RunWith(JMock.class)
public class ActivityServiceTest {
	private Mockery context;

	private ActivityService service;

	@Before
	public void setUp() {
		context = new JUnit4Mockery();
		service = new ActivityServiceImpl();
	}

	@Test
	public void testCreate() {

		final ActivityDao dao = context.mock(ActivityDao.class);
		final Activity activity = getActivity();

		// define expectations
		context.checking(new Expectations() {
			{
				one(dao).save(activity);
			}
		});

		service.setActivityDao(dao);
		service.create(activity);

		assertEquals(ActivityType.PROFILE.toString(), activity.getType().toString());
	}

	@Test
	public void testUpdateSatus() {
		final ActivityDao dao = context.mock(ActivityDao.class);
		final Activity activity = getStatusActivity();

		// define expectations
		context.checking(new Expectations() {
			{
				one(dao).save(activity);
			}
		});

		service.setActivityDao(dao);
		service.create(activity);

		assertEquals(ActivityType.STATUS.toString(), activity.getType().toString());
		assertEquals("Das ist ein Test", activity.getContent());
	}

	private Activity getActivity() {

		Activity activity = new Activity();
		activity.setType(ActivityType.PROFILE);

		return activity;
	}

	private Activity getStatusActivity() {
		Activity activity = new Activity();
		activity.setType(ActivityType.STATUS);
		activity.setContent("Das ist ein Test");

		return activity;
	}
}
