package ch.hszt.mdp.domain;

import java.io.Serializable;

public class FriendshipId implements Serializable {

	private Integer primary_user;

	private Integer secondary_user;

	public int hashCode() {
		return (int) (primary_user + secondary_user);
	}

	public boolean equals(Object object) {
		if (object instanceof FriendshipId) {
			FriendshipId otherId = (FriendshipId) object;
			return (otherId.primary_user == this.primary_user) && (otherId.secondary_user == this.secondary_user);
		}
		return false;
	}

}
