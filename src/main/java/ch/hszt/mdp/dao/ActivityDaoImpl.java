package ch.hszt.mdp.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.orm.hibernate3.HibernateTemplate;

import ch.hszt.mdp.domain.Activity;
import ch.hszt.mdp.domain.Activity.ActivityType;
import ch.hszt.mdp.domain.User;

public class ActivityDaoImpl extends HibernateTemplate implements ActivityDao {

	public List<Activity> getActivities() {
		Query q = getSession().createQuery("from Activity");
		return q.list();
	}
	
	public void save(Activity activity) {
		getSession().save(activity);
	}
	
}
