package postgres.addict;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Target(TYPE)
@Retention(RUNTIME)
public @interface Table {
  /**
   * the table name
   */
  String name() default "";

  /**
   * The schema to set the table. <br>
   * by default, it is "public"
   */
  String schema() default "public";
}
