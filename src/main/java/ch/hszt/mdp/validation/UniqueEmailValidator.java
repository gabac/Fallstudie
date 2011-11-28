package ch.hszt.mdp.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import ch.hszt.mdp.dao.UserDao;

public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {

	@Autowired
	private UserDao userDao;

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public void initialize(UniqueEmail constraint) {

	}

	@Override
	public boolean isValid(String email, ConstraintValidatorContext constraint) {

		if (userDao != null) {
			return !userDao.duplicate(email);
		}
		return true;
	}
}
