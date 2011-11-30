package ch.hszt.mdp.dao;

import java.util.List;

import ch.hszt.mdp.domain.Friendship;
import org.hibernate.Query;
import org.springframework.orm.hibernate3.HibernateTemplate;

public class FriendshipDaoImpl extends HibernateTemplate implements FriendshipDao{
	
	@SuppressWarnings("unchecked")
	public List<Friendship> getFriendsFromUser(String email) {
		return getSession().createQuery("from Friendship").list();
	}

}
