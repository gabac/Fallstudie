package ch.hszt.mdp.dao;

import ch.hszt.mdp.domain.Friendship;

public interface FriendshipDao {
	
	public void save(Friendship friendship);

	public boolean checkFriendship(int friend_id, int user_id);

}
