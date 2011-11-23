package ch.hszt.mdp.service;

import java.util.List;

import ch.hszt.mdp.domain.User;

public interface UserService {

	public List<User> queryNick(String nick);
}
