package ch.hszt.mdp.web;

import java.security.Principal;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ch.hszt.mdp.service.UserService;

@Controller
@RequestMapping("/")
public class StreamController {

	@Autowired
	private UserService userService;

	private DateTime dt;

	public StreamController() {
		dt = new DateTime();
	}

	public StreamController(UserService service, DateTime dt) {
		this.userService = service;
		this.dt = dt;
	}

	@RequestMapping(method = RequestMethod.GET)
	public String list(Model model, Principal principal) {
		model.addAttribute("today", dt.toDate());

		dt = dt.minusDays(1);
		model.addAttribute("yesterday", dt.toDate());

		dt = dt.minusDays(1);
		model.addAttribute("dayBeforeYesterday", dt.toDate());

		//todo remove if found out how to test the principal
		if (principal != null) {
			model.addAttribute("activities", userService.getActivitiesFromFriends(principal.getName()));
		}

		return "stream/list";
	}
}
