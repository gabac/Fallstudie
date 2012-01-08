package ch.hszt.mdp.service;

import java.util.List;

import org.joda.time.DateTime;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ch.hszt.mdp.dao.ActivityDao;
import ch.hszt.mdp.domain.Activity;
import ch.hszt.mdp.domain.Activity.ActivityType;
import ch.hszt.mdp.domain.User;

@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
public class ActivityServiceImpl implements ActivityService {

	private ActivityDao activityDao;

	public void setActivityDao(ActivityDao activityDao) {
		this.activityDao = activityDao;
	}

	public List<Activity> getActivities() {
		return activityDao.getActivities();
	}

	public void create(Activity activity) {
		activityDao.save(activity);
	}

	/**
	 * Accept Friends by clicking on the accept button following are executed: create a new Activity Object by filling
	 * out the attributes for the user and the friend make an insert into with activity.save(activity)
	 * 
	 * @author Roger Bollmann
	 * 
	 */
	public void acceptFriendship(User friend, User user) {

		// make a status for the user: You are now friends with "friend"
		Activity activity = new Activity();
		activity.setContent(getFriendshipContent(user, friend));
		activity.setType(ActivityType.FRIEND);
		activity.setUser(user);
		activity.setTime(new DateTime());

		// make a status for the friend: "friend" is now friends with "user"
		Activity activity2 = new Activity();
		activity2.setContent(getFriendshipContent(friend, user));
		activity2.setType(ActivityType.FRIEND);
		activity2.setUser(friend);
		activity2.setTime(new DateTime());

		activityDao.save(activity);

		activityDao.save(activity2);

	}
	
	public void updateStatus(Activity activity) {

		activityDao.save(activity);
	}

	private String getFriendshipContent(User user1, User user2) {
		return user1.getPrename() + " " + user1.getSurname() + " is now friends with " + user2.getPrename() + " " + user2.getSurname();
	}
	

}
