package ch.hszt.mdp.service;

import ch.hszt.mdp.domain.User;

public interface FriendshipService {
	
	public boolean askForFriendship(User friend, User user);
	
	public boolean checkForFriendship(User friend, User user);

}
