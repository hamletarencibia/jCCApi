package jcc.api.database.sqloader;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.LinkedList;

/**
 * Indicates that a field declaration is a One-To-Many relationship. This link is used by the {@link SQLoader} class. The field must be a {@link LinkedList} of the targeted class type.
 * @author Hamlet Arencibia Casanova
 *
 */
@Target(value = ElementType.FIELD)
@Retention(value = RetentionPolicy.RUNTIME)
public @interface OneToMany {
	/**
	 * The name of the referenced field of the target class to match the values.
	 * @return a {@link String} with the name of the referenced field of the target class.
	 */
	public String mappedBy() default "";
}
