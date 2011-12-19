package ch.hszt.mdp.web;

import static org.springframework.test.web.server.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.server.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.server.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.server.setup.MockMvcBuilders.standaloneSetup;

import org.junit.Before;
import org.junit.Test;
import org.springframework.test.web.server.MockMvc;

public class AuthControllerTest {

	private MockMvc mvc;

	@Before
	public void setup() {
		mvc = standaloneSetup(new AuthController()).build();
	}

	@Test
	public void testLogin() throws Exception {

		mvc.perform(get("/auth")).andExpect(view().name("auth/login"));
	}

	@Test
	public void testFailed() throws Exception {

		mvc.perform(post("/auth/failed")).andExpect(view().name("auth/failed"));
	}

	@Test
	public void testLogout() throws Exception {

		mvc.perform(post("/auth/logout")).andExpect(view().name("redirect:/"));
	}
}
