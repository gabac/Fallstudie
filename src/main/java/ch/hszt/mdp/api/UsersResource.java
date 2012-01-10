package ch.hszt.mdp.api;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ch.hszt.mdp.domain.User;
import ch.hszt.mdp.service.UserService;

@Controller
@RequestMapping("/api/users")
public class UsersResource {

	@Autowired
	protected UserService userService;

	public UsersResource() {

	}

	public UsersResource(UserService service) {
		this.userService = service;
	}

	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	@ResponseBody
	public Object list(@PathVariable("id") int id, Principal principal) {

		try {

			User user = userService.getUser(id);

			return user;

		} catch (Exception e) {
			return e.getMessage();
		}
	}
}
