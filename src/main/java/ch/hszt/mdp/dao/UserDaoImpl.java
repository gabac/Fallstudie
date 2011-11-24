package ch.hszt.mdp.dao;

import java.util.List;

import org.springframework.orm.hibernate3.HibernateTemplate;

import ch.hszt.mdp.domain.User;

public class UserDaoImpl extends HibernateTemplate implements UserDao {

	public void save(User user) {
		getSession().saveOrUpdate(user);
	}

	@SuppressWarnings("unchecked")
	public List<User> getUserByNick(String nick) {
		return getSession().createQuery(
				"from User u where u.nick='" + nick + "'").list();
	}

}
