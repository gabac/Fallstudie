package ch.hszt.mdp.service;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import ch.hszt.mdp.dao.TaskBirthdayDao;
import ch.hszt.mdp.dao.TaskBirthdayDaoImpl;
import ch.hszt.mdp.domain.Friendship;
import ch.hszt.mdp.domain.User;

@Service
public class TaskBirthdayServiceImpl implements TaskBirthdayService{
	

	private TaskBirthdayDao taskBirthdayDao;
	
	private UserService service;
	
	public TaskBirthdayServiceImpl(){

	}
	
//	public TaskBirthdayServiceImpl(UserService service, TaskBirthdayDao taskBirthdayDao){
//		this.taskBirthdayDao = taskBirthdayDao;
//		this.service = service;
//	
//	}
//	
	public void setTaskBirthdayDao(TaskBirthdayDao taskBirthdayDao) {
		this.taskBirthdayDao = taskBirthdayDao;
		
	}
	
	public void setUserService(UserService service) {
		this.service = service;
		
	}

	/**
	 * Compare Users Birthday with today
	 * and post a "Happy Birthday" for all User who has his Birthday today
	 */
	public void sayHappyBirthday() {
		
		//System.out.println("FOO");
		
		List<User> users = service.getUsers();
		//System.out.println("Username " +users.get(0).getPrename());
		List<User> allBirthdayUsers = new ArrayList<User>();
		//List<Friendship> friends = new ArrayList<Friendship>();
		
		//DateTime of today
		DateTime today = new DateTime();
		int dayOfMonth = today.getDayOfMonth();
		int MonthOfYear = today.getMonthOfYear();
		
		
		for (User user : users) {
			List<Friendship> friendOfUser = service.getAccepteFriendships(user);
			
			System.out.println("Friend "+friendOfUser.get(0).getSecondaryUser().getPrename());
			
			
			//Compare the Users Birthday with today
			for (Friendship friend:friendOfUser){
				System.out.println("Friend " +friend.getPrimaryUser().getPrename());
				int UserDayOfMonth = friend.getSecondaryUser().getBirthdate().getDayOfMonth();
				int UserMonthOfYear = friend.getSecondaryUser().getBirthdate().getMonthOfYear();
				
				if (UserDayOfMonth == dayOfMonth && UserMonthOfYear == MonthOfYear){
					
					User user2 = service.getUser(friend.getSecondaryUser().getId());
					allBirthdayUsers.add(user2);
					
				}
			}
			
		}
		
		

//		for (Friendship friend:friends){
//			if (friend.getSecondaryUser().getBirthdate().getDayOfYear() == td){
//				User user = service.getUser(friend.getSecondaryUser().getId());
//				users.add(user);
//			}
//		}
		
		taskBirthdayDao.postHappyBirthday(allBirthdayUsers);
		
//		System.out.println("here we go");
		
	}


}