package ch.hszt.mdp.api;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ch.hszt.mdp.domain.Activity;
import ch.hszt.mdp.domain.Stream;
import ch.hszt.mdp.service.ActivityService;
import ch.hszt.mdp.service.UserService;

@Controller
@RequestMapping("/api/activities")
public class ActivitiesResource {

	@Autowired
	protected UserService userService;

	@Autowired
	protected ActivityService activityService;

	public ActivitiesResource() {

	}

	public ActivitiesResource(UserService service, ActivityService activityService) {
		this.userService = service;
		this.activityService = activityService;
	}

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public Stream list(Principal principal) {

		return userService.getActivitiesFromFriends(principal.getName());
	}

	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public Activity create(@Valid Activity activity, Principal principal) {

		activity.setUser(userService.getUserByEmail(principal.getName()));

		activityService.updateStatus(activity);

		return activity;
	}
}
