package DJ.models.identity.units;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public abstract class User {

    private String name;

    private String lastName;

    private String description;

    private String email;
}
