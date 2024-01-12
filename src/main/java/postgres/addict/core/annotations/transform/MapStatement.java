package postgres.addict.core.annotations.transform;

import lombok.SneakyThrows;
import postgres.addict.ColumnType;
import postgres.addict.core.annotations.definition.ColumnDefinition;
import postgres.addict.core.annotations.definition.TableDefinition;

import java.lang.reflect.Field;
import java.sql.PreparedStatement;
import java.util.List;

public class MapStatement<T> {
  private final TableDefinition<T> definition;

  public MapStatement(TableDefinition<T> tableDefinition){
    this.definition = tableDefinition;
  }

  @SneakyThrows
  public void mapInstanceToStatement(
      T instance,
      List<ColumnDefinition> orderedColumns,
      PreparedStatement statement
  ){
    for (int i = 0; i < orderedColumns.size(); i++) {
      ColumnDefinition currentDef = orderedColumns.get(i);
      Field currentField = currentDef.getField();
      Object value = this.definition.getValueFromField(instance, currentField);
      this.passValidator(value, currentDef);
      statement.setObject((i + 1), value);
    }
  }


  private void passValidator(Object value, ColumnDefinition definition) throws Exception {
    if(
        value instanceof String &&
        definition.getColumnType().equals(ColumnType.VARCHAR)
    ){
      int varcharSize = definition.getSize();
      if(((String) value).length() > varcharSize){
        throw new Exception(
            "value of type " + ColumnType.VARCHAR + "(" + varcharSize + "): " + value + " is too long"
        );
      }
    }
  }
}
