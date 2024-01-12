package postgres.addict;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Retention(RUNTIME)
@Target(FIELD)
public @interface Column {
  /**
   * The column name
   */
  String name() default "";

  /**
   * The default value for the column if there is no provided value during INSERT
   */
  String defaultValue() default "";

  /**
   * the column type is by default parsed from the field return type.
   */
  String columnType() default ColumnType.DETECT;

  /**
   * the limit for some postgresql type like varchar and so on.
   * by default, they take the max size limit during execution; not 0
   */
  int size() default 0;

  /**
   * generally used for numeric type "{@link ColumnType @see ColumnType.NUMERIC}". <br>
   * precision means how much number size should be before the decimal coma
   */
  int precision() default 0;

  /**
   * generally used for numeric type "{@link ColumnType @see ColumnType.NUMERIC}". <br>
   * scale means how much decimal size should be after the decimal coma
   */
  int scale() default 0;

  /**
   * is the column is a required one while inserting new value to the database
   */
  boolean required() default false;

  /**
   * is the column a key to identify all his table container. <br>
   * in postgresql it means "Primary Key". <br>
   * Note that there should be only one max in a table
   */
  boolean identity() default false;

  /**
   * if true, the 'Field' return Type should be a class annotated with Table. <br>
   * but also, it must have a field that should be set identity true
   */
  boolean references() default false;

  /**
   * if true, the value to be inserted in database should be unique
   */
  boolean unique() default false;

  /**
   * set a generative method for the column. <br>
   * It means that whenever we don't set value, it will have one by a way
   */
  GenerativeValue generative() default GenerativeValue.NONE;
}
