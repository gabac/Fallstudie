package ch.hszt.mdp.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Calendar;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ch.hszt.mdp.domain.Activity;
import ch.hszt.mdp.domain.Activity.ActivityType;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:mdp-test-daos.xml" })
public class ActivityDaoTest {

	// this instance will be dependency injected by type
	private ActivityDao activityDao;

	private Activity activity;

	@Autowired
	public void setUserDao(ActivityDao activityDao) {
		this.activityDao = activityDao;
	}

	@Before
	public void setup() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(2000, 1, 1);

		activity = new Activity();
		//activity.setTyp(ActivityType.PROFILE);
		activity.setUser_id(new Integer(0));
	}

	@After
	public void cleanup() {
		activityDao.delete(activity);
	}
	@Test
	public void test() {
		assertEquals(true, true);
	}

	//@Test
	public void testSaveActivity() { 

		//activityDao.save(activity);

		assertNotNull(activityDao.getActivities());
	}
	
	//@Test
	public void testGetActivities() {
		activityDao.save(activity);
		
		List<Activity> activities = activityDao.getActivities();
		
		assertNotNull(activities);
		
		Activity test = activities.get(0);
		
		assertEquals(ActivityType.PROFILE, test.getActivityType().toValue());
		assertEquals(new Integer(0), test.getUser_id());
	}
}
