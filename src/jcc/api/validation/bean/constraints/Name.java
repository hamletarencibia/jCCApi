package jcc.api.validation.bean.constraints;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import jcc.api.validation.bean.validators.NameValidator;

@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(Name.List.class)
@Documented
@Constraint(validatedBy = {NameValidator.class})
public @interface Name {
	public abstract String message() default "The value can't contain numbers or special characters.";
	public abstract Class<?>[] groups() default {};
	public abstract Class<? extends Payload>[] payload() default {};
	
	@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
	@Retention(RetentionPolicy.RUNTIME)
	@Documented
	public static @interface List {
		public abstract Name[] value();
	}
}
