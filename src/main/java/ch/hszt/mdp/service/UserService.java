package ch.hszt.mdp.service;

import java.util.List;

import ch.hszt.mdp.domain.User;

public interface UserService {

	public List<User> queryEmail(String email);

	public void create(User user);
}
