package ch.hszt.mdp.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.orm.hibernate3.HibernateTemplate;

import ch.hszt.mdp.domain.Activity;
import ch.hszt.mdp.domain.User;

public class ActivityDaoImpl extends HibernateTemplate implements ActivityDao {

	public List<Activity> getActivities() {
		Query q = getSession().createQuery("from Activity activity order by activity.time desc");
		return q.list();
	}

	public void save(Activity activity) {
		getSession().save(activity);
	}

	public void delete(Activity activity) {
		getSession().delete(activity);
	}

	public Activity getActivityByParent(Activity parent, User user) {
		return (Activity) getSession().createQuery("from Activity a where a.parent = :parent and a.user = :user")
				.setParameter("parent", parent).setParameter("user", user).uniqueResult();
	}

	public Activity getActivity(int id) {
		return (Activity) getSession().createQuery("from Activity a where a.id = :id").setParameter("id", id).uniqueResult();
	}
}
