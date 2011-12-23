package ch.hszt.mdp.web;

import static org.springframework.test.web.server.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.server.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.server.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.server.setup.MockMvcBuilders.standaloneSetup;

import java.security.Principal;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.server.MockMvc;

import ch.hszt.mdp.domain.Activity;
import ch.hszt.mdp.domain.Activity.ActivityType;
import ch.hszt.mdp.domain.User;
import ch.hszt.mdp.service.ActivityService;
import ch.hszt.mdp.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:mdp-test-daos.xml" })
public class StreamControllerTest {

	private User user;

	@Autowired
	private UserService userService;

	@Autowired
	private ActivityService activityService;

	private MockMvc mvc;

	private Principal principal;

	@Before
	public void setup() {
		mvc = standaloneSetup(new StreamController(userService, activityService, new DateTime())).build();

		principal = new Principal() {

			@Override
			public String getName() {
				return "gabathuler@gmail.com";
			}
		};
	}

	@Test
	public void testList() throws Exception {
		createAndSaveUser();

		mvc.perform(get("/").principal(principal)).andExpect(view().name("stream/list"));
	}

	@Test
	public void testUpdateStatus() throws Exception {
		mvc.perform(post("/").principal(principal).param("statusUpdate", "Das ist ein Test").param("privacy", "friends")).andExpect(view().name("redirect:/v1/"));
	}

	private void createAndSaveUser() {
		user = new User();
		user.setEmail("gabathuler@gmail.com");
		user.setPrename("Cyril");
		user.setSurname("Gabathuler");
		user.setPassword("123");
		user.setRepeat("123");
		user.setBirthdate(new DateTime(2000, 1, 1, 0, 0, 0, 0));
		user.setCity("Baden");

		Activity activity = new Activity();
		activity.setContent("test");
		activity.setTime(new DateTime(2000, 1, 1, 0, 0, 0, 0));
		activity.setTyp(ActivityType.PROFILE);
		activity.setUser(user);

		user.addActivity(activity);

		userService.saveUser(user);
	}
}
