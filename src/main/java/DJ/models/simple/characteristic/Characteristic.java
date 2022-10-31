package DJ.models.simple.characteristic;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Accessors(chain = true)
@Entity
public class Characteristic {

    @Id
    private Long id;

    private String characteristic;

}
