package ch.hszt.mdp.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import ch.hszt.mdp.domain.Friendship;
import ch.hszt.mdp.domain.User;

/**
 * Interface for UserDao (User Data Access Object) Return a User-Email Address from the User
 * 
 * 
 */

public interface UserDao {
	void save(User user);

	User getUserByEmail(String email);

	boolean duplicate(String email);
	
	void delete(Object entity) throws DataAccessException;

}
