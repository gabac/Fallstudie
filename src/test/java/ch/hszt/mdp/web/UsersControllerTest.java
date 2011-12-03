package ch.hszt.mdp.web;

import static org.junit.Assert.assertEquals;

import javax.servlet.http.HttpServletRequest;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.annotation.AnnotationMethodHandlerAdapter;

import ch.hszt.mdp.domain.User;
import ch.hszt.mdp.service.UserService;
import ch.hszt.mdp.validation.DateTimePropertyEditor;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:mdp-test-daos.xml" })
public class UsersControllerTest {

	@Autowired
	private Validator validator;

	@Autowired
	private UserService userService;

	private AnnotationMethodHandlerAdapter adapter;

	private MockHttpServletResponse response;

	private UsersController controller;

	@Before
	public void setup() {
		adapter = new AnnotationMethodHandlerAdapter();
		response = new MockHttpServletResponse();
		controller = new UsersController(userService);
	}

	@Test
	public void testRegister() throws Exception {

		// http://blog.vergiss-blackjack.de/2010/04/unit-testing-a-spring-controller/

		MockHttpServletRequest request = new MockHttpServletRequest("POST", "/users");

		request.setParameter("email", "gabathuler@gmail.com");
		request.setParameter("prename", "Cyril");
		request.setParameter("surname", "Gabathuler");
		request.setParameter("password", "123");
		request.setParameter("repeat", "123");
		request.setParameter("birthdate", "2011-12-01");
		request.setParameter("city", "Baden");

		ModelAndView mv = adapter.handle(request, response, controller);

		assertEquals("redirect:/", mv.getViewName());
	}

	@Test
	public void testRegisterError() throws Exception {

		MockHttpServletRequest request = new MockHttpServletRequest("POST", "/users");

		BindingResult result = validate(request);

		assertEquals(8, result.getErrorCount());
	}

	@Test
	public void testRegistrationForm() throws Exception {
		MockHttpServletRequest request = new MockHttpServletRequest("GET", "/users");

		ModelAndView mv = adapter.handle(request, response, controller);

		assertEquals("users/registration", mv.getViewName());

	}

	private BindingResult validate(HttpServletRequest request) {

		User user = new User();

		WebDataBinder binder = new WebDataBinder(user);

		binder.setValidator(validator); // use the validator from the context
		binder.registerCustomEditor(DateTime.class, null, new DateTimePropertyEditor("yyyy-MM-dd"));

		binder.bind(new MutablePropertyValues(request.getParameterMap()));

		// validation must be triggered manually
		binder.getValidator().validate(binder.getTarget(), binder.getBindingResult());
		return binder.getBindingResult();
	}
}
