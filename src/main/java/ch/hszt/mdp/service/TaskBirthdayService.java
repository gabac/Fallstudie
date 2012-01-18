package ch.hszt.mdp.service;

import ch.hszt.mdp.dao.TaskBirthdayDao;
import ch.hszt.mdp.dao.UserDao;

public interface TaskBirthdayService {
	
	public void setTaskBirthdayDao(TaskBirthdayDao taskBirthdayDao);
	public void sayHappyBirthday();

}
