package ch.hszt.mdp.web;

import static org.springframework.test.web.server.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.server.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.server.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.server.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.server.setup.MockMvcBuilders.standaloneSetup;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.server.MockMvc;

import ch.hszt.mdp.service.FriendshipService;
import ch.hszt.mdp.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:mdp-test-daos.xml" })
public class UsersControllerTest {

	@Autowired
	private UserService userService;

	private FriendshipService friendshipService;

	private MockMvc mvc;

	@Before
	public void setup() {
		mvc = standaloneSetup(new UsersController(userService, friendshipService)).build();
	}

	@Test
	public void testRegister() throws Exception {

		mvc.perform(
				post("/users").param("email", "gabathuler@gmail.com").param("prename", "Cyril").param("surname", "Gabathuler")
						.param("password", "123").param("repeat", "123").param("birthdate", "2011-12-01").param("city", "Baden"))
				.andExpect(view().name("redirect:/"));
	}

	@Test
	public void testRegisterError() throws Exception {

		mvc.perform(post("/users")).andExpect(model().attributeHasErrors("user"));
	}

	@Test
	public void testRegistrationForm() throws Exception {

		mvc.perform(get("/users")).andExpect(view().name("users/registration"));
	}
}
