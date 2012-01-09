package ch.hszt.mdp.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import ch.hszt.mdp.domain.User;

@Controller
@RequestMapping("/api/activities")
public class ActivitiesResource extends Resource {

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public Object list(@RequestParam("api_key") String apiKey) {

		try {
			User user = getUser(apiKey);

			return userService.getActivitiesFromFriends(user.getEmail());

		} catch (Exception e) {
			return e.getMessage();
		}
	}
}
