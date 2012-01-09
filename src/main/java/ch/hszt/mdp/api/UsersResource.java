package ch.hszt.mdp.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import ch.hszt.mdp.domain.User;

@Controller
@RequestMapping("/api/users")
public class UsersResource extends Resource {

	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	@ResponseBody
	public Object list(@PathVariable("id") int id, @RequestParam("api_key") String apiKey) {

		try {
			User remote = getUser(apiKey);
			User user = userService.getUser(id);

			return user;

		} catch (Exception e) {
			return e.getMessage();
		}
	}
}
