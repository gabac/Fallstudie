package ch.hszt.mdp.dao;

import ch.hszt.mdp.domain.Friendship;
import ch.hszt.mdp.domain.User;

public interface FriendshipDao {
	
	public void save(Friendship friendship);

	public boolean checkFriendship(User friend, User user);

}
