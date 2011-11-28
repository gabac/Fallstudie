package ch.hszt.mdp.web;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.annotation.AnnotationMethodHandlerAdapter;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:mdp-test-daos.xml" })
public class AuthControllerTest {

	private AnnotationMethodHandlerAdapter adapter;

	private MockHttpServletResponse response;

	private AuthController controller;

	@Before
	public void setup() {
		adapter = new AnnotationMethodHandlerAdapter();
		response = new MockHttpServletResponse();
		controller = new AuthController();
	}
	
	
	@Test
	public void testLogin() throws Exception{
		MockHttpServletRequest request = new MockHttpServletRequest("GET", "/auth");
		ModelAndView mAv = adapter.handle(request, response, controller);

		assertEquals("auth/login", mAv.getViewName());
	}
	
	@Test
	public void testFailed() throws Exception{
		MockHttpServletRequest request = new MockHttpServletRequest("POST", "/auth/failed");
		
		ModelAndView mAv = adapter.handle(request, response, controller);

		assertEquals("auth/failed", mAv.getViewName());
	}

	@Test
	public void testLogout() throws Exception {

		MockHttpServletRequest request = new MockHttpServletRequest("POST", "/auth/logout");

		MockHttpSession session = new MockHttpSession();
		request.setSession(session);

		ModelAndView mAv = adapter.handle(request, response, controller);

		assertTrue(session.isInvalid());
		assertEquals("redirect:/", mAv.getViewName());
	}
}
