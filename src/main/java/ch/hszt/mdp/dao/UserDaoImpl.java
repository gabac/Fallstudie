
package ch.hszt.mdp.dao;

import org.hibernate.Query;
import org.springframework.orm.hibernate3.HibernateTemplate;

import ch.hszt.mdp.domain.User;

/**
*Return a User-Email Address from the User
*@author Cyril Gabathuler
*
*/

public class UserDaoImpl extends HibernateTemplate implements UserDao {
	/**
	 * Saves a UserObject to the Database with Hibernate
	 */
	public void save(User user) {
		getSession().saveOrUpdate(user);
	}

	/**
	 * Creates a UserList with User Objects. The Userlist is collected from the User Database.
	 */
	public User getUserByEmail(String email) {
		return (User) getSession().createQuery("from User u where u.email='" + email + "'").uniqueResult();
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
