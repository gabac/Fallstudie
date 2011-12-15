package ch.hszt.mdp.dao;

import org.hibernate.Query;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;

import ch.hszt.mdp.domain.Friendship;
import ch.hszt.mdp.domain.User;

@Transactional
public class FriendshipDaoImpl extends HibernateTemplate implements FriendshipDao {

	/**
	 * Saves a FriendshipObject to the Database with Hibernate
	 */
	public void save(Friendship friendship) {

		getSession().saveOrUpdate(friendship);

	}

	@Override
	public boolean checkFriendship(User friend, User user) {

		Query q = getSession().createQuery("SELECT COUNT(*) FROM Friendship f where f.primaryUser = :user and f.secondaryUser = :friend");
		q.setParameter("friend", friend);
		q.setParameter("user", user);

		long count = (Long) q.iterate().next();

		return count > 0;
	}

}
