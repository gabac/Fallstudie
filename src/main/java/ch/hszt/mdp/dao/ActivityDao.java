package ch.hszt.mdp.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import ch.hszt.mdp.domain.Activity;
import ch.hszt.mdp.domain.User;

public interface ActivityDao {
	void save(Activity activity);

	List<Activity> getActivities();

	void delete(Object entity) throws DataAccessException;

	Activity getActivity(int id);

	Activity getActivityByParent(Activity parent, User user);

	void delete(Activity activity);
}
