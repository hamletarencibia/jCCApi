package jcc.api.validation.bean.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import jcc.api.validation.bean.constraints.CI;

public class CIValidator implements ConstraintValidator<CI, String>{

	@Override
	public boolean isValid(String arg0, ConstraintValidatorContext arg1) {
		return jcc.api.objects.CI.validate(arg0);
	}

}
