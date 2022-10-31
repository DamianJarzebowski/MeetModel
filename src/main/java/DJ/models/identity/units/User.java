package DJ.models.identity.units;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.MappedSuperclass;

@Data
@Accessors(chain = true)
@MappedSuperclass
public abstract class User {

    private String name;

    private String lastName;

    private String description;

    private String email;
}
