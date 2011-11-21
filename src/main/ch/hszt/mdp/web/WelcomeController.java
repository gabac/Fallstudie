package ch.hszt.mdp.web;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ch.hszt.mdp.domain.Stock;
import ch.hszt.mdp.service.StockService;

@Controller
@RequestMapping("/welcome")
public class WelcomeController {
	

	
	@RequestMapping(method = RequestMethod.GET)
	public String welcome(Model model) {
		Date today = new Date();
		model.addAttribute("today", today);

		return "welcome";
	}
}
