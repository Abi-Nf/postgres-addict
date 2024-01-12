package postgres.addict.core.annotations.definition;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.SneakyThrows;
import lombok.ToString;
import postgres.addict.Column;
import postgres.addict.ColumnType;
import postgres.addict.GenerativeValue;

import java.lang.reflect.Field;

@ToString
@Getter
@EqualsAndHashCode
public class ColumnDefinition {
  private static final int VARCHAR_MAX = 255;
  private static final int PRECISION_MAX = 1000;
  private static final int SCALE_MIN = -9999;
  private static final int SCALE_MAX = 9999;

  private String name;
  private String defaultValue;
  private String columnType;
  private int size;
  private int precision;
  private int scale;
  private boolean required;
  private boolean identity;
  private boolean references;
  private boolean unique;
  private GenerativeValue generative;
  private final boolean column;
  private final Field field;

  public ColumnDefinition(Field field){
    this.field = field;
    Column column = field.getAnnotation(Column.class);
    if(column != null){
      String columnName = column.name();
      if(columnName.isEmpty()){
        columnName = field.getName();
      }
      this.name = columnName;
      this.mapType(column, field);
      this.defaultValue = column.defaultValue();
      this.column = true;
      this.required = column.required();
      this.identity = column.identity();
      this.references = column.references();
      this.unique = column.unique();
      this.generative = column.generative();
      return;
    }
    this.column = false;
  }

  @SneakyThrows
  public Object createInstanceWithReturnType(){
    return this.field.getType().getDeclaredConstructor().newInstance();
  }

  private void mapType(Column column, Field field){
    String type = column.columnType();

    if(type.isEmpty() || type.equals(ColumnType.DETECT)){
      type = TypeMapper.javaToPsql(field.getType().getSimpleName().toLowerCase());
    }

    this.columnType = type;
    final int size = column.size();
    final int precision = column.precision();
    final int scale = column.scale();

    switch (type){
      case ColumnType.VARCHAR:
        if(size > 0 && size <= VARCHAR_MAX){
          this.size = size;
        }else {
          this.size = VARCHAR_MAX;
        }
      break;

      case ColumnType.CHAR, ColumnType.BPCHAR:
        if(size > 0){
          this.size = size;
        }else {
          this.size = 1;
        }
      break;

      case ColumnType.NUMERIC:
        if(precision > 0 && precision <= PRECISION_MAX){
          this.precision = precision;
        }else {
          this.precision = PRECISION_MAX;
        }

        if(scale >= SCALE_MIN && scale <= SCALE_MAX){
          this.scale = scale;
        }else {
          this.scale = 10;
        }
      break;

      default:
      break;
    }
  }
}
