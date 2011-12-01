package ch.hszt.mdp.web;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;

import ch.hszt.mdp.domain.User;
import ch.hszt.mdp.service.UserService;
import ch.hszt.mdp.validation.DateTimePropertyEditor;

/**
 * 
 * Controller for all request beyond "/users" The controller uses the userService to get the data from hibernate. The
 * object is injected via Spring
 * 
 * @author gaba, fabian
 * 
 */

@Controller
@RequestMapping(value = "/users")
public class UsersController {

	private UserService service;

	@Autowired
	public UsersController(UserService service) {
		this.service = service;
	}

	/**
	 * 
	 * Used to register the binder for the photo upload
	 * 
	 * @param request
	 * @param binder
	 * @throws ServletException
	 */
	@InitBinder
	protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws ServletException {

		binder.registerCustomEditor(DateTime.class, null, new DateTimePropertyEditor("yyyy-MM-dd"));

		// Convert multipart object to byte[]
		binder.registerCustomEditor(byte[].class, new ByteArrayMultipartFileEditor());
	}

	/**
	 * Displays the register form and the User object is added to the context of the JSP.
	 * 
	 * @param model
	 * @return jsp view
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String getRegistrationForm(Model model) {

		model.addAttribute(new User());

		return "users/registration";
	}

	@RequestMapping(value = "profile", method = RequestMethod.GET)
	public String getProfileForm(Model model, Principal principal) {

		User user = service.getUserByEmail(principal.getName());

		model.addAttribute("user", user);
		model.addAttribute("friends", user.getFriendships());

		return "users/profile";
	}

	/**
	 * 
	 * The post request from the registration page. If there is no error the user object will be saved to the database
	 * 
	 * @param user
	 * @param result
	 * @return the jsp view
	 */
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
