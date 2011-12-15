package ch.hszt.mdp.service;

import ch.hszt.mdp.dao.FriendshipDao;
import ch.hszt.mdp.domain.Friendship;
import ch.hszt.mdp.domain.User;

public class FriendshipServiceImpl implements FriendshipService {
	
	private FriendshipDao friendshipDao;
	
	public void setFriendshipDao(FriendshipDao friendshipDao){
		this.friendshipDao = friendshipDao;
	}

	public boolean askForFriendship(User friend, User user) throws NullPointerException{
		
		if (checkForFriendship(friend, user) == true){

			return false;		
			
		}else{
		
			Friendship friendship = new Friendship();
			friendship.setPrimary_user(friend.getId());
			friendship.setSecondary_user(user.getId());
			friendship.setAccepted(0);
		
			friendshipDao.save(friendship);
			return true;
		}
		
	}
	
	public boolean checkForFriendship(User friend, User user) {
		return friendshipDao.checkFriendship(friend, user);
	}

}
