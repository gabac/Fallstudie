package ch.hszt.mdp.web;

import java.io.IOException;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ch.hszt.mdp.domain.User;
import ch.hszt.mdp.service.FriendshipService;
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
	private FriendshipService friendshipService;

	@Autowired
	public UsersController(UserService service, FriendshipService friendshipService) {

		this.service = service;
		this.friendshipService = friendshipService;
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

	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public String getProfileForm(@PathVariable("id") int id, Model model, Principal principal) {

		// id = aufgerufenes profil
		User friend = service.getUser(id);

		// eingeloggter user = roger
		User myself = service.getUserByEmail(principal.getName());

		// List<Friendship> birthday = service.getAccepteFriendships(principal.getName());
		// List<Integer> birthdayUser = new ArrayList<Integer>();
		//
		// for (int i = 0; i < birthday.size(); i++) {
		// birthdayUser.add(birthday.get(i).getSecondaryUser().getAge());
		// }
		//

		User user = service.getUserByEmail(principal.getName());

		boolean alreadyfriends = friendshipService.checkForFriendship(friend, myself);
		model.addAttribute("profile", friend);
		model.addAttribute("alreadyFriends", alreadyfriends);
		model.addAttribute("accepedFriends", service.getAccepteFriendships(user));

		return "users/profile";
	}

	@RequestMapping(value = "{id}/edit", method = RequestMethod.GET)
	public String getProfileFormEdit(@PathVariable("id") int id, Model model, Principal principal) {

		User user = service.getUser(id);
		model.addAttribute("profile", user);

		return "users/edit";
	}

	/**
	 * Accept friend request by clicking on the accept button on the GUI. Reload page by returning
	 * "redirect:/v1/users/"+id;
	 * 
	 * @author Roger Bollmann
	 * @param id
	 * @param friendId
	 * @param model
	 * @param principal
	 * @return
	 */

	@RequestMapping(value = "{id}/accept/{friendId}", method = RequestMethod.GET)
	public String getAcceptFriend(@PathVariable("id") int id, @PathVariable("friendId") int friendId, Model model, Principal principal) {

		service.acceptFriend(friendId, id);

		return "redirect:/v1/users/" + id;
	}

	/**
	 * Ignore friend request by clicking on the ignore button on the GUI. Reload page by returning
	 * "redirect:/v1/users/"+id;
	 * 
	 * @author Roger Bollmann
	 * @param id
	 * @param friendId
	 * @param model
	 * @param principal
	 * @return "redirect:/v1/users/"+id
	 */
	@RequestMapping(value = "{id}/ignore/{friendId}", method = RequestMethod.GET)
	public String getIgnoreFriend(@PathVariable("id") int id, @PathVariable("friendId") int friendId, Model model, Principal principal) {

		service.ignoreFriend(friendId, id);

		return "redirect:/v1/users/" + id;
	}

	@RequestMapping(value = "{id}/image", method = RequestMethod.GET)
	public ResponseEntity<byte[]> image(@PathVariable("id") int id, Model model, Principal principal) {

		User user = service.getUser(id);

		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.setContentType(MediaType.parseMediaType("image/png"));
		responseHeaders.setContentLength(user.getPhoto().length);

		return new ResponseEntity<byte[]>(user.getPhoto(), responseHeaders, HttpStatus.OK);
	}

	private ResponseEntity<byte[]> photo(int id, int size, boolean crop) throws IOException {

		byte[] photo = service.getPhoto(id, 300, false);

		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.setContentType(MediaType.parseMediaType("image/png"));
		responseHeaders.setContentLength(photo.length);

		return new ResponseEntity<byte[]>(photo, responseHeaders, HttpStatus.OK);
	}

	private ResponseEntity<byte[]> photoPeople(int id, int size, boolean crop) throws IOException {

		byte[] photo = service.getPhoto(id, size, crop);

		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.setContentType(MediaType.parseMediaType("image/png"));
		responseHeaders.setContentLength(photo.length);

		return new ResponseEntity<byte[]>(photo, responseHeaders, HttpStatus.OK);
	}

	@RequestMapping(value = "{id}/thumbnail", method = RequestMethod.GET)
	public ResponseEntity<byte[]> thumbnail(@PathVariable("id") int id, Model model, Principal principal) throws IOException {

		return photo(id, 300, false);
	}

	@RequestMapping(value = "{id}/preview", method = RequestMethod.GET)
	public ResponseEntity<byte[]> preview(@PathVariable("id") int id, Model model, Principal principal) throws IOException {

		return photoPeople(id, 50, true);
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

	@RequestMapping(value = "{id}/edit", method = RequestMethod.POST)
	public String update(@PathVariable("id") int id, @Valid @ModelAttribute("profile") User user, BindingResult result,
			RedirectAttributes redirectAttributes, HttpSession session) {

		User origin = service.getUser(id);

		if (result.hasErrors()) {

			return "users/edit";

		}

		boolean auth = false;
		if (user.getPassword().equals("") && user.getEmail().equals(origin.getEmail())) {
			auth = true;
		}

		// update user
		service.updateUser(origin, user);

		redirectAttributes.addFlashAttribute("message", "Your profile has been updated.");

		if (auth) {
			return "redirect:/v1/users/" + id;
		} else {
			session.invalidate();
			return "redirect:/";
		}
	}

	@RequestMapping(value = "{friendId}/ask/{id}", method = RequestMethod.GET)
	public String getAskForFriend(@PathVariable("id") int id, @PathVariable("friendId") int friendId, Model model, Principal principal,
			RedirectAttributes redirectAttributes) {

		User friend = service.getUser(friendId);
		User user = service.getUser(id);

		try {
			friendshipService.askForFriendship(friend, user);

		} catch (Exception e) {
			e.printStackTrace();
		}

		redirectAttributes.addFlashAttribute("message", friend.getPrename() + " has been asked for a friendship.");

		return "redirect:/";
	}

	@RequestMapping(value = "search", method = RequestMethod.GET)
	public String search(HttpServletRequest request, Model model, Principal principal, HttpSession session) {

		User user = service.getUserByEmail(principal.getName());

		List<User> users = service.searchUser(request.getParameter("search"), user);

		model.addAttribute("users", users);

		return "users/search";
	}

	@ModelAttribute("privacies")
	public Map<String, String> populatePrivacies() {

		HashMap<String, String> privacies = new HashMap<String, String>();
		privacies.put("everyone", "Everyone");
		privacies.put("friends", "Friends");

		return privacies;
	}

	@RequestMapping(value = "{id}/privacy", method = RequestMethod.GET)
	public String privacy(@PathVariable("id") int id, Model model, Principal principal) {

		User user = service.getUser(id);
		model.addAttribute("profile", user);

		return "users/privacy";
	}

	@RequestMapping(value = "{id}/privacy", method = RequestMethod.POST)
	public String savePrivacy(@Validated(User.Privacy.class) @ModelAttribute("profile") User user, Model model, BindingResult result,
			RedirectAttributes redirectAttributes) {

		if (result.hasErrors()) {

			return "users/privacy";
		}

		// update user
		service.updatePrivacy(user);

		redirectAttributes.addFlashAttribute("message", "Privacy settings have been saved.");

		return "redirect:/v1/";
	}
}
