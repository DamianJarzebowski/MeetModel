package DJ.models.simple.benefit;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Accessors(chain = true)
@Entity
public class Benefit {

    @Id
    private Long id;

    private String name;
}
