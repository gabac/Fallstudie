package ch.hszt.mdp.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ch.hszt.mdp.domain.User;
import ch.hszt.mdp.service.UserService;

@Controller
@RequestMapping(value = "/users")
public class UsersController {

	private UserService service;

	@Autowired
	public UsersController(UserService service) {
		this.service = service;
	}

	@RequestMapping(method = RequestMethod.GET)
	public String getRegistrationForm(Model model) {

		model.addAttribute(new User());

		return "users/registration";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String register(@Valid User user, BindingResult result) {

		if (result.hasErrors()) {
			return "users/registration";
		}

		// create user
		service.create(user);

		return "redirect:/";
	}
}
