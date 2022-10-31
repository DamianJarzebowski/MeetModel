package DJ.models.simple.achievement;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Embeddable;
import javax.persistence.Id;

@Data
@Accessors(chain = true)
@Embeddable
public class Achievement {

    private String name;
}
