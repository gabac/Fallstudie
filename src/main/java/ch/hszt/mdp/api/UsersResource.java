package ch.hszt.mdp.api;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

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

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public ArrayList<HashMap<String, String>> list(HttpServletRequest request, Principal principal) {

		ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

		for (User user : userService.getUsers()) {

			HashMap<String, String> map = new HashMap<String, String>();
			map.put("prename", user.getPrename());
			map.put("surname", user.getSurname());
			map.put("id", user.getId() + "");

			list.add(map);
		}

		return list;
	}

	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	@ResponseBody
	public User get(@PathVariable("id") int id, Principal principal) {

		User user = userService.getUser(id);

		return user;
	}
}
