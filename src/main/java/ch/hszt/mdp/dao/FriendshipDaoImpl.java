package ch.hszt.mdp.dao;

import org.hibernate.Query;
import org.springframework.orm.hibernate3.HibernateTemplate;

import ch.hszt.mdp.domain.Friendship;
import ch.hszt.mdp.domain.User;

public class FriendshipDaoImpl extends HibernateTemplate implements FriendshipDao {
	
	/**
	 * Saves a FriendshipObject to the Database with Hibernate
	 */
	public void save(Friendship friendship) {
		
		getSession().saveOrUpdate(friendship);

	}

	@Override
	public boolean checkFriendship(User friend, User user) {
		
		Query q = getSession().createQuery("SELECT COUNT(*) FROM Friendship fs where fs.primary_user = :user_id and fs.secondary_user = :friend_id");
		q.setParameter("friend_id", friend.getId());
		q.setParameter("user_id", user.getId());

		long count = (Long) q.iterate().next();

		if (count > 0){
			return true;
		} else{
			return false;
		}
	}

}
