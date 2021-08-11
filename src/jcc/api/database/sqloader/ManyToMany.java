package jcc.api.database.sqloader;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Indicates that a field declaration is a Many-To-Many relationship. This link is used by the {@link SQLoader} class.
 * @author Hamlet Arencibia Casanova
 *
 */
@Target(value = ElementType.FIELD)
@Retention(value = RetentionPolicy.RUNTIME)
public @interface ManyToMany {
	public String joinTable();
	public String columnName() default "";
	public String referencedColumnName() default "";
	public String targetReferencedColumnName() default "";
}
