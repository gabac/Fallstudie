package ch.hszt.mdp.service;

import java.util.List;

import ch.hszt.mdp.domain.Friendship;

public interface FriendshipService {
	
	public List<Friendship> getFriendsFromUser (String email);

}
