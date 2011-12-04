package ch.hszt.mdp.service;

import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ch.hszt.mdp.dao.ActivityDao;
import ch.hszt.mdp.domain.Activity;

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
	
}
