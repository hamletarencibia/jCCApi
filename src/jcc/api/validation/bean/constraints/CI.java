package jcc.api.validation.bean.constraints;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import jcc.api.validation.bean.validators.CIValidator;

@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(CI.List.class)
@Documented
@Constraint(validatedBy = {CIValidator.class})
public @interface CI {
	public abstract String message() default "Invalid value.";
	public abstract Class<?>[] groups() default {};
	public abstract Class<? extends Payload>[] payload() default {};
	
	@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER})
	@Retention(RetentionPolicy.RUNTIME)
	@Documented
	public static @interface List {
		public abstract CI[] value();
	}
}
