package postgres.addict.core.annotations.definition;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.SneakyThrows;
import lombok.ToString;
import postgres.addict.Column;
import postgres.addict.Table;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

@Getter
@ToString
@EqualsAndHashCode
public class TableDefinition<T> {
  private final String name;
  private final String schema;
  private ColumnDefinition columnIdentity;
  private final List<ColumnDefinition> columns = new ArrayList<>();

  private final Class<T> clazz;

  public TableDefinition(Class<T> clazz) throws Exception {
    Table table = clazz.getAnnotation(Table.class);
    if(table != null){
      this.clazz = clazz;
      String name = table.name().replace(" ", "_");
      if(name.isEmpty()){
        name = clazz.getSimpleName().toLowerCase();
      }
      this.name = name;
      this.schema = table.schema();

      Field[] fields = clazz.getDeclaredFields();
      for (Field field : fields) {
        ColumnDefinition columnDef = new ColumnDefinition(field);
        if(columnDef.isColumn()){
          if(columnDef.isIdentity()){
            if(this.columnIdentity != null){
              throw new Exception("Table: " + this.name + " has already one column used for primary key");
            }
            this.columnIdentity = columnDef;
          }else {
            this.columns.add(columnDef);
          }
        }
      }
      if(this.columnIdentity == null){
        throw new Exception("Table: " + this.name + " doesn't contain column used for primary key");
      }
      return;
    }
    throw new Exception("class: " + clazz.getName() + " doesn't have annotation @Table");
  }

  @SneakyThrows
  public T createInstance(){
    return this.clazz.getDeclaredConstructor().newInstance();
  }

  @SneakyThrows
  public void setValueToFieldInstance(Object instance, Field field, Object value){
    Column column = field.getAnnotation(Column.class);
    if(column == null) return;

    if (column.references()) {
      TableDefinition<?> refDef = new TableDefinition<>(field.getType());
      Field refColumnId = refDef.getColumnIdentity().getField();
      Object refInstance = refDef.createInstance();
      refDef.setValueToFieldInstance(refInstance, refColumnId, value);
      return;
    }

    this.clazz.getMethod(
        this.getSetterFromField(field),
        field.getType()
    ).invoke(instance, value);
  }

  @SneakyThrows
  public Object getValueFromField(Object instance, Field field){
    boolean booleanType = this.isFieldBooleanType(field);
    String getterBegin = booleanType ? "is" : "get";
    Column column = field.getAnnotation(Column.class);
    Object value = this
        .getClazz()
        .getMethod(getterBegin + this.capitalizeName(field))
        .invoke(instance);

    if (column.references()) {
      TableDefinition<?> refDef = new TableDefinition<>(value.getClass());
      return refDef.getValueFromField(value, refDef.getColumnIdentity().getField());
    }
    return value;
  }

  private boolean isFieldBooleanType(Field field){
    return field.getType().getSimpleName().equalsIgnoreCase("boolean");
  }

  private String capitalizeName(Field field){
    String fieldName = field.getName();
    char UpperCase = Character.toUpperCase(fieldName.charAt(0));
    String rest = fieldName.substring(1);
    return UpperCase + rest;
  }

  private String getSetterFromField(Field field){
    return "set" + this.capitalizeName(field);
  }
}
