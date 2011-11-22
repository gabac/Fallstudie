package ch.hszt.mdp.service;

import java.util.ArrayList;
import java.util.List;

import ch.hszt.mdp.domain.User;

//TODO Implements with DB persistence
public class UserServiceImpl implements UserService {

	private List<User> nicks;

	public UserServiceImpl() {
		nicks = new ArrayList<User>();

		nicks.add(new User("Cyril Gabathuler", "gabathuler@gmail.com", "gaba"));
		nicks.add(new User("Fabian Vogler", "fabian.vogler@gmail.com", "euklid"));
	}

	@Override
	public List<User> queryNick(String nick) {
		List<User> result = new ArrayList<User>();

		for (User user : nicks) {
			if (user.getNick().equals(nick)) {
				result.add(user);
			}
		}
		return result;
	}

}
