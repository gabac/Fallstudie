package ch.hszt.mdp.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ch.hszt.mdp.dao.ActivityDao;
import ch.hszt.mdp.domain.Activity;
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
	
	public List<Activity> getMyActivities(User user) {
		List<Activity> activities = activityDao.getActivities();
		
		List<Activity> myActivities = new ArrayList<Activity>();
		
		for(Activity activity : activities) {
			
			if(activity.getUser_id().equals(user.getId())) {
				myActivities.add(activity);
			}
		}
		
		return myActivities;
	}

}
