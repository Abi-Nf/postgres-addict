import lombok.SneakyThrows;
import postgres.addict.Table;
import postgres.addict.core.annotations.definition.TableDefinition;
import postgres.addict.core.annotations.transform.QueryString;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;

import static postgres.addict.AppEnv.POOL_CONNECTION;

public class Main {
  public static void testGenericType(){
    Class<?> repositoryClass = UserRepository.class;

    // Get the generic interfaces implemented by the repository class
    Type[] genericInterfaces = repositoryClass.getGenericInterfaces();

    System.out.println(Arrays.toString(genericInterfaces));

    // Find the interface that extends JpaRepository
    Type jpaRepositoryType = Arrays.stream(genericInterfaces)
        .filter(type -> type instanceof ParameterizedType)
        .findFirst()
        .orElse(null);

    // Extract the generic types (entity type and ID type) from JpaRepository
    if (jpaRepositoryType instanceof ParameterizedType) {
      Type[] typeArguments = ((ParameterizedType) jpaRepositoryType).getActualTypeArguments();

      // typeArguments[0] is the entity type
      // typeArguments[1] is the ID type
      if (typeArguments.length >= 2) {
        Class<?> entityType = (Class<?>) typeArguments[0];
        Class<?> idType = (Class<?>) typeArguments[1];

        System.out.println("Entity Type: " + entityType.getName());
        System.out.println("ID Type: " + idType.getName());
      }
    }
  }

  @SneakyThrows
  public static void main(String[] args) {
    TableDefinition<Model> definition = new TableDefinition<>(Model.class);
    QueryString<Model> queryString = new QueryString<>(definition);
    System.out.println(queryString.saveAll(100L, "id", "user", "password"));
  }
}
