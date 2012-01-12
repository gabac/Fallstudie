package ch.hszt.mdp.service;

import ch.hszt.mdp.dao.FriendshipDao;
import ch.hszt.mdp.domain.Friendship;
import ch.hszt.mdp.domain.User;

/**
 * This class provides functions for handling friendships
 * @author Roger Bollmann, Raphael Marques
 *
 */
public class FriendshipServiceImpl implements FriendshipService {

	private FriendshipDao friendshipDao;
	
	private PushNotificationService pushNotifictioniOSService;

	public void setFriendshipDao(FriendshipDao friendshipDao) {
		this.friendshipDao = friendshipDao;
	}

	
	public void setPushNotifictioniOSService(PushNotificationService pushNotifictioniOSService) {
		this.pushNotifictioniOSService = pushNotifictioniOSService;
	}
	
	public boolean askForFriendship(User friend, User user) throws NullPointerException {

		if (checkForFriendship(friend, user) == true) {

			return false;

		} else {

			Friendship friendship = new Friendship();
			friendship.setPrimaryUser(friend);
			friendship.setSecondaryUser(user);
			friendship.setAccepted(0);

			friendshipDao.save(friendship);
			
			pushNotifictioniOSService.addAsFriend(friend);

			return true;
		}
	}

	public boolean checkForFriendship(User friend, User user) {
		return friendshipDao.checkFriendship(friend, user);
	}
}
