package ch.hszt.mdp.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import ch.hszt.mdp.domain.User;

public class PasswordValidator implements ConstraintValidator<PasswordsEqual, User> {

	@Override
	public void initialize(PasswordsEqual constraint) {

	}

	@Override
	public boolean isValid(User user, ConstraintValidatorContext constraint) {

		return user.getPassword().equals(user.getRepeat());
	}

}
