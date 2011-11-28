package ch.hszt.mdp.web;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Calendar;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.annotation.AnnotationMethodHandlerAdapter;

import ch.hszt.mdp.domain.User;
import ch.hszt.mdp.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:mdp-test-daos.xml" })
public class UsersControllerTest {

	@Autowired
	UserService userService;

	private MockHttpServletRequest request;
	private MockHttpServletResponse response;
	private AnnotationMethodHandlerAdapter adapter;
	private UsersController usersController;

	@Before
	public void setup() {

		request = new MockHttpServletRequest();
		response = new MockHttpServletResponse();
		adapter = new AnnotationMethodHandlerAdapter();
		usersController = new UsersController(userService);
	}

	@Test
	public void testLogout() {
		try {
			request.setMethod("GET");
			request.setRequestURI("/users/logout");

			MockHttpSession session = new MockHttpSession();
			request.setSession(session);

			ModelAndView mAv = adapter.handle(request, response, usersController);

			assertTrue(session.isInvalid());
			assertEquals("redirect:/", mAv.getViewName());
		} catch (Exception e) {
			fail();
		}
	}

	@Test
	public void testRegistrationForm() {
		try {
			request.setMethod("GET");
			request.setRequestURI("/users");
			ModelAndView mAv = adapter.handle(request, response, usersController);
			assertEquals("users/registration", mAv.getViewName());
		} catch (Exception e) {
			fail();
		}
	}

	@Test
	public void testRegister() {
		try {
			request.setMethod("POST");
			request.setRequestURI("/users");

			request.setParameter("email", "gabathuler@gmail.com");
			request.setParameter("prename", "Cyril");
			request.setParameter("surname", "Gabathuler");
			request.setParameter("password", "123");
			request.setParameter("repeat", "123");
			request.setParameter("birthdate", "1988-06-29");
			request.setParameter("city", "Baden");

			ModelAndView mAv = adapter.handle(request, response, usersController);

			assertEquals("users/registration", mAv.getViewName());
		} catch (Exception e) {
			fail();
		}
	}
}
