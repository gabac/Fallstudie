package ch.hszt.mdp.service;

import ch.hszt.mdp.dao.FriendshipDao;
import ch.hszt.mdp.domain.Friendship;
import ch.hszt.mdp.domain.User;

/**
 * This class provides functions for handling friendships 
 * @author Roger Bollmann, Raphael Marques
 * @param friendshipDao Data Access Object for FriendShip Object.
 * @param pushNotificationiOSService push notification for iPhone/iPad App
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
	/**
	 * @author Gaba
	 */
	
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
	/**
	 * A sipmple boolean method to find out, if 2 users are friends or not.
	 * @param friend User1
	 * @param user User 2
	 * @return boolean if User1 and User2 are friends, true.
	 */
	public boolean checkForFriendship(User friend, User user) {
		return friendshipDao.checkFriendship(friend, user);
	}
}
