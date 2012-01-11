package ch.hszt.mdp.web;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ch.hszt.mdp.domain.Activity;
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

	@ModelAttribute("privacies")
	public Map<String, String> populatePrivacies() {

		HashMap<String, String> privacies = new HashMap<String, String>();
		privacies.put("everyone", "Everyone");
		privacies.put("friends", "Friends");

		return privacies;
	}

	@RequestMapping(method = RequestMethod.GET)
	public String list(Model model, Principal principal) {

		dt = new DateTime();

		model.addAttribute("today", dt);

		dt = dt.minusDays(1);
		model.addAttribute("yesterday", dt);

		model.addAttribute("stream", userService.getActivitiesFromFriends(principal.getName()));

		model.addAttribute("unaccepedFriends", userService.getUnaccepteFriendships(principal.getName()));

		model.addAttribute(new Activity());

		return "stream/list";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String updateStatus(@Valid Activity activity, Principal principal) {

		activity.setUser(userService.getUserByEmail(principal.getName()));

		activityService.updateStatus(activity);

		return "redirect:/v1/";
	}

	@RequestMapping(value = "activity/{id}", method = RequestMethod.GET)
	public String like(@PathVariable("id") int id, Model model, Principal principal) {

		Activity activity = activityService.getActivity(id);

		model.addAttribute(activity);

		return "stream/activity";
	}

	@RequestMapping(value = "activity/{id}/like", method = RequestMethod.POST)
	@ResponseBody
	public void like(@PathVariable("id") int id, Principal principal) {

		Activity activity = activityService.getActivity(id);

		activityService.like(userService.getUserByEmail(principal.getName()), activity);
	}

	@RequestMapping(value = "activity/{id}/unlike", method = RequestMethod.POST)
	@ResponseBody
	public void unlike(@PathVariable("id") int id, Principal principal) {

		Activity activity = activityService.getActivity(id);

		activityService.unlike(userService.getUserByEmail(principal.getName()), activity);
	}
}
