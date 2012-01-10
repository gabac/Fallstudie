package ch.hszt.mdp.api;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ch.hszt.mdp.domain.Stream;
import ch.hszt.mdp.service.UserService;

@Controller
@RequestMapping("/api/activities")
public class ActivitiesResource {

	@Autowired
	protected UserService userService;

	public ActivitiesResource() {

	}

	public ActivitiesResource(UserService service) {
		this.userService = service;
	}

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public Stream list(Principal principal) {

		return userService.getActivitiesFromFriends(principal.getName());
	}
}
