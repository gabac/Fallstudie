package ch.hszt.mdp.api;

import org.springframework.beans.factory.annotation.Autowired;

import ch.hszt.mdp.domain.User;
import ch.hszt.mdp.service.UserService;

public class Resource {

	@Autowired
	protected UserService userService;

	public Resource() {

	}

	public Resource(UserService service) {
		this.userService = service;
	}

	protected User getUser(String apiKey) throws Exception {

		try {
			String[] keys = apiKey.split(":");
			int id = Integer.parseInt(keys[0]);

			User user = userService.getUser(id);

			if (user == null) {
				throw new Exception("Unknown user");
			}

			if (user.getApiKey().equals(keys[1])) {
				return user;
			}

		} catch (NumberFormatException e) {
			throw new Exception("Invalid API key");
		}

		throw new Exception("Access denied");
	}
}
