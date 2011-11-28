package ch.hszt.mdp.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class StreamController {

	@RequestMapping(method = RequestMethod.GET)
	public String list(Model model) {

		return "stream/list";
	}
}
