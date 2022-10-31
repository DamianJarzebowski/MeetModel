package DJ.models.identity.units.model;

import DJ.models.identity.units.User;
import DJ.models.simple.characteristic.Characteristic;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.List;

@Data
@Accessors(chain = true)
@Entity
public class Model extends User {

    @Id
    private Long id;

    private int age;

    private List<Characteristic> characteristics;

}
