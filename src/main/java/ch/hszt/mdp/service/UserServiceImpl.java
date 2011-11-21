package ch.hszt.mdp.service;

import java.util.ArrayList;
import java.util.List;

import ch.hszt.mdp.domain.User;

//TODO implement with persistence

public class UserServiceImpl implements UserService {
	
	private List<User> users;
	
	public UserServiceImpl() {
		users = new ArrayList<User>();
		
		users.add(new User("cyril", "gabathuler@gmail.com", "gaba"));
		users.add(new User("fabian", "fabian.vogler@gmail.com", "euklid"));
	}
	
	@Override
	public List<User> query(String name) {
		List<User> result = new ArrayList<User>();
		
		for(User user : users) {
			if(user.getName().equals(name)) {
				result.add(user);
			}
		}
		return result;
	}

}
