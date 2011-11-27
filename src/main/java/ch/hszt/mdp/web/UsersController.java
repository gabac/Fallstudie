package ch.hszt.mdp.web;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;

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

	@InitBinder
	protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws ServletException {

		// Convert multipart object to byte[]
		binder.registerCustomEditor(byte[].class, new ByteArrayMultipartFileEditor());
	}

	@RequestMapping(method = RequestMethod.GET)
	public String getRegistrationForm(Model model) {

		model.addAttribute(new User());

		return "users/registration";
	}

	@RequestMapping("/logout")
	public String logout(HttpSession session) {

		session.invalidate();

		return "redirect:/";
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
