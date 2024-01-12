package postgres.addict.core.annotations.transform;

import postgres.addict.core.annotations.definition.TableDefinition;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class QueryString<T> {
  private final TableDefinition<T> definition;
  private final String tableName;
  private final String idName;

  public QueryString(TableDefinition<T> definition){
    this.definition = definition;
    this.tableName = definition.getName();
    this.idName = definition.getColumnIdentity().getName();
  }

  public String save(String ...column){
    List<String> params = Collections.nCopies(column.length, "?");
    return "INSERT INTO \"" + this.tableName + "\" (" + String.join(",", column) + ") values (" + String.join(", ", params) + ")";
  }

  public String saveAll(Long scale, String ...column){
    List<String> params = Collections.nCopies(column.length, "?");
    List<String> batch = Collections.nCopies(scale.intValue(), "(" + String.join(", ", params) + ")");
    return "INSERT INTO \"" + this.tableName + "\" (" + String.join(",", column) + ") values " + String.join(",", batch);
  }

  public String find(String ...columns){
    String selection = columns[0].equals("*") ? "*" : String.join(", ", columns);
    return "SELECT " + selection + " from \"" + this.tableName + "\"";
  }

  public String delete(){
    return "DELETE FROM \"" + this.tableName + "\"";
  }

  public String update(String ...column){
    String updates = String.join(", ", Arrays.stream(column).map(v -> v + " = ?").toList());
    return "UPDATE \"" + this.tableName + "\" SET " + updates;
  }

  public String findAll(){
    return this.find("*");
  }

  public String findById(){
    return this.findAll() + " WHERE " + this.idName + " = ?";
  }

  private String deleteById(){
    return this.delete() + " WHERE " + this.idName + " = ?";
  }

  private String updateById(String ...column){
    return this.update(column) + " WHERE " + this.idName + " = ?";
  }
}
