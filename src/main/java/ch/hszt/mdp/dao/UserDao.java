
package ch.hszt.mdp.dao;

import java.util.List;
import ch.hszt.mdp.domain.User;

/**
*Interface for UserDao (User Data Access Object)
*Return a User-Email Address from the User
*
*
*/

public interface UserDao {
	void save(User user);
	
	List<User> getUserByEmail(String email);
}
