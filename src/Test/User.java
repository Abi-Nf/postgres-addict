import lombok.Data;
import postgres.addict.Column;
import postgres.addict.Table;

@Table
@Data
public class User {
  @Column(identity = true)
  private Long id;
}
