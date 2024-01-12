import lombok.Data;
import postgres.addict.Column;
import postgres.addict.Table;

@Table
@Data
public class Model {
  @Column(identity = true)
  private Long id;

  @Column
  private String username;

  @Column(references = true)
  private User user;
}
