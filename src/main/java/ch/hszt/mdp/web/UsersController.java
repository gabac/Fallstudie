package ch.hszt.mdp.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ch.hszt.mdp.domain.User;

@Controller
@RequestMapping(value = "/users")
public class UsersController {

	@RequestMapping(method = RequestMethod.GET)
	public String getRegistrationForm(Model model) {

		model.addAttribute(new User());

		return "users/registration";
	}
}
