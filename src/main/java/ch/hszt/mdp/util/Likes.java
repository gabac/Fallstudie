package ch.hszt.mdp.util;

import ch.hszt.mdp.domain.Activity;
import ch.hszt.mdp.domain.User;

public class Likes {

	public static boolean likes(User user, Activity activity) {

		for (Activity like : activity.getLikes()) {
			if (like.getUser().getId() == user.getId()) {
				return true;
			}
		}

		return false;
	}
}
