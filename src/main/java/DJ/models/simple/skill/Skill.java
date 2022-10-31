package DJ.models.simple.skill;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Embeddable;
import javax.persistence.Id;

@Data
@Accessors(chain = true)
@Embeddable
public class Skill {

    private String name;
}
