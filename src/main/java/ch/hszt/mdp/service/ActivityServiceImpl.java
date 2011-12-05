package ch.hszt.mdp.service;

import java.util.List;

import org.joda.time.DateTime;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ch.hszt.mdp.dao.ActivityDao;
import ch.hszt.mdp.domain.Activity;
import ch.hszt.mdp.domain.User;
import ch.hszt.mdp.domain.Activity.ActivityType;

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
	 * Accept Friends by clicking on the accept button
	 * following are executed:
	 * create a new Activity Object by filling out the attributes for the user and the friend
	 * make an insert into with activity.save(activity)
	 * 
	 * @author Roger Bollmann
	 * 
	 */
	public void acceptFriendship(User friend, User user) {
		
		//make a status for the user: You are now friend with "friend"
		Activity activity = new Activity();
		activity.setContent("You are now friend with " + friend.getPrename() + " " + friend.getSurname());
		activity.setTyp(ActivityType.FRIEND);
		activity.setUser_id(user);
		activity.setTime(new DateTime());
		
		//make a status for the friend: You are now friend with "user"
		Activity activity2 = new Activity();
		activity2.setContent("You are now friend with " + user.getPrename() + " " + user.getSurname());
		activity2.setTyp(ActivityType.FRIEND);
		activity2.setUser_id(friend);
		activity2.setTime(new DateTime());
		
		activityDao.save(activity);
		
		activityDao.save(activity2);
		
	}
	
}
