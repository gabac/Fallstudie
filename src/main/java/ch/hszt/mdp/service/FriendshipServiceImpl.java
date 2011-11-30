package ch.hszt.mdp.service;

import java.util.List;

import ch.hszt.mdp.dao.FriendshipDao;
import ch.hszt.mdp.domain.Friendship;

public class FriendshipServiceImpl implements FriendshipService{
	
	private FriendshipDao friendshipDao;

	@Override
	public List<Friendship> getFriendsFromUser(String email) {
		// TODO Auto-generated method stub
		return friendshipDao.getFriendsFromUser(email);
	}

}
