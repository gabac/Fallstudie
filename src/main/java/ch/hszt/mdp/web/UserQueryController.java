package ch.hszt.mdp.web;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ch.hszt.mdp.domain.User;
import ch.hszt.mdp.service.UserService;

@Controller
@RequestMapping("/userQuery")
public class UserQueryController {
private UserService userService;
	
	@Autowired
	public UserQueryController(UserService userService) {
		this.userService = userService;
	}
	
	@RequestMapping(method = RequestMethod.GET) 
	public void setupForm() {
		
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String submitForm(@RequestParam("nick") String nick, Model model) {
		List<User> users = Collections.emptyList();
		if(nick != null) {
			users = userService.queryNick(nick);
		}
		model.addAttribute("users", users);
		return "userQuery";
		
	}
}
