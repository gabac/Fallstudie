package ch.hszt.mdp.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/auth")
public class LoginController {

	@RequestMapping(method = RequestMethod.GET)
	public String login(Model model) {

		return "auth/login";
	}
}
