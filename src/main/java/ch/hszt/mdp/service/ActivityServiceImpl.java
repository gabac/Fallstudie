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
/**
 * This Service provides funtions to get Activities, Friendships and Likes/Unlikes
 * @author Raphael Marques, Roger Bollmann, Cyril Gabathuler
 * @param activityDao Data Access Object for Activities
 */
public class ActivityServiceImpl implements ActivityService {

	private ActivityDao activityDao;

	public void setActivityDao(ActivityDao activityDao) {
		this.activityDao = activityDao;
	}

	public List<Activity> getActivities() {
		return activityDao.getActivities();
	}
	/**
	 * This method creates an activity and saves it to the database. 
	 */
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
	/**
	 * This method saves and updates the activity (subtype status). 
	 * @param activity
	 */
	public void updateStatus(Activity activity) {

		activity.setType(ActivityType.STATUS);
		activity.setTime(new DateTime());

		activityDao.save(activity);
	}

	public Activity getActivity(int id) {
		return activityDao.getActivity(id);
	}
	/**
	 * This method saves and updates the activity (subtype "dislike")
	 * @param user the user that iniciated a like/dislike
	 * @param parent the activity, which is "rated"
	 */
	public void like(User user, Activity parent) {

		Activity activity = activityDao.getActivityByParent(parent, user);

		if (activity == null) {

			activity = new Activity();
			activity.setUser(user);
			activity.setType(ActivityType.LIKE);
			activity.setParent(parent);
			activity.setContent(parent.getContent());
		}

		activity.setTime(new DateTime());

		activityDao.save(activity);
	}
	/**
	 * This method removes the activity (subtype "like")
	 * @param user the user that iniciated a like/dislike
	 * @param parent the activity, which is "rated"
	 */
	public void unlike(User user, Activity parent) {

		Activity activity = activityDao.getActivityByParent(parent, user);

		if (activity != null) {
			activityDao.delete(activity);
		}
	}
	/**
	 * This Method get the String "<user1> is now friends with <user2>
	 * @param user1
	 * @param user2
	 * @return gives the string "<user1> is now friends with <user2> back
	 */
	private String getFriendshipContent(User user1, User user2) {
		return user1.getPrename() + " " + user1.getSurname() + " is now friends with " + user2.getPrename() + " " + user2.getSurname();
	}

}
