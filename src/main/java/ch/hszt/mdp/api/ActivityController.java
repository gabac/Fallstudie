package ch.hszt.mdp.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ch.hszt.mdp.domain.Stream;
import ch.hszt.mdp.service.UserService;

@Controller
@RequestMapping("/api/activity")
public class ActivityController {

	@Autowired
	private UserService userService;

	public ActivityController() {

	}

	public ActivityController(UserService service) {
		this.userService = service;
	}

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public Stream list(Model model) {

		return userService.getActivitiesFromFriends("fabian.vogler@bluewin.ch");
	}
}
