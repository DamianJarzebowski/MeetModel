package DJ.models.identity.units.photographer;

import DJ.models.identity.units.User;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Accessors(chain = true)
@Entity
public class Photographer extends User {

    @Id
    private Long id;

}
