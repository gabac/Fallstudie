package ch.hszt.mdp.web;

import static org.junit.Assert.*;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.annotation.AnnotationMethodHandlerAdapter;

import ch.hszt.mdp.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:mdp-test-daos.xml" })
public class UsersControllerTest {

	@Autowired
	UserService userService;

	private MockHttpServletRequest request;
	private MockHttpServletResponse response;
	private AnnotationMethodHandlerAdapter handler;
	private UsersController usersController;

	@Before
	public void setup() {

		request = new MockHttpServletRequest();
		response = new MockHttpServletResponse();
		handler = new AnnotationMethodHandlerAdapter();
		usersController = new UsersController(userService);
	}

	@Test
	public void testRegistrationForm() {
		try {
			request.setMethod("GET");
			request.setRequestURI("/users");
			ModelAndView mAv = handler.handle(request, response,
					usersController);
			assertEquals("users/registration", mAv.getViewName());
		} catch (Exception e) {
			fail();
		}
	}
}
