package jcc.api.database.sqloader;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Indicates that a class declaration is an entity that simulates a SQL table. This condition is used in the {@link SQLoader} class.
 * @author Hamlet Arencibia Casanova
 *
 */
@Target(value = ElementType.TYPE)
@Retention(value = RetentionPolicy.RUNTIME)
public @interface Entity {

}
