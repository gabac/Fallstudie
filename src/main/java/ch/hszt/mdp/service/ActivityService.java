package ch.hszt.mdp.service;

import java.util.List;

import ch.hszt.mdp.dao.ActivityDao;
import ch.hszt.mdp.domain.Activity;
import ch.hszt.mdp.domain.User;

public interface ActivityService {
	List<Activity> getActivities();

	void create(Activity activity);

	void setActivityDao(ActivityDao activityDao);

	void acceptFriendship(User friend, User user);

	void updateStatus(Activity activity);

	Activity getActivity(int id);

	void like(User user, Activity parent);

	void unlike(User user, Activity parent);
}
