package ch.hszt.mdp.dao;

import java.util.List;

import ch.hszt.mdp.domain.Activity;
import ch.hszt.mdp.domain.User;

public interface TaskBirthdayDao {
	
	
	public void postHappyBirthday(List<User> friends);

}
