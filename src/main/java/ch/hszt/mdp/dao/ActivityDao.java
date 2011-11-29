package ch.hszt.mdp.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import ch.hszt.mdp.domain.Activity;

public interface ActivityDao {
	void save(Activity activity);

	List<Activity> getActivities();

	void delete(Object entity) throws DataAccessException;
}
