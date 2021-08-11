package jcc.api.database.sqloader;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Indicates that a field declaration is primary column on a table in a database. This annotation is used by the {@link SQLoader} class. This annotation has to be declared before the {@link Column} annotation.
 * @author Hamlet Arencibia Casanova
 *
 */
@Target(value = ElementType.FIELD)
@Retention(value=RetentionPolicy.RUNTIME)
public @interface Id {

}
