package postgres.addict.core.annotations.transform;

import lombok.SneakyThrows;
import postgres.addict.core.annotations.definition.ColumnDefinition;
import postgres.addict.core.annotations.definition.TableDefinition;

import java.sql.ResultSet;
import java.util.List;

public class MapResultSet<T> {
  private final TableDefinition<T> definition;

  private final T instance;
  private final List<ColumnDefinition> orderedColumns;

  public MapResultSet(List<ColumnDefinition> orderedColumns, TableDefinition<T> definition){
    this.orderedColumns = orderedColumns;
    this.definition = definition;
    this.instance = definition.createInstance();
  }

  @SneakyThrows
  public T toInstance(ResultSet resultSet){
    for (ColumnDefinition column : this.orderedColumns) {
      Object columnValue = resultSet.getObject(column.getName());
      this.definition.setValueToFieldInstance(
          this.instance,
          column.getField(),
          columnValue
      );
    }
    return instance;
  }
}
