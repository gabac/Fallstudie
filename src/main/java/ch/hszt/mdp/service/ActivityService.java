package ch.hszt.mdp.service;

import java.util.List;

import ch.hszt.mdp.dao.ActivityDao;
import ch.hszt.mdp.domain.Activity;

public interface ActivityService {
	List<Activity> getActivities();
	
	void create(Activity activity);
	
	void setActivityDao(ActivityDao activityDao);
}
