package ch.hszt.mdp.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.orm.hibernate3.HibernateTemplate;

import ch.hszt.mdp.domain.User;

public class UserDaoImpl extends HibernateTemplate implements UserDao {

	public void save(User user) {
		getSession().saveOrUpdate(user);
	}

	@SuppressWarnings("unchecked")
	public List<User> getUserByEmail(String email) {
		return getSession().createQuery("from User u where u.email='" + email + "'").list();
	}

	/**
	 * Checks if there is already a user with this email address.
	 * 
	 * @param email
	 * @return <code>true</code> if the email address already exists
	 */
	public boolean duplicate(String email) {

		Query q = getSession().createQuery("SELECT COUNT(u.id) FROM User u where u.email = :email");
		q.setParameter("email", email);

		long count = (Long) q.iterate().next();

		return count > 0;
	}
}
