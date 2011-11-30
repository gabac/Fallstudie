package ch.hszt.mdp.dao;

import java.util.List;

import ch.hszt.mdp.domain.Friendship;

public interface FriendshipDao {
	
	List<Friendship> getFriendsFromUser (String email);

}
