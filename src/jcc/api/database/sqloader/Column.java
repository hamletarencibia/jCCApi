package jcc.api.database.sqloader;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Indicates that a field declaration is linked to a column on a table in a database. This link is used by the {@link SQLoader} class.
 * @author Hamlet Arencibia Casanova
 *
 */
@Target(value = ElementType.FIELD)
@Retention(value = RetentionPolicy.RUNTIME)
public @interface Column {
	/**
	 * The value type of the field.
	 * @return a {@link String} with the type name.
	 */
	public String type() default "";
	/**
	 * The column on the database linked to the field.
	 * @return a {@link String} with the name of the column.
	 */
	public String column() default "";
}
