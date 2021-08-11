package jcc.api.validation.bean.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import jcc.api.validation.bean.constraints.Name;

public class NameValidator implements ConstraintValidator<Name, java.lang.String> {
	
	@Override
	public boolean isValid(java.lang.String arg0, ConstraintValidatorContext arg1) {
		if(!arg0.matches("^([A-Za-zñÑáÁéÉíÍóÓúÚ\\s]+)$")) {
			return false;
		}
		return true;
	}

}
