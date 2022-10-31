package DJ.models.simple;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Benefit {

    @Id
    private Long id;

    private String name;
}
