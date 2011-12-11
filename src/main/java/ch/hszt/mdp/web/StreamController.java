package ch.hszt.mdp.web;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ch.hszt.mdp.service.ActivityService;
import ch.hszt.mdp.service.UserService;

@Controller
@RequestMapping("/")
public class StreamController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private ActivityService activityService;

	private DateTime dt;
	
	public StreamController() {
		
	}

	public StreamController(UserService service, ActivityService activityService, DateTime dt) {
		this.userService = service;
		this.activityService = activityService;
		this.dt = dt;
	}

	@RequestMapping(method = RequestMethod.GET)
	public String list(Model model, Principal principal) {

		dt = new DateTime();
		
		model.addAttribute("today", dt);

		dt = dt.minusDays(1);
		model.addAttribute("yesterday", dt);

		model.addAttribute("stream", userService.getActivitiesFromFriends(principal.getName()));

		return "stream/list";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String updateStatus(HttpServletRequest request, Model model, Principal principal) {
		String status = request.getParameter("statusUpdate");
		
		activityService.updateStatus(userService.getUserByEmail(principal.getName()), status);
		
		//return "redirect:/";

		return list(model, principal);
		
	}
}
